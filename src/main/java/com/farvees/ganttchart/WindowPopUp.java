package com.farvees.ganttchart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class WindowPopUp extends JFrame {
    private HashMap<String, Component> options;
    private JPanel optionsContainer;
    private int width;
    private int height;

    public WindowPopUp(int x, int y, HashMap<String, Component> options) {
        this.options = options;

        for (String key: options.keySet()) {
            width = options.get(key).getWidth();
            height = options.get(key).getHeight();
            break;
        }

        setUndecorated(true);
        setSize(new Dimension(width, (height + 5) * options.size() + 5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(x, y);
        
        optionsContainer = new JPanel();
        optionsContainer.setLayout(null);
        optionsContainer.setBackground(Color.WHITE);
        optionsContainer.setBorder(new LineBorder(getForeground()));

        add(optionsContainer);
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public void Show() {
        WindowFocusListener focusListener = new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                
            }
        };

        addWindowFocusListener(focusListener);

        int i = 0;
        for (String key: options.keySet()) {
            OptButton btn = (OptButton) options.get(key);
            btn.setMethod(key);
            if (i == 0)
                btn.setBounds(0, (i * btn.getHeight()) + 5, btn.getWidth(), btn.getHeight());
            else            
                btn.setBounds(0, (i * btn.getHeight()) + ((i + 1) * 5), btn.getWidth(), btn.getHeight());    
            optionsContainer.add(btn);
            i++;
        }
        setVisible(true);
    }
}
