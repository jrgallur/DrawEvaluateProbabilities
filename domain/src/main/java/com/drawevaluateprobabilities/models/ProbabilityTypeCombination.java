package com.drawevaluateprobabilities.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Contains one combination of different types
 */
@ToString
@Getter
@Setter
public class ProbabilityTypeCombination {
    private String code;
    private List<ProbabilityTypeWeight> combination = new ArrayList<>();

    public void addCombinations(List<ProbabilityTypeWeight> combination) {
        combination.addAll(combination);
    }

    public void addProbabilityTypeWeight(ProbabilityTypeWeight probabilityTypeWeight) {
        combination.add(probabilityTypeWeight);
    }

    public String getCode() {
        if (code==null && combination.isEmpty()) {
            return null;
        }

        if (code==null) {
            List<ProbabilityTypeWeight> combinationAux = new ArrayList<>(getCombination());
            combinationAux.sort(
                    Comparator.comparing(p -> p.getProbabilityType().getCode())
            );
            StringBuilder sb = new StringBuilder();
            for (ProbabilityTypeWeight row : combinationAux) {
                if (!sb.isEmpty()) {
                    sb.append("#");
                }
                sb.append("#").append(row.getProbabilityType().getCode());
            }
            code = sb.toString();
        }
        return code;
    }
}
