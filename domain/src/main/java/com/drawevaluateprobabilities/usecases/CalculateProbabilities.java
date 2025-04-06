package com.drawevaluateprobabilities.usecases;

import com.drawevaluateprobabilities.models.DrawList;
import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.DrawDatasourcePort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.services.calculators.HigherWhenLastAppearanceIsOlder;
import com.drawevaluateprobabilities.services.calculators.ifaces.IProbabilityValues;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CalculateProbabilities {
    private final DrawDatasourcePort drawDatasourcePort;
    private final NumberProbabilityListPort numberProbabilityListPort;


    private final HigherWhenLastAppearanceIsOlder calculatorHigherWhenLastAppearanceIsOlder;

    public void executeAll() {
        DrawList drawList = drawDatasourcePort.getDrawList();
        TDateInteger firstDrawDate = drawList.findFirstDrawDateFromDate(new TDateInteger(19950101)); // Descartamos los anteriores como irrelevantes
    }

    private void combineCalculators(DrawList drawList, TDateInteger firstDrawDate, List<IProbabilityValues> probabilityValuesList) {
        getCombinationList(probabilityValuesList);
    }

    private void getCombinationList(List<IProbabilityValues> probabilityValuesList) {
        // TODO: Implementar
    }
}
