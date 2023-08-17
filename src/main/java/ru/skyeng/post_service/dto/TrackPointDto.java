package ru.skyeng.post_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skyeng.post_service.entity.TrackStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackPointDto {

    @NotNull(message = "Status is required")
    private TrackStatus status;
    private Long postOfficeId;
}
