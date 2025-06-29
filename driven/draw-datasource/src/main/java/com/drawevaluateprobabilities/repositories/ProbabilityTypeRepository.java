package com.drawevaluateprobabilities.repositories;

import com.drawevaluateprobabilities.models.ProbabilityTypeMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProbabilityTypeRepository extends JpaRepository<ProbabilityTypeMO, Short> {
    /**
     * Get the type list
     *
     * @return Whole type list
     */
    List<ProbabilityTypeMO> findAll();

    Optional<ProbabilityTypeMO> findByCode(String code);

}