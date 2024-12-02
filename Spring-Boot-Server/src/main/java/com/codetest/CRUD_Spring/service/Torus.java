package com.codetest.CRUD_Spring.service;
import java.util.*;

public class Torus {
    private final int row;
    private final int column;
    private Firefly[][] grid;

    public Torus(int row, int column, double coupling, int threadSleepTime) {
        this.row = row;
        this.column = column;
        grid = new Firefly[row][column];
        initializeFireflies(coupling, threadSleepTime);
    }

    private void initializeFireflies(double coupling, int threadSleepTime) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = new Firefly(coupling, threadSleepTime);
            }
        }
        setNeighbors();
    }

    private void setNeighbors() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                List<Firefly> neighbors = new ArrayList<>();
                neighbors.add(grid[(i - 1 + row) % row][j]); // oben
                neighbors.add(grid[(i + 1) % row][j]);       // unten
                neighbors.add(grid[i][(j - 1 + column) % column]); // links
                neighbors.add(grid[i][(j + 1) % column]);       // rechts
                grid[i][j].setNeighbors(neighbors);
            }
        }
    }

    public Firefly[][] getGrid() {
        return grid;
    }
}
