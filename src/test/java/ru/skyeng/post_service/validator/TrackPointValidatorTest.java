package ru.skyeng.post_service.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.skyeng.post_service.dto.TrackPointDto;
import ru.skyeng.post_service.entity.Address;
import ru.skyeng.post_service.entity.PostOffice;
import ru.skyeng.post_service.entity.PostalItem;
import ru.skyeng.post_service.entity.PostalItemType;
import ru.skyeng.post_service.entity.TrackPoint;
import ru.skyeng.post_service.entity.TrackStatus;
import ru.skyeng.post_service.exception.DataValidationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TrackPointValidatorTest {
    @InjectMocks
    private TrackPointValidator trackPointValidator;
    private TrackPointDto trackPointDto;
    private PostalItem postalItem;

    @BeforeEach
    void setUp() {
        TrackPoint trackPoint = TrackPoint.builder()
                .status(TrackStatus.REGISTERED)
                .postOffice(PostOffice.builder().id(1L).build())
                .build();

        postalItem = PostalItem.builder()
                .type(PostalItemType.LETTER)
                .recipientName("name")
                .recipientAddress(Address.builder().build())
                .trackHistory(List.of(trackPoint))
                .build();

        trackPointDto = TrackPointDto.builder()
                .status(TrackStatus.REGISTERED)
                .postOfficeId(1L)
                .build();
    }

    @Test
    void validate_shouldThrowDataValidationException_whenPostOfficeIsNull() {
        trackPointDto.setStatus(TrackStatus.LEFT_POST_OFFICE);
        trackPointDto.setPostOfficeId(null);

        assertThrows(DataValidationException.class,
                () -> trackPointValidator.validate(postalItem, trackPointDto),
                "Post office id is required");
    }

    @Test
    void validate_shouldThrowDataValidationException_whenPointIsAlreadyRegistered() {
        assertThrows(DataValidationException.class,
                () -> trackPointValidator.validate(postalItem, trackPointDto),
                "Track point is already registered");
    }

    @Test
    void validate_shouldNotThrowException() {
        trackPointDto.setStatus(TrackStatus.DELIVERED_TO_RECIPIENT);

        assertDoesNotThrow(() -> trackPointValidator.validate(postalItem, trackPointDto));
    }
}