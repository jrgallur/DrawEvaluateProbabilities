package com.drawevaluateprobabilities.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "probability_type_combination")
public class ProbabilityTypeCombinationMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="code")
    private String code;

    @OneToMany(mappedBy="probabilityTypeCombinationId")
    private List<ProbabilityTypeWeightMO> probabilityTypeWeightMOList;
}
