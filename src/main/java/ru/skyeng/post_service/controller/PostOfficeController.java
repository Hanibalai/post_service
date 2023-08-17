package ru.skyeng.post_service.controller;

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
@RequestMapping("/api/PostOffices")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

    @PostMapping
    public PostOfficeDto create(@Valid @RequestBody PostOfficeDto postOfficeDto) {
        return postOfficeService.create(postOfficeDto);
    }

    @GetMapping("/{id}")
    public PostOfficeDto getById(@PathVariable Long id) {
        return postOfficeService.getById(id);
    }

    @GetMapping
    public List<PostOfficeDto> getAll() {
        return postOfficeService.getAll();
    }
}
