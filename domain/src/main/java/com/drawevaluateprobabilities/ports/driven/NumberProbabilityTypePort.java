package com.drawevaluateprobabilities.ports.driven;

import com.drawevaluateprobabilities.models.NumberProbabilityType;

import java.util.List;

public interface NumberProbabilityTypePort {
    NumberProbabilityType getByCode(String code);

    List<NumberProbabilityType> findAll();
}
