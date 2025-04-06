package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityTypePort;
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
    private NumberProbabilityTypePort numberProbabilityTypePort;
    private Random service;

    @BeforeEach
    void setUp() {
        service = new Random(numberProbabilityTypePort);
    }

    @Test
    void t1() {
        assertEquals("0000", service.getTypeCode());
    }

    @Test
    void t2() {
        when(numberProbabilityTypePort.getByCode(any())).thenReturn(NumberProbabilityType.builder().code("XXXX").build());

        assertEquals("XXXX", service.getNumberProbabilityType().getCode());
        assertEquals("XXXX", service.getNumberProbabilityType().getCode());
        verify(numberProbabilityTypePort, times(1)).getByCode(any());
    }

    @Test
    void t3() {
        NumberProbabilityList numberProbabilityList = service.getNumberProbabilityListByDate(TDateInteger.today());
        assertNotNull(numberProbabilityList.getNumberList());
        assertEquals(49, numberProbabilityList.getNumberList().size());
        assertEquals(0, BigDecimal.ONE.compareTo(numberProbabilityList.getNumberList().stream().reduce(BigDecimal.ZERO, BigDecimal::add)));
    }

}
