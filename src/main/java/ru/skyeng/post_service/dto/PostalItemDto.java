package ru.skyeng.post_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skyeng.post_service.entity.PostalItemType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о почтовом отправлении")
public class PostalItemDto {

    @Schema(description = "Идентификатор отправления")
    private Long id;

    @Schema(description = "Тип отправления")
    private PostalItemType type;

    @NotBlank(message = "Recipient name is required")
    @Schema(description = "ФИО получателя")
    private String recipientName;

    @NotBlank(message = "Recipient post code is required")
    @Schema(description = "Почтовый индекс получателя")
    private String recipientPostCode;

    @NotBlank(message = "Recipient house is required")
    @Schema(description = "Номер дома получателя")
    private String recipientHouse;

    @NotBlank(message = "Recipient street is required")
    @Schema(description = "Название улицы получателя")
    private String recipientStreet;

    @NotBlank(message = "Recipient city is required")
    @Schema(description = "Название города получателя")
    private String recipientCity;

    @NotBlank(message = "Recipient country is required")
    @Schema(description = "Название страны получателя")
    private String recipientCountry;

    @Schema(description = "Дата регистрации отправления")
    private LocalDateTime createdAt;

    @Schema(description = "Дата последнего обновления отправления")
    private LocalDateTime updatedAt;
}
