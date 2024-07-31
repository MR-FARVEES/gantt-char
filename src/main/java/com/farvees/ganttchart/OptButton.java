package com.farvees.ganttchart;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;

public class OptButton extends JButton {
    private String text;
    private String methodName;
    private Color nBg = Color.WHITE;
    private Color hBg = Color.LIGHT_GRAY;
    private Color cBg = Color.GRAY;
    private Color nFg = Color.BLACK;
    private Color hFg = Color.WHITE;
    private boolean isHover = false;
    private boolean isClicked = false;

    OptButton(String text) {
        this.text = text;
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHover = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isClicked = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isClicked = false;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                GanttChart gantt = new GanttChart();
                Class<?> clazz = gantt.getClass();
                try {
                    clazz.getMethod(methodName).invoke(gantt);
                } catch (IllegalAccessException     ex) {
                    ex.printStackTrace();
                } catch (IllegalArgumentException   ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException  ex) {
                    ex.printStackTrace();
                } catch (NoSuchMethodException      ex) {
                    ex.printStackTrace();
                } catch (SecurityException          ex) {
                    ex.printStackTrace();
                }
            }
        };

        addMouseListener(adapter);
    }

    public void setMethod(String text) {
        this.methodName = text;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(getFont());
        FontMetrics metrics = g2.getFontMetrics();
        int x = 20;
        int y = (getHeight() - metrics.getHeight()) / 2 + (int)(metrics.getAscent() * 0.9);
        if (isHover) {
            if (isClicked) {
                g2.setColor(cBg);
            } else {
                g2.setColor(hBg);
            }
        } else {
            g2.setColor(nBg);
        }
        g2.fillRoundRect(5, 0, getWidth() - 10, getHeight(), 10, 10);
        if (isHover) {
            g2.setColor(hFg);
        } else {
            g2.setColor(nFg);
        }
        g2.drawString(text, x, y);
    }
}
