package com.drawevaluateprobabilities.models;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class ProbabilityTypeWeightCombination {
    private List<ProbabilityTypeWeight> probabilityTypeWeightList = new ArrayList<>();

    public void addCombination(List<ProbabilityTypeWeight> combination) {
        probabilityTypeWeightList.addAll(combination);
    }

    public void addProbabilityTypeWeight(ProbabilityTypeWeight probabilityTypeWeight) {
        probabilityTypeWeightList.add(probabilityTypeWeight);
    }

    public List<ProbabilityTypeWeight> getCombination() {
        return probabilityTypeWeightList;
    }
}
