package com.codetest.CRUD_Spring.service;
import java.util.*;

public class Torus {
    private final int size;
    private Firefly[][] grid;

    public Torus(int size, double coupling, int threadSleepTime) {
        this.size = size;
        grid = new Firefly[size][size];
        initializeFireflies(coupling, threadSleepTime);
    }

    private void initializeFireflies(double coupling, int threadSleepTime) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Firefly(coupling, threadSleepTime);
            }
        }
        setNeighbors();
    }

    private void setNeighbors() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                List<Firefly> neighbors = new ArrayList<>();
                neighbors.add(grid[(i - 1 + size) % size][j]); // oben
                neighbors.add(grid[(i + 1) % size][j]);       // unten
                neighbors.add(grid[i][(j - 1 + size) % size]); // links
                neighbors.add(grid[i][(j + 1) % size]);       // rechts
                grid[i][j].setNeighbors(neighbors);
            }
        }
    }

    public Firefly[][] getGrid() {
        return grid;
    }
}
