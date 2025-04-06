package com.drawevaluateprobabilities.models;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class NumberProbabilityType {
    private Short id;
    private String code;
    private String description;

    public String toString() {
        return code;
    }
}
