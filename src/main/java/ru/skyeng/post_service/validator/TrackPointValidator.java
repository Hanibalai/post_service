package ru.skyeng.post_service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skyeng.post_service.dto.TrackPointDto;
import ru.skyeng.post_service.entity.ItemTrackStatus;
import ru.skyeng.post_service.entity.PostalItem;
import ru.skyeng.post_service.entity.TrackPoint;
import ru.skyeng.post_service.exception.DataValidationException;

import java.util.Comparator;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TrackPointValidator {

    public void validate(PostalItem postalItem, TrackPointDto trackPointDto) {
        ItemTrackStatus status = trackPointDto.getStatus();
        boolean shouldHavePostOffice = status.equals(ItemTrackStatus.ARRIVED_AT_POST_OFFICE)
                || status.equals(ItemTrackStatus.LEFT_POST_OFFICE);

        if (shouldHavePostOffice && Objects.isNull(trackPointDto.getPostOfficeId())) {
            throw new DataValidationException("Post office id is required");
        }

        TrackPoint lastPoint = postalItem.getTrackHistory().stream()
                .max(Comparator.comparing(TrackPoint::getDate))
                .orElse(TrackPoint.builder().build());

        if (lastPoint.getStatus().equals(trackPointDto.getStatus())
                && lastPoint.getPostOffice().getId().equals(trackPointDto.getPostOfficeId())) {
            throw new DataValidationException("Track point is already registered");
        }
    }
}
