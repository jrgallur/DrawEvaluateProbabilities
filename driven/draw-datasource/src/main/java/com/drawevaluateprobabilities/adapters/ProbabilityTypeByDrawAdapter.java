package com.drawevaluateprobabilities.adapters;

import com.drawevaluateprobabilities.mappers.ProbabilityTypeByDrawMapper;
import com.drawevaluateprobabilities.models.ProbabilityTypeByDraw;
import com.drawevaluateprobabilities.models.ProbabilityTypeByDrawMO;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.ports.driven.ProbabilityTypeByDrawPort;
import com.drawevaluateprobabilities.repositories.ProbabilityTypeByDrawRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class ProbabilityTypeByDrawAdapter implements ProbabilityTypeByDrawPort {
    private final ProbabilityTypeByDrawRepository repository;
    private final ProbabilityTypeByDrawMapper mapper;

    @Override
    public void upsert(ProbabilityTypeByDraw probabilityTypeByDraw) {
        ProbabilityTypeByDrawMO probabilityTypeByDrawMO = mapper.toModel(probabilityTypeByDraw);
        Optional<ProbabilityTypeByDrawMO> probabilityTypeByDrawMOOptional = repository.findByProbabilityTypeIdAndDate(probabilityTypeByDrawMO.getProbabilityTypeId(), probabilityTypeByDrawMO.getDate());
        if (probabilityTypeByDrawMOOptional.isPresent()) {
            probabilityTypeByDrawMOOptional.get().setValues(probabilityTypeByDrawMO.getValues());
            repository.save(probabilityTypeByDrawMOOptional.get());
        } else {
            repository.save(probabilityTypeByDrawMO);
        }
    }

    @Override
    public boolean existsByProbabilityTypeAndDrawDate(Short probabilityTypeId, TDateInteger drawdate) {
        return repository.existsByProbabilityTypeIdAndDate(probabilityTypeId, drawdate.toInteger());
    }

    @Override
    public ProbabilityTypeByDraw findByProbabilityTypeAndDrawDate(Short probabilityTypeId, TDateInteger drawDate) {
        return mapper.toDomain(repository.findByProbabilityTypeIdAndDate(probabilityTypeId, drawDate.toInteger()).orElse(null));
    }
}
