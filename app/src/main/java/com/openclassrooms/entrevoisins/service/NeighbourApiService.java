package com.openclassrooms.entrevoisins.service;

import android.content.Context;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavorites();

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    Boolean changeStatus(Neighbour neighbour);
}
