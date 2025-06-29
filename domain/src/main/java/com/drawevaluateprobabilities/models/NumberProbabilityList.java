package com.drawevaluateprobabilities.models;

import com.drawevaluateprobabilities.models.types.TDateInteger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
@Getter
@Setter
public class NumberProbabilityList {
    private List<BigDecimal> numberList;
    private TDateInteger calculateDrawDate;
    private ProbabilityType type;

    public NumberProbabilityList() {
        numberList = new ArrayList<>(Collections.nCopies(49, new BigDecimal(0)));
    }

    public BigDecimal getNumberProbability(int number) {
        return numberList.get(number - 1);
    }

    public void setNumberProbability(int number, BigDecimal value) {
        numberList.set(number - 1, value);
    }
}
