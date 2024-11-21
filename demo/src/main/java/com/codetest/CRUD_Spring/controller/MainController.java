package com.codetest.CRUD_Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.codetest.CRUD_Spring.service.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController // This means that this class is a Controller
@RequestMapping(path="/data") // This means URL's start with /data (after Application path)
public class MainController {
    private int row;
    private int column;
    private Torus torus;

    @GetMapping(path="/start")
    public int start(@RequestParam int row, @RequestParam int column, @RequestParam double coupling, @RequestParam int threadSleepTime) {
        this.row = row;
        this.column = column;
        System.out.println("Fireflies woke up!");
        torus = new Torus(row, column,coupling, threadSleepTime);
        Firefly[][] ff = torus.getGrid();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                ff[i][j].start();
            }
        }
        return 0;
    }

    @GetMapping(path="/stop")
    public int stop() {
        System.out.println("Fireflies went to sleep!");
        if (torus != null) {
            Firefly[][] ff = torus.getGrid();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    ff[i][j].interrupt();
                }
            }
            return 0;
        }else return 1;
    }


    @GetMapping(path="/getFireflies")
    public double[][] getFireflies(){
        double[][] phases = new double[row][column];
        if (torus != null) {
            Firefly[][] ff = torus.getGrid();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    phases[i][j] = ff[i][j].getPhase();
                }
            }
        }
        return phases;
    }


}