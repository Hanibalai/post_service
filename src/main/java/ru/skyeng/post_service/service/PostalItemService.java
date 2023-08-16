package ru.skyeng.post_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skyeng.post_service.dto.PostalItemDto;
import ru.skyeng.post_service.entity.PostalItem;
import ru.skyeng.post_service.mapper.PostalItemMapper;
import ru.skyeng.post_service.repository.AddressRepository;
import ru.skyeng.post_service.repository.PostalItemRepository;

@Service
@RequiredArgsConstructor
public class PostalItemService {
    private final PostalItemRepository postalItemRepository;
    private final AddressRepository addressRepository;
    private final PostalItemMapper postalItemMapper;

    public PostalItemDto create(PostalItemDto postalItemDto) {
        PostalItem postalItem = postalItemMapper.toEntity(postalItemDto);
        addressRepository.save(postalItem.getRecipientAddress());
        return postalItemMapper.toDto(postalItemRepository.save(postalItem));
    }
}
