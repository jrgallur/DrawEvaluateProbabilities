package com.drawevaluateprobabilities.ports.driven;

import com.drawevaluateprobabilities.models.NumberProbabilityType;

public interface NumberProbabilityTypePort {
    void upsert(NumberProbabilityType numberProbabilityType);

    NumberProbabilityType getByCode(String code);
}
