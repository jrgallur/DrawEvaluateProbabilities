package com.drawevaluateprobabilities.mappers;

import com.drawevaluateprobabilities.models.*;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NumberProbabilityTypeMapper {
    NumberProbabilityTypeMO toModel(NumberProbabilityType numberProbabilityType);

    NumberProbabilityType toDomain(NumberProbabilityTypeMO numberProbabilityTypeMO);
}