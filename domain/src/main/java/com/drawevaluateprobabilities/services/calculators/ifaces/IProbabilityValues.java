package com.drawevaluateprobabilities.services.calculators.ifaces;

import com.drawevaluateprobabilities.exceptions.NumberProbabilityTypeNotFoundException;
import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityTypePort;

import java.math.RoundingMode;

public abstract class IProbabilityValues {
    public static final int DIVIDE_SCALE = 10;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private NumberProbabilityType numberProbabilityType;

    private final NumberProbabilityTypePort numberProbabilityTypePort;
    private final NumberProbabilityListPort numberProbabilityListPort;

    protected IProbabilityValues(NumberProbabilityTypePort numberProbabilityTypePort, NumberProbabilityListPort numberProbabilityListPort) {
        this.numberProbabilityTypePort = numberProbabilityTypePort;
        this.numberProbabilityListPort = numberProbabilityListPort;
    }

    protected abstract String getTypeCode();

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
}
