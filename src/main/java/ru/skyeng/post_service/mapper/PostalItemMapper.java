package ru.skyeng.post_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skyeng.post_service.dto.PostalItemDto;
import ru.skyeng.post_service.entity.Address;
import ru.skyeng.post_service.entity.PostalItem;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostalItemMapper {

    @Mapping(target = "recipientPostCode", source = "recipientAddress.postCode")
    @Mapping(target = "recipientHouse", source = "recipientAddress.house")
    @Mapping(target = "recipientStreet", source = "recipientAddress.street")
    @Mapping(target = "recipientCity", source = "recipientAddress.city")
    @Mapping(target = "recipientCountry", source = "recipientAddress.country")
    PostalItemDto toDto(PostalItem postalItem);

    @Mapping(target = "recipientAddress", expression = "java(mapAddress(postalItemDto))")
    PostalItem toEntity(PostalItemDto postalItemDto);

    default Address mapAddress(PostalItemDto postalItemDto) {
        return Address.builder()
                .postCode(postalItemDto.getRecipientPostCode())
                .house(postalItemDto.getRecipientHouse())
                .street(postalItemDto.getRecipientStreet())
                .city(postalItemDto.getRecipientCity())
                .country(postalItemDto.getRecipientCountry())
                .build();
    }
}
