package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private int[] availableTimes;
    private boolean[] volunteerNeeded;
    private Animal[] animals;
    private ArrayList<ScheduleItem>[] schedule;
}
