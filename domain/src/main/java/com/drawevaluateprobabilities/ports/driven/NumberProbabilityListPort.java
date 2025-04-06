package com.drawevaluateprobabilities.ports.driven;

import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.types.TDateInteger;

public interface NumberProbabilityListPort {
    void upsert(NumberProbabilityList numberProbabilityList);
    boolean existsByNumberProbabilityTypeAndDrawDate(Short numberProbabilityTypeId, TDateInteger drawdate);

    NumberProbabilityList findByTypeAndDrawDate(Short numberProbabilityTypeId, TDateInteger drawDate);
}
