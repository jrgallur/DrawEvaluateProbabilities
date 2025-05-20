package com.drawevaluateprobabilities.usecases;

import com.drawevaluateprobabilities.models.DrawList;
import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.models.ProbabilityTypeWeight;
import com.drawevaluateprobabilities.models.ProbabilityTypeWeightCombination;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.DrawDatasourcePort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityTypePort;
import com.drawevaluateprobabilities.services.GenerateProbabilityTypesCombination;
import com.drawevaluateprobabilities.services.calculators.HigherWhenLastAppearanceIsOlder;
import com.drawevaluateprobabilities.services.calculators.ifaces.ProbabilityType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.drawevaluateprobabilities.services.GenerateProbabilityTypesCombination.getCombinations;

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
        List<ProbabilityTypeWeightCombination> probabilityTypeWeightCombinationList =  GenerateProbabilityTypesCombination.getCombinations(numberProbabilityTypeList);
        int cont = 1;
        for (ProbabilityTypeWeightCombination probabilityTypeWeightCombination : probabilityTypeWeightCombinationList) {
            String result = String.valueOf(cont) + ": ";
            List<ProbabilityTypeWeight> probabilityTypeWeightList = probabilityTypeWeightCombination.getCombination();
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
