package com.drawevaluateprobabilities.usecases;

import com.drawevaluateprobabilities.models.DrawList;
import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.models.ProbabilityTypeWeight;
import com.drawevaluateprobabilities.models.ProbabilityTypeCombination;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.DrawDatasourcePort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityTypePort;
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
    private final NumberProbabilityTypePort numberProbabilityTypePort;


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
        List<NumberProbabilityType> numberProbabilityTypeList = new ArrayList<>();
        numberProbabilityTypeList.add(NumberProbabilityType.builder().code("0000").build());
        numberProbabilityTypeList.addAll(numberProbabilityTypePort.findAll());

        log.info("#######");
        for (NumberProbabilityType numberProbabilityType : numberProbabilityTypeList) {
            log.info(numberProbabilityType.getCode());
        }
        log.info("#######");
        List<ProbabilityTypeCombination> probabilityTypeCombinationList =  GenerateCombinationTypes.getCombinationsFromTypeList(numberProbabilityTypeList);
        int cont = 1;
        for (ProbabilityTypeCombination probabilityTypeCombination : probabilityTypeCombinationList) {
            String result = String.valueOf(cont) + ": ";
            List<ProbabilityTypeWeight> probabilityTypeWeightList = probabilityTypeCombination.getCombination();
            for (ProbabilityTypeWeight probabilityTypeWeight : probabilityTypeWeightList) {
                result += probabilityTypeWeight.getNumberProbabilityType().getCode() + "-" + probabilityTypeWeight.getWeight() + " ";
            }
            log.info(result);

            cont++;
        }
        log.info("#######");

        // TODO: Implementar
    }
}
