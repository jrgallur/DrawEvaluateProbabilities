package com.drawevaluateprobabilities.repositories;

import com.drawevaluateprobabilities.models.NumberProbabilityListMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NumberProbabilityListRepository extends JpaRepository<NumberProbabilityListMO, Short> {
    List<NumberProbabilityListMO> findByProbabilityTypeId(Short probabilityType);
    Optional<NumberProbabilityListMO> findByProbabilityTypeIdAndDate(Short probabilityType, Integer date);
    boolean existsByProbabilityTypeIdAndDate(Short probabilityType, Integer date);
}