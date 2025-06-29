package com.drawevaluateprobabilities.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "probability_type_by_draw")
public class ProbabilityTypeByDrawMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer date;

    private String values;

    private Short probabilityTypeId;
}
