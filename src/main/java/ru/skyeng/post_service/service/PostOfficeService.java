package ru.skyeng.post_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skyeng.post_service.dto.PostOfficeDto;
import ru.skyeng.post_service.entity.PostOffice;
import ru.skyeng.post_service.exception.EntityNotFoundException;
import ru.skyeng.post_service.mapper.PostOfficeMapper;
import ru.skyeng.post_service.repository.AddressRepository;
import ru.skyeng.post_service.repository.PostOfficeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostOfficeService {

    private final PostOfficeRepository postOfficeRepository;
    private final AddressRepository addressRepository;
    private final PostOfficeMapper postOfficeMapper;

    @Transactional
    public PostOfficeDto create(PostOfficeDto postOfficeDto) {
        PostOffice postOffice = postOfficeMapper.toEntity(postOfficeDto);
        addressRepository.save(postOffice.getOfficeAddress());
        log.info("Created post office: {}", postOffice);

        return postOfficeMapper.toDto(postOfficeRepository.save(postOffice));
    }

    @Transactional(readOnly = true)
    public PostOfficeDto getById(Long id) {
        PostOffice postOffice = getPostOffice(id);
        return postOfficeMapper.toDto(postOffice);
    }

    @Transactional(readOnly = true)
    public List<PostOfficeDto> getAll() {
        return postOfficeRepository.findAll().stream()
                .map(postOfficeMapper::toDto)
                .toList();
    }

    protected PostOffice getPostOffice(Long id) {
        return postOfficeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post office with id " + id + " not found"));

    }
}
