package ru.skyeng.post_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.skyeng.post_service.dto.PostOfficeDto;
import ru.skyeng.post_service.entity.PostOffice;
import ru.skyeng.post_service.exception.EntityNotFoundException;
import ru.skyeng.post_service.mapper.PostOfficeMapper;
import ru.skyeng.post_service.repository.AddressRepository;
import ru.skyeng.post_service.repository.PostOfficeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostOfficeServiceTest {

    @Mock
    private PostOfficeRepository postOfficeRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private PostOfficeMapper postOfficeMapper;
    @InjectMocks
    private PostOfficeService postOfficeService;
    private PostOffice postOffice;
    private PostOfficeDto postOfficeDto;

    @BeforeEach
    void setUp() {
        postOffice = mock(PostOffice.class);
        postOfficeDto = mock(PostOfficeDto.class);

        when(postOfficeMapper.toEntity(postOfficeDto)).thenReturn(postOffice);
        when(postOfficeMapper.toDto(postOffice)).thenReturn(postOfficeDto);
        when(postOfficeRepository.findById(1L)).thenReturn(Optional.of(postOffice));
    }

    @Test
    void create_shouldSavePostOffice() {
        postOfficeService.create(postOfficeDto);
        verify(postOfficeRepository).save(postOffice);
    }

    @Test
    void create_shouldInvokeMapperBothMethods() {
        when(postOfficeRepository.save(postOffice)).thenReturn(postOffice);

        postOfficeService.create(postOfficeDto);

        verify(postOfficeMapper).toEntity(postOfficeDto);
        verify(postOfficeMapper).toDto(postOffice);
    }

    @Test
    void getById_shouldInvokeRepositoryAndMapperMethods() {
        postOfficeService.getById(1L);

        verify(postOfficeRepository).findById(1L);
        verify(postOfficeMapper).toDto(postOffice);
    }

    @Test
    void getAll_shouldInvokeRepositoryFindAllMethod() {
        postOfficeService.getAll();
        verify(postOfficeRepository).findAll();
    }

    @Test
    void getPostOffice_shouldInvokeRepositoryFindByIdMethod() {
        postOfficeService.getPostOffice(1L);
        verify(postOfficeRepository).findById(1L);
    }

    @Test
    void getPostOffice_shouldThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class,
                () -> postOfficeService.getPostOffice(2L),
                "Post office with id 2 not found");
    }
}