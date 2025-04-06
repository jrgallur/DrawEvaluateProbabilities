package com.drawevaluateprobabilities.adapters;

import com.drawevaluateprobabilities.mappers.NumberProbabilityTypeMapper;
import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.NumberProbabilityType;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityTypePort;
import com.drawevaluateprobabilities.repositories.NumberProbabilityTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class NumberProbabilityTypeAdapter implements NumberProbabilityTypePort {
    private final NumberProbabilityTypeRepository repository;
    private final NumberProbabilityTypeMapper mapper;

    @Override
    public void upsert(NumberProbabilityType numberProbabilityType) {
        repository.save(mapper.toModel(numberProbabilityType));
    }

    @Override
    public NumberProbabilityType getByCode(String code) {
        return mapper.toDomain(repository.findByCode(code).orElse(null));
    }
}
