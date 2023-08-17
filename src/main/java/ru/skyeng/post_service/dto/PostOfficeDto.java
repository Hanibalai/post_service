package ru.skyeng.post_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostOfficeDto {

    private Long id;
    @NotBlank(message = "Office name is required")
    private String name;
    @NotBlank(message = "Office post code is required")
    private String postCode;
    @NotBlank(message = "Office house is required")
    private String house;
    @NotBlank(message = "Office street is required")
    private String street;
    @NotBlank(message = "Office city is required")
    private String city;
    @NotBlank(message = "Office country is required")
    private String country;
}
