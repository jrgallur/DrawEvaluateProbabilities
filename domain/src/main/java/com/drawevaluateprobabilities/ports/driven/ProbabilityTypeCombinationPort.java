package com.drawevaluateprobabilities.ports.driven;

import com.drawevaluateprobabilities.models.ProbabilityTypeCombination;

public interface ProbabilityTypeCombinationPort {
    void upsert(ProbabilityTypeCombination probabilityTypeCombination);
}
