package com.drawevaluateprobabilities.mappers;

import com.drawevaluateprobabilities.models.*;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProbabilityTypeByDrawMapper {

    default ProbabilityTypeByDrawMO toModel(ProbabilityTypeByDraw probabilityTypeByDraw) {
        return ProbabilityTypeByDrawMO.builder()
                .date(probabilityTypeByDraw.getCalculateDrawDate().toInteger())
                .probabilityTypeId(probabilityTypeByDraw.getType().getId())
                .values(numberListToString(probabilityTypeByDraw.getNumberList()))
                .build();
    }

    default String numberListToString(List<BigDecimal> numberList) {
        StringBuilder result = new StringBuilder();
        for (int cont=0; cont<49; cont++) {
            result.append(numberList.get(cont)).append("#");
        }
        return result.toString();
    }

    default ProbabilityTypeByDraw toDomain(ProbabilityTypeByDrawMO probabilityTypeByDrawMO) {
        ProbabilityTypeByDraw probabilityTypeByDraw = new ProbabilityTypeByDraw();
        probabilityTypeByDraw.setType(ProbabilityType.builder().id(probabilityTypeByDrawMO.getProbabilityTypeId()).build());
        probabilityTypeByDraw.setCalculateDrawDate(new TDateInteger(probabilityTypeByDrawMO.getDate()));
        probabilityTypeByDraw.setNumberList(stringToNumberList(probabilityTypeByDrawMO.getValues()));

        return probabilityTypeByDraw;
    }

    default List<BigDecimal> stringToNumberList(String numberString) {
        return Arrays.stream(numberString.split("#")).map(BigDecimal::new).toList();
    }
}