package ru.skyeng.post_service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skyeng.post_service.dto.PostOfficeDto;
import ru.skyeng.post_service.entity.Address;
import ru.skyeng.post_service.entity.PostOffice;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PostOfficeMapperTest {
    @Spy
    private PostOfficeMapperImpl postOfficeMapper;
    private PostOffice postOffice;
    private PostOfficeDto postOfficeDto;

    @BeforeEach
    void setUp() {
        postOffice = PostOffice.builder()
                .id(1L)
                .name("name")
                .officeAddress(Address.builder()
                        .postCode("postCode")
                        .house("house")
                        .street("street")
                        .city("city")
                        .country("country")
                        .build())
                .build();

        postOfficeDto = PostOfficeDto.builder()
                .id(1L)
                .name("name")
                .postCode("postCode")
                .house("house")
                .street("street")
                .city("city")
                .country("country")
                .build();
    }

    @Test
    void toDto_shouldMatchAllFields() {
        PostOfficeDto postOfficeDto = postOfficeMapper.toDto(postOffice);
        assertEquals(postOfficeDto, postOfficeDto);
    }

    @Test
    void toEntity_shouldMatchAllFields() {
        PostOffice postOffice = postOfficeMapper.toEntity(postOfficeDto);
        assertEquals(postOffice, postOffice);
    }

    @Test
    void mapAddress_shouldMatchAllFields() {
        Address actual = postOfficeMapper.mapAddress(postOfficeDto);
        Address expected = postOffice.getOfficeAddress();

        assertEquals(expected, actual);
    }
}