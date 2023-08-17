package ru.skyeng.post_service.dto;

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
public class ItemTrackHistoryDto {
    private TrackStatus status;
    private List<String> history;
}
