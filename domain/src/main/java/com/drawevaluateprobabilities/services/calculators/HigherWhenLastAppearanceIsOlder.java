package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityTypePort;
import com.drawevaluateprobabilities.services.calculators.ifaces.IProbabilityValues;
import org.springframework.stereotype.Service;

@Service
public class HigherWhenLastAppearanceIsOlder extends IProbabilityValues {
    public static final String TYPE_CODE = "0001";
    public static final String TYPE_DESCRIPTION = "Higher probability when last appearance is older";

    public HigherWhenLastAppearanceIsOlder(NumberProbabilityTypePort numberProbabilityTypePort, NumberProbabilityListPort numberProbabilityListPort) {
        super(numberProbabilityTypePort, numberProbabilityListPort);
    }

    @Override
    protected String getTypeCode() {
        return TYPE_CODE;
    }
}
