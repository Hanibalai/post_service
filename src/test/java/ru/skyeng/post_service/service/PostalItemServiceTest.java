package ru.skyeng.post_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.skyeng.post_service.dto.TrackHistoryDto;
import ru.skyeng.post_service.dto.PostalItemDto;
import ru.skyeng.post_service.dto.TrackPointDto;
import ru.skyeng.post_service.entity.PostalItem;
import ru.skyeng.post_service.entity.TrackPoint;
import ru.skyeng.post_service.entity.TrackStatus;
import ru.skyeng.post_service.mapper.PostalItemMapperImpl;
import ru.skyeng.post_service.repository.AddressRepository;
import ru.skyeng.post_service.repository.PostalItemRepository;
import ru.skyeng.post_service.repository.TrackPointRepository;
import ru.skyeng.post_service.validator.TrackPointValidator;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostalItemServiceTest {
    @Mock
    private PostalItemRepository postalItemRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private TrackPointRepository trackPointRepository;
    @Mock
    private PostOfficeService postOfficeService;
    @Mock
    private TrackPointValidator trackPointValidator;
    @Mock
    private PostalItemMapperImpl postalItemMapper;
    @InjectMocks
    private PostalItemService postalItemService;
    private PostalItem postalItem;
    private PostalItemDto postalItemDto;
    private TrackPointDto trackPointDto;


    @BeforeEach
    void setUp() {
        postalItem = mock(PostalItem.class);
        postalItemDto = mock(PostalItemDto.class);
        trackPointDto = mock(TrackPointDto.class);

        when(postalItemMapper.toEntity(postalItemDto)).thenReturn(postalItem);
        when(postalItemMapper.toDto(postalItem)).thenReturn(postalItemDto);
        when(postalItemRepository.findById(1L)).thenReturn(Optional.of(postalItem));
        when(postalItemRepository.save(postalItem)).thenReturn(postalItem);
    }

    @Test
    void create_shouldSaveAddress() {
        postalItemService.create(postalItemDto);

        verify(addressRepository).save(postalItem.getRecipientAddress());
    }

    @Test
    void create_shouldSaveTrackPoint() {
        postalItemService.create(postalItemDto);

        verify(trackPointRepository).save(Mockito.any());
    }

    @Test
    void create_shouldSavePostalItem() {
        postalItemService.create(postalItemDto);
        verify(postalItemRepository).save(postalItem);
    }

    @Test
    void create_shouldInvokeMapperBothMethods() {
        when(postalItemRepository.save(postalItem)).thenReturn(postalItem);

        postalItemService.create(postalItemDto);

        verify(postalItemMapper).toEntity(postalItemDto);
        verify(postalItemMapper).toDto(postalItem);
    }

    @Test
    void update_shouldValidateTrackPoint() {
        when(postalItemRepository.findById(1L)).thenReturn(Optional.of(postalItem));

        postalItemService.update(1L, trackPointDto);

        verify(trackPointValidator).validate(postalItem, trackPointDto);
    }

    @Test
    void update_shouldSaveTrackPoint() {
        postalItemService.update(1L, trackPointDto);
        verify(trackPointRepository).save(Mockito.any());
    }

    @Test
    void update_shouldRetrieveTrackHistory() {
        postalItemService.update(1L, trackPointDto);
        verify(postalItem).getTrackHistory();
    }

    @Test
    void update_shouldInvokeMapperBothMethods() {
        when(postalItemRepository.save(postalItem)).thenReturn(postalItem);

        postalItemService.create(postalItemDto);

        verify(postalItemMapper).toEntity(postalItemDto);
        verify(postalItemMapper).toDto(postalItem);
    }

    @Test
    void getTrackHistory_shouldReturnTrackHistoryObject() {
        TrackHistoryDto actual = TrackHistoryDto.builder()
                .status(TrackStatus.REGISTERED)
                .history(new ArrayList<>())
                .build();

        assertEquals(actual, postalItemService.getTrackHistory(1L));
    }

    @Test
    void getById_shouldInvokeRepositoryAndMapperMethods() {
        postalItemService.getById(1L);

        verify(postalItemRepository).findById(1L);
        verify(postalItemMapper).toDto(postalItem);
    }

    @Test
    void getAll_shouldInvokeRepositoryFindAllMethod() {
        postalItemService.getAll();

        verify(postalItemRepository).findAll();
    }
}