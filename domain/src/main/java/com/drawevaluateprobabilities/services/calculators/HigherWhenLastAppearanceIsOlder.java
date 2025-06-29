package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypePort;
import com.drawevaluateprobabilities.services.calculators.ifaces.ProbabilityType;
import org.springframework.stereotype.Service;

@Service
public class HigherWhenLastAppearanceIsOlder extends ProbabilityType {
    public static final String TYPE_CODE = "0001";
    public static final String TYPE_DESCRIPTION = "Higher probability when last appearance is older";

    public HigherWhenLastAppearanceIsOlder(ProbabilityTypePort probabilityTypePort, NumberProbabilityListPort numberProbabilityListPort) {
        super(probabilityTypePort, numberProbabilityListPort);
    }

    @Override
    public String getTypeCode() {
        return TYPE_CODE;
    }
}
