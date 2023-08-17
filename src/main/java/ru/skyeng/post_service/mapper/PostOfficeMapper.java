package ru.skyeng.post_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skyeng.post_service.dto.PostOfficeDto;
import ru.skyeng.post_service.entity.Address;
import ru.skyeng.post_service.entity.PostOffice;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PostOfficeMapper {

    @Mapping(target = "postCode", source = "postOffice.officeAddress.postCode")
    @Mapping(target = "house", source = "postOffice.officeAddress.house")
    @Mapping(target = "street", source = "postOffice.officeAddress.street")
    @Mapping(target = "city", source = "postOffice.officeAddress.city")
    @Mapping(target = "country", source = "postOffice.officeAddress.country")
    PostOfficeDto toDto(PostOffice postOffice);

    @Mapping(target = "officeAddress", expression = "java(mapAddress(postOfficeDto))")
    PostOffice toEntity(PostOfficeDto postOfficeDto);

    default Address mapAddress(PostOfficeDto postOfficeDto) {
        return Address.builder()
                .postCode(postOfficeDto.getPostCode())
                .house(postOfficeDto.getHouse())
                .street(postOfficeDto.getStreet())
                .city(postOfficeDto.getCity())
                .country(postOfficeDto.getCountry())
                .build();
    }
}
