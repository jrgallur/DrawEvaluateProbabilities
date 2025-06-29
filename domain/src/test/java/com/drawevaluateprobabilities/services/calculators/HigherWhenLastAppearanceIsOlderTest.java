package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.models.ProbabilityType;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypeByDrawPort;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HigherWhenLastAppearanceIsOlderTest {
    private HigherWhenLastAppearanceIsOlder service;

    @Mock
    private ProbabilityTypePort probabilityTypePort;

    @Mock
    private ProbabilityTypeByDrawPort probabilityTypeByDrawPort;

    @BeforeEach
    void setUp() {
        service = new HigherWhenLastAppearanceIsOlder(probabilityTypePort, probabilityTypeByDrawPort);
        when(probabilityTypePort.getByCode(any())).thenReturn(new ProbabilityType());
    }

    //@Test
    void test_1() {

    }
}
