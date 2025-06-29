package com.drawevaluateprobabilities.ports.driven;

import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.types.TDateInteger;

public interface NumberProbabilityListPort {
    void upsert(NumberProbabilityList numberProbabilityList);
    boolean existsByProbabilityTypeAndDrawDate(Short probabilityTypeId, TDateInteger drawdate);

    NumberProbabilityList findByProbabilityTypeAndDrawDate(Short probabilityTypeId, TDateInteger drawDate);
}
