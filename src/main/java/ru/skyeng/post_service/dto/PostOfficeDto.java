package ru.skyeng.post_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о почтовом отделении")
public class PostOfficeDto {

    @Schema(description = "Идентификатор почтового отделения")
    private Long id;

    @NotBlank(message = "Office name is required")
    @Schema(description = "Название почтового отделения")
    private String name;

    @NotBlank(message = "Office post code is required")
    @Schema(description = "Почтовый индекс")
    private String postCode;

    @NotBlank(message = "Office house is required")
    @Schema(description = "Номер дома")
    private String house;

    @NotBlank(message = "Office street is required")
    @Schema(description = "Название улицы")
    private String street;

    @NotBlank(message = "Office city is required")
    @Schema(description = "Название города")
    private String city;

    @NotBlank(message = "Office country is required")
    @Schema(description = "Название страны")
    private String country;
}
