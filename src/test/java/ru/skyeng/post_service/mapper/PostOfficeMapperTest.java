package ru.skyeng.post_service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skyeng.post_service.dto.PostOfficeDto;
import ru.skyeng.post_service.entity.Address;
import ru.skyeng.post_service.entity.PostOffice;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    void toDto_shouldReturnNullFromEmptyFields() {
        PostOfficeDto postOfficeDto = postOfficeMapper.toDto(PostOffice.builder().build());
        assertAll(() -> {
            assertNull(postOfficeDto.getId());
            assertNull(postOfficeDto.getName());
            assertNull(postOfficeDto.getPostCode());
            assertNull(postOfficeDto.getHouse());
            assertNull(postOfficeDto.getStreet());
            assertNull(postOfficeDto.getCity());
            assertNull(postOfficeDto.getCountry());
        });
    }

    @Test
    void toDto_shouldReturnNullFromNullEntity() {
        PostOfficeDto actual = postOfficeMapper.toDto(null);
        assertNull(actual);
    }

    @Test
    void toEntity_shouldMatchAllFields() {
        PostOffice postOffice = postOfficeMapper.toEntity(postOfficeDto);
        assertEquals(postOffice, postOffice);
    }

    @Test
    void toEntity_shouldReturnNullFromEmptyFields() {
        PostOffice postOffice = postOfficeMapper.toEntity(PostOfficeDto.builder().build());
        assertAll(() -> {
            assertNull(postOffice.getId());
            assertNull(postOffice.getName());
            assertNull(postOffice.getOfficeAddress().getPostCode());
            assertNull(postOffice.getOfficeAddress().getHouse());
            assertNull(postOffice.getOfficeAddress().getStreet());
            assertNull(postOffice.getOfficeAddress().getCity());
            assertNull(postOffice.getOfficeAddress().getCountry());
        });
    }

    @Test
    void toEntity_shouldReturnNullFromNullDto() {
        PostOffice actual = postOfficeMapper.toEntity(null);
        assertNull(actual);
    }

    @Test
    void mapAddress_shouldMatchAllFields() {
        Address actual = postOfficeMapper.mapAddress(postOfficeDto);
        Address expected = postOffice.getOfficeAddress();

        assertEquals(expected, actual);
    }
}