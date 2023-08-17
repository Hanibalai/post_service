package ru.skyeng.post_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skyeng.post_service.dto.ItemTrackHistoryDto;
import ru.skyeng.post_service.dto.PostalItemDto;
import ru.skyeng.post_service.dto.TrackPointDto;
import ru.skyeng.post_service.service.PostalItemService;

import java.util.List;

@RestController
@RequestMapping("/api/PostalItems")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PostalItemController {
    private final PostalItemService postItemService;

    @PostMapping
    public PostalItemDto create(@Valid @RequestBody PostalItemDto postalItemDto) {
        return postItemService.create(postalItemDto);
    }

    @PutMapping("/{id}")
    public PostalItemDto update(@PathVariable Long id, @Valid @RequestBody TrackPointDto trackPointDto) {
        return postItemService.update(id, trackPointDto);
    }

    @GetMapping("/{id}/History")
    public ItemTrackHistoryDto getTrackHistory(@PathVariable Long id) {
        return postItemService.getTrackHistory(id);
    }

    @GetMapping("/{id}")
    public PostalItemDto getById(@PathVariable Long id) {
        return postItemService.getById(id);
    }

    @GetMapping
    public List<PostalItemDto> getAll() {
        return postItemService.getAll();
    }
}
