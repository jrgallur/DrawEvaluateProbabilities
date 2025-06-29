package com.drawevaluateprobabilities.ports.driven;

import com.drawevaluateprobabilities.models.ProbabilityType;

import java.util.List;

public interface ProbabilityTypePort {
    ProbabilityType getByCode(String code);

    List<ProbabilityType> findAll();
}
