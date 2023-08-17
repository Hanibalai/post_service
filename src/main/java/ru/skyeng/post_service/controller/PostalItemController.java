package ru.skyeng.post_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import ru.skyeng.post_service.dto.TrackHistoryDto;
import ru.skyeng.post_service.dto.PostalItemDto;
import ru.skyeng.post_service.dto.TrackPointDto;
import ru.skyeng.post_service.service.PostalItemService;

import java.util.List;

@RestController
@RequestMapping("/api/postalItems")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "Контроллер отправлений", description = "Получение, создание и обновление отправлений")
public class PostalItemController {
    private final PostalItemService postItemService;

    @Operation(summary = "Создание и регистрация отправления")
    @PostMapping
    public PostalItemDto create(@Valid @RequestBody PostalItemDto postalItemDto) {
        log.debug("Received request to create postal item: {}", postalItemDto);
        return postItemService.create(postalItemDto);
    }

    @Operation(summary = "Обновление отправления: " +
            "в тело запроса передаются объект TrackPointDto для обновления статуса или промежуточного пункта")
    @PutMapping("/{id}")
    public PostalItemDto update(
            @Parameter(description = "Идентификатор отправления", example = "1") @PathVariable Long id,
            @Valid @RequestBody TrackPointDto trackPointDto) {
        log.debug("Received request to update postal item with id: {}", id);
        return postItemService.update(id, trackPointDto);
    }

    @Operation(summary = "Получение истории отправления " +
            "получаем объект ItemTrackHistoryDto, содержащий актуальный статус и историю отправления")
    @GetMapping("/{id}/history")
    public TrackHistoryDto getTrackHistory(
            @Parameter(description = "Идентификатор отправления", example = "1") @PathVariable Long id) {
        log.debug("Received request to get track history for postal item with id: {}", id);
        return postItemService.getTrackHistory(id);
    }

    @Operation(summary = "Получение отправления по id")
    @GetMapping("/{id}")
    public PostalItemDto getById(
            @Parameter(description = "Идентификатор отправления", example = "1")@PathVariable Long id) {
        log.debug("Received request to get postal item with id: {}", id);
        return postItemService.getById(id);
    }

    @Operation(summary = "Получение всех отправлений")
    @GetMapping
    public List<PostalItemDto> getAll() {
        log.debug("Received request to get all postal items");
        return postItemService.getAll();
    }
}
