package com.drawevaluateprobabilities.ports.driven;

import com.drawevaluateprobabilities.models.ProbabilityTypeByDraw;
import com.drawevaluateprobabilities.models.types.TDateInteger;

public interface ProbabilityTypeByDrawPort {
    void upsert(ProbabilityTypeByDraw probabilityTypeByDraw);
    boolean existsByProbabilityTypeAndDrawDate(Short probabilityTypeId, TDateInteger drawdate);

    ProbabilityTypeByDraw findByProbabilityTypeAndDrawDate(Short probabilityTypeId, TDateInteger drawDate);
}
