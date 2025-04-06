package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityTypePort;
import com.drawevaluateprobabilities.services.calculators.ifaces.IProbabilityValues;

import java.math.BigDecimal;

public class Random extends IProbabilityValues {
    public static final String TYPE_CODE = "0000";
    private NumberProbabilityType numberProbabilityType;

    protected Random(NumberProbabilityTypePort numberProbabilityTypePort) {
        super(numberProbabilityTypePort, null);
    }

    @Override
    protected String getTypeCode() {
        return TYPE_CODE;
    }

    @Override
    public NumberProbabilityList getNumberProbabilityListByDate(TDateInteger calculationDrawDate) {
        NumberProbabilityList numberProbabilityList = new NumberProbabilityList();
        numberProbabilityList.setCalculateDrawDate(calculationDrawDate);

        BigDecimal uniformValue = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(49), IProbabilityValues.DIVIDE_SCALE, IProbabilityValues.ROUNDING_MODE);

        for (int index = 0; index < 49; index++) {
            numberProbabilityList.setNumberProbability(index + 1, uniformValue);
        }

        return numberProbabilityList;
    }
}
