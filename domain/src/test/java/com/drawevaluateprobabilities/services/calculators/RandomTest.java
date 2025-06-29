package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.ProbabilityType;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RandomTest {
    @Mock
    private ProbabilityTypePort probabilityTypePort;
    private Random service;

    @BeforeEach
    void setUp() {
        service = new Random(probabilityTypePort);
    }

    @Test
    void t1() {
        assertEquals("0000", service.getTypeCode());
    }

    @Test
    void t2() {
        when(probabilityTypePort.getByCode(any())).thenReturn(ProbabilityType.builder().code("XXXX").build());

        assertEquals("XXXX", service.getProbabilityType().getCode());
        assertEquals("XXXX", service.getProbabilityType().getCode());
        verify(probabilityTypePort, times(1)).getByCode(any());
    }

    @Test
    void t3() {
        NumberProbabilityList numberProbabilityList = service.getNumberProbabilityListByDate(TDateInteger.today());
        assertNotNull(numberProbabilityList.getNumberList());
        assertEquals(49, numberProbabilityList.getNumberList().size());
        assertEquals(0, BigDecimal.ONE.compareTo(numberProbabilityList.getNumberList().stream().reduce(BigDecimal.ZERO, BigDecimal::add)));
    }

}
