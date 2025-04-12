package com.drawevaluateprobabilities.usecases;

import com.drawevaluateprobabilities.models.DrawList;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.DrawDatasourcePort;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.services.calculators.HigherWhenLastAppearanceIsOlder;
import com.drawevaluateprobabilities.services.calculators.ifaces.ProbabilityType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CalculateProbabilities {
    private final DrawDatasourcePort drawDatasourcePort;
    private final NumberProbabilityListPort numberProbabilityListPort;

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
        Map<String, ProbabilityType> beansOfType = context.getBeansOfType(ProbabilityType.class);

        List<ProbabilityType> lista = beansOfType.values().stream().collect(Collectors.toList());
        log.info("#######");
        for (ProbabilityType clase : lista) {
            log.info(clase.getTypeCode());
        }
        // TODO: Implementar
    }
}
