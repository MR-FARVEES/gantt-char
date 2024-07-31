package com.farvees.ganttchart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class CharPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.decode("#dfdfdf"));
        g2.drawLine(0, 0, width, 0);
        g2.drawLine(0, 0, 0, height);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.decode("#bbbbbb"));
        g2.drawLine(0, 0, width, 0);
        g2.drawLine(0, 0, 0, height);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.decode("#909090"));
        g2.drawLine(0, 0, width, 0);
        g2.drawLine(0, 0, 0, height);
    }
}
