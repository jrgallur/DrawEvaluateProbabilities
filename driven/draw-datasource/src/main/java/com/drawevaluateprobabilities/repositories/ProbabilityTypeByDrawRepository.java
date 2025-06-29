package com.drawevaluateprobabilities.repositories;

import com.drawevaluateprobabilities.models.ProbabilityTypeByDrawMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProbabilityTypeByDrawRepository extends JpaRepository<ProbabilityTypeByDrawMO, Short> {
    List<ProbabilityTypeByDrawMO> findByProbabilityTypeId(Short probabilityType);
    Optional<ProbabilityTypeByDrawMO> findByProbabilityTypeIdAndDate(Short probabilityType, Integer date);
    boolean existsByProbabilityTypeIdAndDate(Short probabilityType, Integer date);
}