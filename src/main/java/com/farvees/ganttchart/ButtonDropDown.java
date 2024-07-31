package com.farvees.ganttchart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonDropDown extends JButton {
    private Color btnBg = Color.decode("#009fff");
    private Color drpBg = Color.decode("#009fff");
    private Color hvrBg = Color.decode("#1750b0");
    private Color fg = Color.WHITE;
    private Image drop = new ImageIcon(ButtonDropDown.class.getResource("/images/down.png")).getImage();

    private int corner = 10;
    private int dropDownSize = 30;
    private String text;
    private boolean isClicked = false;
    private Point clickedPoint;

    private HashMap<String, Component> dropDownOptions = new HashMap<>();
    
    public ButtonDropDown() {
        text = "Add Issues";
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Container container = getParent();
                int x = (int)clickedPoint.getX();
                int range = getWidth() - dropDownSize;
                Point location = container.getLocationOnScreen();
                if (x > range) {
                    WindowPopUp pop = new WindowPopUp(
                        getX() + location.x, 
                        (getY() + getHeight()) + location.y + 2,
                        getOptions());
                    pop.Show();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isClicked = true;
                clickedPoint = e.getPoint();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isClicked = false;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                repaint();
            }
        };

        addMouseListener(adapter);
    }

    public void addOption(Component comp, String method) {
        this.dropDownOptions.put(method, comp);
    }

    public HashMap<String, Component> getOptions() {
        return dropDownOptions;
    }

    public void setBg(Color color) {
        this.btnBg = color;
    }

    public void setFg(Color color) {
        this.fg = color;
    }

    public void setHvrBg(Color color) {
        this.hvrBg = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int imageSize = 30;
        int dx = width - dropDownSize;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setColor(btnBg);

        if (isClicked) {
            if (clickedPoint.getX() < dx) {
                g2.setColor(hvrBg);
            } else { 
                g2.setColor(drpBg); 
            }
        } else { 
            g2.setColor(drpBg); 
        }

        g2.fillRoundRect(0, 0, dx, height, corner, corner);
        g2.fillRect(10, 0, dx - 10, height);
        
        if (isClicked) {
            if (clickedPoint.getX() > dx) {
                g2.setColor(hvrBg);
            } else { 
                g2.setColor(drpBg); 
            }
        } else { 
            g2.setColor(drpBg); 
        }

        g2.fillRoundRect(dx, 0, width - dx, height, corner, corner);
        g2.fillRect(dx, 0, 10, height);
        g2.setColor(fg);
        g2.fillRect(dx, 0, 2, height);

        g2.setFont(getFont());
        FontMetrics metrics = g2.getFontMetrics(getFont());
        int x;
        int y;
        int lrw = 30;
        int tw = metrics.stringWidth(text);
        int th = metrics.getHeight();
        int as = metrics.getAscent();
        LayoutManager layout = getLayout();
        if (tw > dx) {
            int newWidth = tw + (2 * lrw);
            if (layout  == null) {
                dx = newWidth - dropDownSize;
                setBounds(getX(), getY(), newWidth, getHeight());
            } else {
                setPreferredSize(new Dimension(newWidth, getHeight()));
            }
            x = (dx - tw) / 2;
        } else {
            x = (dx - tw) / 2;
        }
        y = ((height - th) / 2) + (int)(as * 0.9);
        g2.drawString(text, x, y);
        g2.drawImage(drop, dx, 0, imageSize, imageSize, null);
    }
}
