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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skyeng.post_service.dto.PostOfficeDto;
import ru.skyeng.post_service.service.PostOfficeService;

import java.util.List;

@RestController
@RequestMapping("/api/postOffices")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "Контроллер отделений", description = "Создание и получение почтовых отделений")
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

    @Operation(summary = "Создание почтового отделения")
    @PostMapping
    public PostOfficeDto create(@Valid @RequestBody PostOfficeDto postOfficeDto) {
        return postOfficeService.create(postOfficeDto);
    }

    @Operation(summary = "Получение почтового отделения по id")
    @GetMapping("/{id}")
    public PostOfficeDto getById(
            @Parameter(description = "Идентификатор отправления", example = "1")@PathVariable Long id) {
        return postOfficeService.getById(id);
    }

    @Operation(summary = "Получение всех почтовых отделений")
    @GetMapping
    public List<PostOfficeDto> getAll() {
        return postOfficeService.getAll();
    }
}
