package com.drawevaluateprobabilities.models;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "probability_type")
public class ProbabilityTypeMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String code;
    private String description;
}
