package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> favorites = new ArrayList<>();

        for(Neighbour n : neighbours) {
            if(n.getFavorite()) favorites.add(n);
        }

        return favorites;
    }

    @Override
    public Boolean changeStatus(Neighbour neighbour) {

        int position = neighbours.indexOf(neighbour);
        neighbours.get(position).setFavorite(!neighbours.get(position).getFavorite());

        return neighbours.get(position).getFavorite();
    }
}
