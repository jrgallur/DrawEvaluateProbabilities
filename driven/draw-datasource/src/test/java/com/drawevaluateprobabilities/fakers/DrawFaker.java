package com.drawevaluateprobabilities.fakers;

import com.drawevaluateprobabilities.models.Draw;
import com.drawevaluateprobabilities.models.types.TDateInteger;
import com.drawevaluateprobabilities.models.types.TNumberList;

public class DrawFaker {
    public static Draw getDraw() {
        TDateInteger date = TDateInteger.valueOfUnknownFormat("20240316");
        TNumberList numbers = TNumberListFaker.getTNumberList();
        byte complementary = 10;

        return new Draw(date, numbers, complementary);
    }
}