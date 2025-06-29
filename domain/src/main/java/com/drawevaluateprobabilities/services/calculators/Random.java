package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.models.ProbabilityTypeByDraw;
import com.drawevaluateprobabilities.models.ProbabilityType;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypePort;
import com.drawevaluateprobabilities.services.calculators.ifaces.ProbabilityTypeIface;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Random extends ProbabilityTypeIface {
    public static final String TYPE_CODE = "0000";
    private ProbabilityType probabilityType;

    private ProbabilityTypeByDraw probabilityTypeByDraw;

    protected Random(ProbabilityTypePort probabilityTypePort) {
        super(probabilityTypePort, null);
    }

    @Override
    public String getTypeCode() {
        return TYPE_CODE;
    }

    @Override
    public ProbabilityTypeByDraw getProbabilityTypeByDrawByDate(TDateInteger calculationDrawDate) {
        if (probabilityTypeByDraw ==null) {
            probabilityTypeByDraw = new ProbabilityTypeByDraw();
            probabilityTypeByDraw.setCalculateDrawDate(calculationDrawDate);

            BigDecimal uniformValue = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(49), ProbabilityTypeIface.DIVIDE_SCALE, ProbabilityTypeIface.ROUNDING_MODE);

            for (int index = 0; index < 49; index++) {
                probabilityTypeByDraw.setNumberProbability(index + 1, uniformValue);
            }

            probabilityTypeByDraw = correctStatistics(probabilityTypeByDraw);
        }
        probabilityTypeByDraw.setCalculateDrawDate(calculationDrawDate);

        return probabilityTypeByDraw;
    }
}
