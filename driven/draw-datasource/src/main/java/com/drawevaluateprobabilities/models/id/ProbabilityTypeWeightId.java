package com.drawevaluateprobabilities.models.id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProbabilityTypeWeightId implements Serializable {
    private Integer probabilityTypeCombinationId;
    private String probabilityTypeCode;
}
