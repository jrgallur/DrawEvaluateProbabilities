package com.drawevaluateprobabilities.models;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ProbabilityType {
    private Short id;
    private String code;
    private String description;

    public String toString() {
        return code;
    }
}
