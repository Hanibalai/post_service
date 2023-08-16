package ru.skyeng.post_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skyeng.post_service.entity.PostalItemType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalItemDto {
    private Long id;
    private PostalItemType type;
    @NotBlank(message = "Recipient index is required")
    private String recipientIndex;
    @NotBlank(message = "Recipient address is required")
    private String recipientName;
    @NotBlank(message = "Recipient country is required")
    private String recipientCountry;
    @NotBlank(message = "Recipient city is required")
    private String recipientCity;
    private String recipientState;
    @NotBlank(message = "Recipient street is required")
    private String recipientStreet;
    @NotBlank(message = "Recipient house is required")
    private String recipientHouse;
}
