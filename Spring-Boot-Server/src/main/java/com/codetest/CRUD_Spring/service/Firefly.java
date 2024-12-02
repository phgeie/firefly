package com.codetest.CRUD_Spring.service;
import java.util.*;
public class Firefly extends Thread {
    private double phase;  // Phase θ
    private final double frequency;  // Eigenfrequenz ω
    private final double coupling;   // Kopplungsstärke K
    private List<Firefly> neighbors; // Nachbarn auf dem Torus
    private double total_phase = 2.0 * Math.PI;
    private int threadSleepTime;

    public Firefly(double coupling, int threadSleepTime) {
        this.threadSleepTime = threadSleepTime;
        this.coupling = coupling;
        this.phase = Math.random() * total_phase;
        this.frequency = Math.random() * total_phase * threadSleepTime/1000.0;
    }

    public void setNeighbors(List<Firefly> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public void run() {
        while (true) {
            updatePhase();
            try {
                Thread.sleep(threadSleepTime); // Simulationstakt
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void updatePhase() {
        double sumSin = 0;
        for (Firefly neighbor : neighbors) {
            sumSin += Math.sin(neighbor.phase - phase);
        }
        phase += frequency + coupling * sumSin/neighbors.size();
        if (phase >= total_phase) {
            phase -= total_phase;
        }
    }

    public double getPhase() {
        return phase;
    }
}