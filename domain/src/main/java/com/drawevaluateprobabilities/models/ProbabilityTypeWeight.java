package com.drawevaluateprobabilities.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProbabilityTypeWeight {
    ProbabilityType probabilityType;
    BigDecimal weight;
}
