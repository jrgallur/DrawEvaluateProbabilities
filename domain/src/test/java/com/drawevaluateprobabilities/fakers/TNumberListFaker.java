package com.drawevaluateprobabilities.fakers;

import com.drawevaluateprobabilities.models.types.TNumberList;

import java.util.Arrays;
import java.util.List;

public class TNumberListFaker {
    public static TNumberList getTNumberList() {
        List<Integer> initialList = Arrays.asList(1, 2, 3, 4, 5, 6);
        return new TNumberList(initialList);
    }
}