package com.drawevaluateprobabilities.services.calculators.ifaces;

import com.drawevaluateprobabilities.exceptions.NumberProbabilityTypeNotFoundException;
import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityTypePort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ProbabilityType {
    public static final int DIVIDE_SCALE = 10;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private NumberProbabilityType numberProbabilityType;

    private final NumberProbabilityTypePort numberProbabilityTypePort;
    private final NumberProbabilityListPort numberProbabilityListPort;

    protected ProbabilityType(NumberProbabilityTypePort numberProbabilityTypePort, NumberProbabilityListPort numberProbabilityListPort) {
        this.numberProbabilityTypePort = numberProbabilityTypePort;
        this.numberProbabilityListPort = numberProbabilityListPort;
    }

    public abstract String getTypeCode();

    public NumberProbabilityList getNumberProbabilityListByDate(TDateInteger calculationDrawDate) {
        return numberProbabilityListPort.findByTypeAndDrawDate(getNumberProbabilityType().getId(), calculationDrawDate);
    }

    public NumberProbabilityType getNumberProbabilityType() {
        if (numberProbabilityType == null) {
            numberProbabilityType = numberProbabilityTypePort.getByCode(getTypeCode());
            if (numberProbabilityType == null) {
                throw new NumberProbabilityTypeNotFoundException();
            }
        }
        return numberProbabilityType;
    }

    protected NumberProbabilityList correctStatistics(NumberProbabilityList numberProbabilityList) {
        BigDecimal sum = numberProbabilityList.getNumberList().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        if (sum.compareTo(BigDecimal.ONE) > 0) {
            return correctGreaterOneStatistics(numberProbabilityList, sum);
        } else if (sum.compareTo(BigDecimal.ONE) < 0) {
            return correctLowerOneStatistics(numberProbabilityList, sum);
        }
        return numberProbabilityList;
    }

    protected NumberProbabilityList correctGreaterOneStatistics(NumberProbabilityList numberProbabilityList, final BigDecimal initialSum) {
        BigDecimal partialSum = new BigDecimal(initialSum.toString());
        BigDecimal unit = BigDecimal.ONE.movePointLeft(DIVIDE_SCALE);
        List<Integer> sortedIndex = getLowerToGreaterSortedIndex(numberProbabilityList);
        while (partialSum.compareTo(BigDecimal.ONE) > 0) {
            BigDecimal difference = partialSum.subtract(BigDecimal.ONE);
            int numberOfValuesToChange = difference.divide(unit, DIVIDE_SCALE, ROUNDING_MODE).intValue();
            if (numberOfValuesToChange > 49) {
                numberOfValuesToChange = 49;
            }
            numberOfValuesToChange--;
            for (int indexesIndex = 0; indexesIndex <= numberOfValuesToChange; indexesIndex++) {
                int valuesIndex = sortedIndex.get(indexesIndex) + 1;

                numberProbabilityList.setNumberProbability(valuesIndex, numberProbabilityList.getNumberProbability(valuesIndex).subtract(unit));
            }
            partialSum = numberProbabilityList.getNumberList().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return numberProbabilityList;
    }

    protected NumberProbabilityList correctLowerOneStatistics(NumberProbabilityList numberProbabilityList, final BigDecimal initialSum) {
        BigDecimal partialSum = new BigDecimal(initialSum.toString());
        BigDecimal unit = BigDecimal.ONE.movePointLeft(DIVIDE_SCALE);
        List<Integer> sortedIndex = getGreaterToLowerSortedIndex(numberProbabilityList);
        while (partialSum.compareTo(BigDecimal.ONE) < 0) {
            BigDecimal difference = BigDecimal.ONE.subtract(partialSum);
            int numberOfValuesToChange = difference.divide(unit, DIVIDE_SCALE, ROUNDING_MODE).intValue();
            if (numberOfValuesToChange > 49) {
                numberOfValuesToChange = 49;
            }
            numberOfValuesToChange--;
            for (int indexesIndex = 0; indexesIndex <= numberOfValuesToChange; indexesIndex++) {
                int valuesIndex = sortedIndex.get(indexesIndex) + 1;

                numberProbabilityList.setNumberProbability(valuesIndex, numberProbabilityList.getNumberProbability(valuesIndex).add(unit));
            }
            partialSum = numberProbabilityList.getNumberList().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return numberProbabilityList;
    }

    protected List<Integer> getLowerToGreaterSortedIndex(NumberProbabilityList numberProbabilityList) {
        return IntStream.range(0, numberProbabilityList.getNumberList().size())  // Range generate indexes as int [0, 1, 2, ...]
                // Convert from int to Integer
                .boxed()
                .sorted(Comparator
                        // Sort bye the probability value
                        .comparing(numberProbabilityList.getNumberList()::get)
                        // When the probability value is equal, then sort by index
                        .thenComparingInt(i -> i)
                )
                .toList();
    }

    protected List<Integer> getGreaterToLowerSortedIndex(NumberProbabilityList numberProbabilityList) {
        List<Integer> result = IntStream.range(0, numberProbabilityList.getNumberList().size())  // Range generate indexes as int [0, 1, 2, ...]
                // Convert from int to Integer
                .boxed()
                .sorted(Comparator
                        // Sort bye the probability value
                        .comparing(numberProbabilityList.getNumberList()::get)
                        // When the probability value is equal, then sort by index
                        .thenComparingInt(i -> i)
                )
                .collect(Collectors.toList());
        Collections.reverse(result);

        return result;
    }
}
