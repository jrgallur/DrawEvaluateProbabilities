package com.drawevaluateprobabilities.services;


import com.drawevaluateprobabilities.models.ProbabilityType;
import com.drawevaluateprobabilities.models.ProbabilityTypeCombination;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GenerateCombinationTypesTest {
    @Test
    void test_order_3(){
        ProbabilityType type1 = ProbabilityType.builder().id((short) 1).code("0001").description("D1").build();
        ProbabilityType type2 = ProbabilityType.builder().id((short) 2).code("0002").description("D2").build();
        ProbabilityType type3 = ProbabilityType.builder().id((short) 3).code("0003").description("D3").build();

        List<ProbabilityTypeCombination> probabilityTypeCombinationList = GenerateCombinationTypes.getCombinationsOrderN(List.of(type1, type2, type3));

        System.out.println(probabilityTypeCombinationList);
    }

}
