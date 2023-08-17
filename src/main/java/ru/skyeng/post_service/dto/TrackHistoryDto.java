package ru.skyeng.post_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skyeng.post_service.entity.TrackStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "История пути почтового отправления")
public class TrackHistoryDto {

    @Schema(description = "Актуальный статус отправления")
    private TrackStatus status;

    @Schema(description = "История пути (включает дату, вермя и статус отправления)")
    private List<String> history;
}
