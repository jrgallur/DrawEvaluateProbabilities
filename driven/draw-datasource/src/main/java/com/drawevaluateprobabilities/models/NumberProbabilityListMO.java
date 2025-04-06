package com.drawevaluateprobabilities.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "number_probability_list")
public class NumberProbabilityListMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer date;

    private String values;

    private Short numberProbabilityTypeId;
}
