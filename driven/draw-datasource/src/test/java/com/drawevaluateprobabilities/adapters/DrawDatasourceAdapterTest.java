package com.drawevaluateprobabilities.adapters;

import com.drawevaluateprobabilities.fakers.DrawFaker;
import com.drawevaluateprobabilities.mappers.DrawMapper;
import com.drawevaluateprobabilities.models.Draw;
import com.drawevaluateprobabilities.models.DrawMO;
import com.drawevaluateprobabilities.repositories.DrawRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class DrawDatasourceAdapterTest {

    private DrawDatasourceAdapter drawDatasourceAdapter;

    @Mock
    private DrawMapper drawMapper;

    @Mock
    private DrawRepository drawRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        drawDatasourceAdapter = new DrawDatasourceAdapter(drawMapper, drawRepository);
    }

    @Test
    void getDrawList_shouldReturnListOfDraws() {
        // Arrange
        List<DrawMO> mockDrawMOList = List.of(DrawMO.builder().build());
        when(drawRepository.findAll()).thenReturn(mockDrawMOList);
        when(drawMapper.toDomain(any())).thenReturn(DrawFaker.getDraw());

        // Act
        drawDatasourceAdapter.getDrawList();

        // Assert
        verify(drawRepository, times(1)).findAll();
        verify(drawMapper, times(mockDrawMOList.size())).toDomain(any());
    }

    @Test
    void insertDraw_shouldInsertDraw() {
        // Arrange
        Draw mockDraw = DrawFaker.getDraw();

        // Act
        drawDatasourceAdapter.insertDraw(mockDraw);

        // Assert
        verify(drawMapper, times(1)).toModel(any());
        verify(drawRepository, times(1)).save(any());
    }
}