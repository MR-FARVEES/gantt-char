package com.farvees.ganttchart;

import javax.swing.*;
import java.awt.*;

public class ScrollBar extends JScrollBar {
    Color SCROLL_BAR_BG = Color.WHITE;
    Color SCROLL_BAR_THUMB_BG = new Color(48, 144, 216);
    public ScrollBar() {
        setUI(new ScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(SCROLL_BAR_THUMB_BG);
        setBackground(SCROLL_BAR_BG);
    }
}
