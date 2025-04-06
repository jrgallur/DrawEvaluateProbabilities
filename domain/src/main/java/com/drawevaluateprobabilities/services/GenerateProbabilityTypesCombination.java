package com.drawevaluateprobabilities.services;

import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.models.ProbabilityTypeWeight;
import com.drawevaluateprobabilities.models.ProbabilityTypeWeightCombination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GenerateProbabilityTypesCombination {

    public void getCombinations(List<NumberProbabilityType> numberProbabilityTypeList) {
        List<ProbabilityTypeWeightCombination> result = new ArrayList<>();
        for (int cont1 = 0; cont1 < numberProbabilityTypeList.size(); cont1++) {
            ProbabilityTypeWeightCombination probabilityTypeWeightCombination = new ProbabilityTypeWeightCombination();
            probabilityTypeWeightCombination.addProbabilityTypeWeight(ProbabilityTypeWeight.builder().numberProbabilityType(numberProbabilityTypeList.get(cont1)).weight(BigDecimal.ONE).build());
            result.add(probabilityTypeWeightCombination);
        }
        result.addAll(getCombinationsOrderN(numberProbabilityTypeList));
    }

    /**
     * Retrieves combinations of two types with the next conditions:
     * The sum of all of them must be 1
     * None of them can value 1
     * None of them can value 0
     * The vales must be 0.1 multiples (0, 0.1, 0.2, ..., 0.9)
     */
    public List<ProbabilityTypeWeightCombination> getCombinationsOrderN(List<NumberProbabilityType> types) {
        List<ProbabilityTypeWeightCombination> result = new ArrayList<>();
        BigDecimal step = BigDecimal.valueOf(0.1);

        generateCombinations(types, new ArrayList<>(), BigDecimal.ZERO, step, result);
        return result;
    }

    private void generateCombinations(List<NumberProbabilityType> types,
                                      List<BigDecimal> currentWeights,
                                      BigDecimal currentSum,
                                      BigDecimal step,
                                      List<ProbabilityTypeWeightCombination> result) {
        int index = currentWeights.size();
        int size = types.size();

        if (index == size - 1) {
            // Calcular el último peso
            BigDecimal lastWeight = BigDecimal.ONE.subtract(currentSum);

            // Verificar que esté en rango válido y no sea igual a 1
            if (isValidWeight(lastWeight)) {
                List<BigDecimal> finalWeights = new ArrayList<>(currentWeights);
                finalWeights.add(lastWeight);

                ProbabilityTypeWeightCombination combination = new ProbabilityTypeWeightCombination();
                for (int i = 0; i < size; i++) {
                    combination.addProbabilityTypeWeight(
                            ProbabilityTypeWeight.builder()
                                    .numberProbabilityType(types.get(i))
                                    .weight(finalWeights.get(i))
                                    .build()
                    );
                }
                result.add(combination);
            }
        } else {
            BigDecimal weight = step;
            while (weight.compareTo(BigDecimal.ONE) < 0) {
                BigDecimal newSum = currentSum.add(weight);

                // Solo continuar si la suma parcial es menor que 1
                if (newSum.compareTo(BigDecimal.ONE) < 0) {
                    List<BigDecimal> newWeights = new ArrayList<>(currentWeights);
                    newWeights.add(weight);
                    generateCombinations(types, newWeights, newSum, step, result);
                }

                weight = weight.add(step);
            }
        }
    }

    private boolean isValidWeight(BigDecimal weight) {
        BigDecimal step = BigDecimal.valueOf(0.1);
        BigDecimal one = BigDecimal.ONE;
        BigDecimal zero = BigDecimal.ZERO;

        // Aceptar solo pesos > 0 y < 1, y múltiplos de 0.1
        return weight.compareTo(zero) > 0 &&
                weight.compareTo(one) < 0 &&
                weight.remainder(step).compareTo(BigDecimal.ZERO) == 0;
    }


}
