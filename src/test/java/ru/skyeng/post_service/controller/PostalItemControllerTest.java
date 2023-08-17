package ru.skyeng.post_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skyeng.post_service.dto.PostalItemDto;
import ru.skyeng.post_service.dto.TrackPointDto;
import ru.skyeng.post_service.service.PostalItemService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostalItemControllerTest {

    @Mock
    private PostalItemService postalItemService;
    @InjectMocks
    private PostalItemController postalItemController;
    private PostalItemDto postalItemDto;
    private TrackPointDto trackPointDto;

    @BeforeEach
    void setUp() {
        postalItemDto = mock(PostalItemDto.class);
        trackPointDto = mock(TrackPointDto.class);
    }

    @Test
    void create_shouldInvokeServiceCreateMethod() {
        postalItemController.create(postalItemDto);
        verify(postalItemService).create(postalItemDto);
    }

    @Test
    void update_shouldInvokeServiceUpdateMethod() {
        postalItemController.update(1L, trackPointDto);
        verify(postalItemService).update(1L, trackPointDto);
    }

    @Test
    void getTrackHistory_shouldInvokeServiceGetTrackHistoryMethod() {
        postalItemController.getTrackHistory(1L);
        verify(postalItemService).getTrackHistory(1L);
    }

    @Test
    void getById_shouldInvokeServiceGetByIdMethod() {
        postalItemController.getById(1L);
        verify(postalItemService).getById(1L);
    }

    @Test
    void getAll_shouldInvokeServiceGetAllMethod() {
        postalItemController.getAll();
        verify(postalItemService).getAll();
    }
}