package ru.skyeng.post_service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skyeng.post_service.dto.PostalItemDto;
import ru.skyeng.post_service.entity.Address;
import ru.skyeng.post_service.entity.PostalItem;
import ru.skyeng.post_service.entity.PostalItemType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PostalItemMapperTest {
    @Spy
    private PostalItemMapperImpl postalItemMapper;
    private PostalItem postalItem;
    private PostalItemDto postalItemDto;

    @BeforeEach
    void setUp() {
        postalItem = PostalItem.builder()
                .id(1L)
                .type(PostalItemType.LETTER)
                .recipientName("recipientName")
                .recipientAddress(Address.builder()
                        .postCode("123456")
                        .house("123")
                        .street("street")
                        .city("city")
                        .country("country")
                        .build())
                .build();

        postalItemDto = PostalItemDto.builder()
                .id(1L)
                .type(PostalItemType.LETTER)
                .recipientName("recipientName")
                .recipientPostCode("123456")
                .recipientHouse("123")
                .recipientStreet("street")
                .recipientCity("city")
                .recipientCountry("country")
                .build();
    }

    @Test
    void toDto_shouldMatchAllFields() {
        PostalItemDto actual = postalItemMapper.toDto(postalItem);
        assertEquals(postalItemDto, actual);
    }

    @Test
    void toEntity_shouldMatchAllFields() {
        PostalItem actual = postalItemMapper.toEntity(postalItemDto);
        assertEquals(postalItem, actual);
    }

    @Test
    void mapAddress_shouldMatchAllFields() {
        Address actual = postalItemMapper.mapAddress(postalItemDto);
        Address expected = postalItem.getRecipientAddress();
        assertEquals(expected, actual);
    }
}