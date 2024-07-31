package com.farvees.ganttchart;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(1100, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GanttChart ganttChart = new GanttChart();
        ganttChart.setPreferredSize(frame.getSize());
        frame.add(ganttChart);

        frame.pack();

        frame.setVisible(true);
    }
}