package com.drawevaluateprobabilities.usecases;

import com.drawevaluateprobabilities.models.DrawList;
import com.drawevaluateprobabilities.models.ProbabilityType;
import com.drawevaluateprobabilities.models.ProbabilityTypeWeight;
import com.drawevaluateprobabilities.models.ProbabilityTypeCombination;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.DrawDatasourcePort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypePort;
import com.drawevaluateprobabilities.services.GenerateCombinationTypes;
import com.drawevaluateprobabilities.services.calculators.HigherWhenLastAppearanceIsOlder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CalculateProbabilities {
    private final DrawDatasourcePort drawDatasourcePort;
    private final NumberProbabilityListPort numberProbabilityListPort;
    private final ProbabilityTypePort probabilityTypePort;


    @Autowired
    private ApplicationContext context;


    private final HigherWhenLastAppearanceIsOlder calculatorHigherWhenLastAppearanceIsOlder;

    public void executeAll() {
        DrawList drawList = drawDatasourcePort.getDrawList();
        TDateInteger firstDrawDate = drawList.findFirstDrawDateFromDate(new TDateInteger(19950101)); // Descartamos los anteriores como irrelevantes
        combineCalculators(drawList,new TDateInteger(19950101));
    }

    private void combineCalculators(DrawList drawList, TDateInteger firstDrawDate) {
        getCombinationList();
    }

    private void getCombinationList() {
        List<ProbabilityType> probabilityTypeList = new ArrayList<>();
        probabilityTypeList.add(ProbabilityType.builder().code("0000").build());
        probabilityTypeList.addAll(probabilityTypePort.findAll());

        log.info("#######");
        for (ProbabilityType probabilityType : probabilityTypeList) {
            log.info(probabilityType.getCode());
        }
        log.info("#######");
        List<ProbabilityTypeCombination> probabilityTypeCombinationList =  GenerateCombinationTypes.getCombinationsFromTypeList(probabilityTypeList);
        int cont = 1;
        for (ProbabilityTypeCombination probabilityTypeCombination : probabilityTypeCombinationList) {
            String result = String.valueOf(cont) + ": ";
            List<ProbabilityTypeWeight> probabilityTypeWeightList = probabilityTypeCombination.getCombination();
            for (ProbabilityTypeWeight probabilityTypeWeight : probabilityTypeWeightList) {
                result += probabilityTypeWeight.getProbabilityType().getCode() + "-" + probabilityTypeWeight.getWeight() + " ";
            }
            log.info(result);

            cont++;
        }
        log.info("#######");

        // TODO: Implementar
    }
}
