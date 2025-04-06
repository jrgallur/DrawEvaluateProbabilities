package com.drawevaluateprobabilities.repositories;

import com.drawevaluateprobabilities.models.DrawMO;
import com.drawevaluateprobabilities.models.NumberProbabilityTypeMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NumberProbabilityTypeRepository extends JpaRepository<NumberProbabilityTypeMO, Short> {
    /**
     * Get the type list
     *
     * @return Whole type list
     */
    List<NumberProbabilityTypeMO> findAll();

    Optional<NumberProbabilityTypeMO> findByCode(String code);

}