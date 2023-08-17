package ru.skyeng.post_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Информация об ошибке")
public class ErrorResponseDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Дата и время ошибки")
    private LocalDateTime timestamp;

    @Schema(description = "Статус ошибки")
    private int status;

    @Schema(description = "Описание ошибки")
    private String error;

    @Schema(description = "Адрес эндпоинта")
    private String path;
}
