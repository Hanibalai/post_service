package ru.skyeng.post_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Информация о состоянии почтового отправления")
public class TrackPointDto {

    @NotNull(message = "Status is required")
    @Schema(description = "Статус почтового отправления", example = "ARRIVED_AT_POST_OFFICE")
    private TrackStatus status;

    @Schema(description = "Идентификатор поочтового отделения")
    private Long postOfficeId;
}
