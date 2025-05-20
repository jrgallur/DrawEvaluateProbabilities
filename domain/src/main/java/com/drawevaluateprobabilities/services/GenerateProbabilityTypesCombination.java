package com.drawevaluateprobabilities.services;

import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.models.ProbabilityTypeWeight;
import com.drawevaluateprobabilities.models.ProbabilityTypeWeightCombination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GenerateProbabilityTypesCombination {
    private static final BigDecimal STEP = BigDecimal.valueOf(0.1);

    private GenerateProbabilityTypesCombination() {
        throw new IllegalStateException("Utility class");
    }

    public static List<ProbabilityTypeWeightCombination> getCombinations(List<NumberProbabilityType> numberProbabilityTypeList) {
        return new ArrayList<>(getCombinationsOrderN(numberProbabilityTypeList));
    }

    /**
     * Retrieves combinations of different types with the next conditions:
     * The sum of all of them must be 1
     * The vales must be STEP multiples (0, 0.1, 0.2, ..., 0.9, 1.0)
     */
    public static List<ProbabilityTypeWeightCombination> getCombinationsOrderN(List<NumberProbabilityType> types) {
        List<ProbabilityTypeWeightCombination> result = new ArrayList<>();

        generateCombinations(types, new ArrayList<>(), BigDecimal.ZERO, STEP, result);
        return result;
    }

    private static void generateCombinations(List<NumberProbabilityType> types,
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
            BigDecimal weight = BigDecimal.ZERO;
            while (weight.compareTo(BigDecimal.ONE) <= 0) {
                BigDecimal newSum = currentSum.add(weight);

                // Solo continuar si la suma parcial es menor que 1
                if (newSum.compareTo(BigDecimal.ONE) <= 0) {
                    List<BigDecimal> newWeights = new ArrayList<>(currentWeights);
                    newWeights.add(weight);
                    generateCombinations(types, newWeights, newSum, step, result);
                }

                weight = weight.add(step);
            }
        }
    }

    private static boolean isValidWeight(BigDecimal weight) {
        // Aceptar solo pesos múltiplos del paso
        return weight.remainder(STEP).compareTo(BigDecimal.ZERO) == 0;
    }


}
