package ru.skyeng.post_service.dto;

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
public class PostalItemDto {
    private Long id;
    private PostalItemType type;
    @NotBlank(message = "Recipient name is required")
    private String recipientName;
    @NotBlank(message = "Recipient post code is required")
    private String recipientPostCode;
    @NotBlank(message = "Recipient house is required")
    private String recipientHouse;
    @NotBlank(message = "Recipient street is required")
    private String recipientStreet;
    @NotBlank(message = "Recipient city is required")
    private String recipientCity;
    @NotBlank(message = "Recipient country is required")
    private String recipientCountry;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
