package com.drawevaluateprobabilities.adapters;

import com.drawevaluateprobabilities.mappers.NumberProbabilityListMapper;
import com.drawevaluateprobabilities.models.NumberProbabilityList;
import com.drawevaluateprobabilities.models.NumberProbabilityListMO;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.NumberProbabilityListPort;
import com.drawevaluateprobabilities.repositories.NumberProbabilityListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class NumberProbabilityListAdapter implements NumberProbabilityListPort {
    private final NumberProbabilityListRepository repository;
    private final NumberProbabilityListMapper mapper;

    @Override
    public void upsert(NumberProbabilityList numberProbabilityList) {
        NumberProbabilityListMO numberProbabilityListMO = mapper.toModel(numberProbabilityList);
        Optional<NumberProbabilityListMO> numberProbabilityListMOList = repository.findByNumberProbabilityTypeIdAndDate(numberProbabilityListMO.getNumberProbabilityTypeId(), numberProbabilityListMO.getDate());
        if (numberProbabilityListMOList.isPresent()) {
            numberProbabilityListMOList.get().setValues(numberProbabilityListMO.getValues());
            repository.save(numberProbabilityListMOList.get());
        } else {
            repository.save(numberProbabilityListMO);
        }
    }

    @Override
    public boolean existsByNumberProbabilityTypeAndDrawDate(Short numberProbabilityType, TDateInteger drawdate) {
        return repository.existsByNumberProbabilityTypeIdAndDate(numberProbabilityType, drawdate.toInteger());
    }

    @Override
    public NumberProbabilityList findByTypeAndDrawDate(Short numberProbabilityTypeId, TDateInteger drawDate) {
        return mapper.toDomain(repository.findByNumberProbabilityTypeIdAndDate(numberProbabilityTypeId, drawDate.toInteger()).orElse(null));
    }
}
