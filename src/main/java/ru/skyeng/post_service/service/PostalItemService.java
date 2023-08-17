package ru.skyeng.post_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skyeng.post_service.dto.ItemTrackHistoryDto;
import ru.skyeng.post_service.dto.PostalItemDto;
import ru.skyeng.post_service.dto.TrackPointDto;
import ru.skyeng.post_service.entity.TrackStatus;
import ru.skyeng.post_service.entity.PostOffice;
import ru.skyeng.post_service.entity.PostalItem;
import ru.skyeng.post_service.entity.TrackPoint;
import ru.skyeng.post_service.exception.EntityNotFoundException;
import ru.skyeng.post_service.mapper.PostalItemMapper;
import ru.skyeng.post_service.repository.AddressRepository;
import ru.skyeng.post_service.repository.PostalItemRepository;
import ru.skyeng.post_service.repository.TrackPointRepository;
import ru.skyeng.post_service.validator.TrackPointValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostalItemService {
    private final PostalItemRepository postalItemRepository;
    private final AddressRepository addressRepository;
    private final TrackPointRepository trackPointRepository;
    private final PostOfficeService postOfficeService;
    private final TrackPointValidator trackPointValidator;
    private final PostalItemMapper postalItemMapper;

    @Transactional
    public PostalItemDto create(PostalItemDto postalItemDto) {
        PostalItem postalItem = postalItemMapper.toEntity(postalItemDto);

        addressRepository.save(postalItem.getRecipientAddress());
        postalItem = postalItemRepository.save(postalItem);

        TrackPoint trackPoint = TrackPoint.builder()
                .postalItem(postalItem)
                .status(TrackStatus.REGISTERED)
                .build();

        trackPointRepository.save(trackPoint);
        log.info("Created postal item: {}", postalItem);

        return postalItemMapper.toDto(postalItem);
    }

    @Transactional
    public PostalItemDto update(Long postalItemId, TrackPointDto trackPointDto) {
        PostalItem postalItem = getItem(postalItemId);

        trackPointValidator.validate(postalItem, trackPointDto);

        TrackPoint trackPoint = TrackPoint.builder()
                .postalItem(postalItem)
                .status(trackPointDto.getStatus())
                .build();

        if (Objects.nonNull(trackPointDto.getPostOfficeId())) {
            trackPoint.setPostOffice(postOfficeService.getPostOffice(trackPointDto.getPostOfficeId()));
        }

        trackPointRepository.save(trackPoint);

        postalItem.getTrackHistory().add(trackPoint);
        log.info("Updated postal item: {}", postalItem);

        return postalItemMapper.toDto(postalItem);
    }

    @Transactional(readOnly = true)
    public ItemTrackHistoryDto getTrackHistory(Long id) {
        PostalItem postalItem = getItem(id);

        TrackStatus actualStatus = postalItem.getTrackHistory().stream()
                .max(Comparator.comparing(TrackPoint::getDate))
                .map(TrackPoint::getStatus)
                .orElse(TrackStatus.REGISTERED);

        List<String> history = postalItem.getTrackHistory().stream()
                .sorted(Comparator.comparing(TrackPoint::getDate))
                .map(TrackPoint::toHistoryValue)
                .toList();

        return ItemTrackHistoryDto.builder()
                .status(actualStatus)
                .history(history)
                .build();
    }

    @Transactional(readOnly = true)
    public PostalItemDto getById(Long id) {
        PostalItem postalItem = getItem(id);
        return postalItemMapper.toDto(postalItem);
    }

    @Transactional(readOnly = true)
    public List<PostalItemDto> getAll() {
        return postalItemRepository.findAll().stream()
                .map(postalItemMapper::toDto)
                .toList();
    }

    private PostalItem getItem(Long id) {
        return postalItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postal item with id " + id + " not found"));
    }
}
