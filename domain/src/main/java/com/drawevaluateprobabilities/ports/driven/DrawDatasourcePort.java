package com.drawevaluateprobabilities.ports.driven;

import com.drawevaluateprobabilities.models.Draw;
import com.drawevaluateprobabilities.models.DrawList;

import java.util.Optional;

public interface DrawDatasourcePort {
    /**
     * Get the draw list
     *
     * @return Draw list
     */
    DrawList getDrawList();

    /**
     * Insert a draw in the database
     *
     * @param draw Draw to insert
     */
    void insertDraw(Draw draw);

    /**
     * Get a Draw by date
     * @param date Date of the draw
     * @return the Draw, if exists in that date
     */
    Optional<Draw> findByDate(Integer date);
}