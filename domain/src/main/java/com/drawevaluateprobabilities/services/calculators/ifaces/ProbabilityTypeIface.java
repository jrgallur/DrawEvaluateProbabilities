package com.drawevaluateprobabilities.services.calculators.ifaces;

import com.drawevaluateprobabilities.exceptions.ProbabilityTypeNotFoundException;
import com.drawevaluateprobabilities.models.ProbabilityType;
import com.drawevaluateprobabilities.models.ProbabilityTypeByDraw;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypeByDrawPort;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypePort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ProbabilityTypeIface {
    public static final int DIVIDE_SCALE = 10;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private ProbabilityType probabilityType;

    private final ProbabilityTypePort probabilityTypePort;
    private final ProbabilityTypeByDrawPort probabilityTypeByDrawPort;

    protected ProbabilityTypeIface(ProbabilityTypePort probabilityTypePort, ProbabilityTypeByDrawPort probabilityTypeByDrawPort) {
        this.probabilityTypePort = probabilityTypePort;
        this.probabilityTypeByDrawPort = probabilityTypeByDrawPort;
    }

    public abstract String getTypeCode();

    public ProbabilityTypeByDraw getProbabilityTypeByDrawByDate(TDateInteger calculationDrawDate) {
        return probabilityTypeByDrawPort.findByProbabilityTypeAndDrawDate(getProbabilityType().getId(), calculationDrawDate);
    }

    public ProbabilityType getProbabilityType() {
        if (probabilityType == null) {
            probabilityType = probabilityTypePort.getByCode(getTypeCode());
            if (probabilityType == null) {
                throw new ProbabilityTypeNotFoundException();
            }
        }
        return probabilityType;
    }

    protected ProbabilityTypeByDraw correctStatistics(ProbabilityTypeByDraw probabilityTypeByDraw) {
        BigDecimal sum = probabilityTypeByDraw.getNumberList().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        if (sum.compareTo(BigDecimal.ONE) > 0) {
            return correctGreaterOneStatistics(probabilityTypeByDraw, sum);
        } else if (sum.compareTo(BigDecimal.ONE) < 0) {
            return correctLowerOneStatistics(probabilityTypeByDraw, sum);
        }
        return probabilityTypeByDraw;
    }

    protected ProbabilityTypeByDraw correctGreaterOneStatistics(ProbabilityTypeByDraw probabilityTypeByDraw, final BigDecimal initialSum) {
        BigDecimal partialSum = new BigDecimal(initialSum.toString());
        BigDecimal unit = BigDecimal.ONE.movePointLeft(DIVIDE_SCALE);
        List<Integer> sortedIndex = getLowerToGreaterSortedIndex(probabilityTypeByDraw);
        while (partialSum.compareTo(BigDecimal.ONE) > 0) {
            BigDecimal difference = partialSum.subtract(BigDecimal.ONE);
            int numberOfValuesToChange = difference.divide(unit, DIVIDE_SCALE, ROUNDING_MODE).intValue();
            if (numberOfValuesToChange > 49) {
                numberOfValuesToChange = 49;
            }
            numberOfValuesToChange--;
            for (int indexesIndex = 0; indexesIndex <= numberOfValuesToChange; indexesIndex++) {
                int valuesIndex = sortedIndex.get(indexesIndex) + 1;

                probabilityTypeByDraw.setNumberProbability(valuesIndex, probabilityTypeByDraw.getNumberProbability(valuesIndex).subtract(unit));
            }
            partialSum = probabilityTypeByDraw.getNumberList().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return probabilityTypeByDraw;
    }

    protected ProbabilityTypeByDraw correctLowerOneStatistics(ProbabilityTypeByDraw probabilityTypeByDraw, final BigDecimal initialSum) {
        BigDecimal partialSum = new BigDecimal(initialSum.toString());
        BigDecimal unit = BigDecimal.ONE.movePointLeft(DIVIDE_SCALE);
        List<Integer> sortedIndex = getGreaterToLowerSortedIndex(probabilityTypeByDraw);
        while (partialSum.compareTo(BigDecimal.ONE) < 0) {
            BigDecimal difference = BigDecimal.ONE.subtract(partialSum);
            int numberOfValuesToChange = difference.divide(unit, DIVIDE_SCALE, ROUNDING_MODE).intValue();
            if (numberOfValuesToChange > 49) {
                numberOfValuesToChange = 49;
            }
            numberOfValuesToChange--;
            for (int indexesIndex = 0; indexesIndex <= numberOfValuesToChange; indexesIndex++) {
                int valuesIndex = sortedIndex.get(indexesIndex) + 1;

                probabilityTypeByDraw.setNumberProbability(valuesIndex, probabilityTypeByDraw.getNumberProbability(valuesIndex).add(unit));
            }
            partialSum = probabilityTypeByDraw.getNumberList().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return probabilityTypeByDraw;
    }

    protected List<Integer> getLowerToGreaterSortedIndex(ProbabilityTypeByDraw probabilityTypeByDraw) {
        return IntStream.range(0, probabilityTypeByDraw.getNumberList().size())  // Range generate indexes as int [0, 1, 2, ...]
                // Convert from int to Integer
                .boxed()
                .sorted(Comparator
                        // Sort bye the probability value
                        .comparing(probabilityTypeByDraw.getNumberList()::get)
                        // When the probability value is equal, then sort by index
                        .thenComparingInt(i -> i)
                )
                .toList();
    }

    protected List<Integer> getGreaterToLowerSortedIndex(ProbabilityTypeByDraw probabilityTypeByDraw) {
        List<Integer> result = IntStream.range(0, probabilityTypeByDraw.getNumberList().size())  // Range generate indexes as int [0, 1, 2, ...]
                // Convert from int to Integer
                .boxed()
                .sorted(Comparator
                        // Sort bye the probability value
                        .comparing(probabilityTypeByDraw.getNumberList()::get)
                        // When the probability value is equal, then sort by index
                        .thenComparingInt(i -> i)
                )
                .collect(Collectors.toList());
        Collections.reverse(result);

        return result;
    }
}
