package com.drawevaluateprobabilities.mappers;

import com.drawevaluateprobabilities.models.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProbabilityTypeMapper {
    ProbabilityTypeMO toModel(ProbabilityType probabilityType);

    ProbabilityType toDomain(ProbabilityTypeMO probabilityTypeMO);
}