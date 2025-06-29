package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.ports.driven.ProbabilityTypeByDrawPort;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypePort;
import com.drawevaluateprobabilities.services.calculators.ifaces.ProbabilityTypeIface;
import org.springframework.stereotype.Service;

@Service
public class HigherWhenLastAppearanceIsOlder extends ProbabilityTypeIface {
    public static final String TYPE_CODE = "0001";
    public static final String TYPE_DESCRIPTION = "Higher probability when last appearance is older";

    public HigherWhenLastAppearanceIsOlder(ProbabilityTypePort probabilityTypePort, ProbabilityTypeByDrawPort probabilityTypeByDrawPort) {
        super(probabilityTypePort, probabilityTypeByDrawPort);
    }

    @Override
    public String getTypeCode() {
        return TYPE_CODE;
    }
}
