package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.models.Draw;
import com.drawevaluateprobabilities.models.DrawList;
import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.models.types.TNumberList;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityTypePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HigherWhenLastAppearanceIsOlderTest {
    private HigherWhenLastAppearanceIsOlder service;

    @Mock
    private NumberProbabilityTypePort numberProbabilityTypePort;

    @Mock
    private NumberProbabilityListPort numberProbabilityListPort;

    @BeforeEach
    void setUp() {
        service = new HigherWhenLastAppearanceIsOlder(numberProbabilityTypePort, numberProbabilityListPort);
        when(numberProbabilityTypePort.getByCode(any())).thenReturn(new NumberProbabilityType());
    }

    @Test
    void test_1() {

    }
}
