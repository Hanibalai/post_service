package ru.skyeng.post_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skyeng.post_service.dto.PostOfficeDto;
import ru.skyeng.post_service.service.PostOfficeService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostOfficeControllerTest {
    @Mock
    private PostOfficeService postOfficeService;
    @InjectMocks
    private PostOfficeController postOfficeController;
    private PostOfficeDto postOfficeDto;

    @BeforeEach
    void setUp() {
        postOfficeDto = mock(PostOfficeDto.class);
    }

    @Test
    void create_shouldInvokeServiceCreateMethod() {
        postOfficeController.create(postOfficeDto);
        verify(postOfficeService).create(postOfficeDto);
    }

    @Test
    void getById_shouldInvokeServiceGetByIdMethod() {
        postOfficeController.getById(1L);
        verify(postOfficeService).getById(1L);
    }

    @Test
    void getAll_shouldInvokeServiceGetAll() {
        postOfficeController.getAll();
        verify(postOfficeService).getAll();
    }
}