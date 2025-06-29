package com.drawevaluateprobabilities.services.calculators;

import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.ProbabilityType;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypePort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Random extends com.drawevaluateprobabilities.services.calculators.ifaces.ProbabilityType {
    public static final String TYPE_CODE = "0000";
    private ProbabilityType probabilityType;

    private NumberProbabilityList numberProbabilityList;

    protected Random(ProbabilityTypePort probabilityTypePort) {
        super(probabilityTypePort, null);
    }

    @Override
    public String getTypeCode() {
        return TYPE_CODE;
    }

    @Override
    public NumberProbabilityList getNumberProbabilityListByDate(TDateInteger calculationDrawDate) {
        if (numberProbabilityList==null) {
            numberProbabilityList = new NumberProbabilityList();
            numberProbabilityList.setCalculateDrawDate(calculationDrawDate);

            BigDecimal uniformValue = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(49), com.drawevaluateprobabilities.services.calculators.ifaces.ProbabilityType.DIVIDE_SCALE, com.drawevaluateprobabilities.services.calculators.ifaces.ProbabilityType.ROUNDING_MODE);

            for (int index = 0; index < 49; index++) {
                numberProbabilityList.setNumberProbability(index + 1, uniformValue);
            }

            numberProbabilityList = correctStatistics(numberProbabilityList);
        }
        numberProbabilityList.setCalculateDrawDate(calculationDrawDate);

        return numberProbabilityList;
    }
}
