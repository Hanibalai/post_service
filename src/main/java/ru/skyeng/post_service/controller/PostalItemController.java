package ru.skyeng.post_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skyeng.post_service.dto.PostalItemDto;
import ru.skyeng.post_service.service.PostalItemService;

@RestController
@RequestMapping("/api/postalItems")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PostalItemController {
    private final PostalItemService postItemService;

    @PostMapping()
    public PostalItemDto create(@RequestBody PostalItemDto postalItemDto) {
        return postItemService.create(postalItemDto);
    }
}
