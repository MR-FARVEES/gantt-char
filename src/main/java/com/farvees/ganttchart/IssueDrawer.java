package com.farvees.ganttchart;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class IssueDrawer extends JPanel {
    private boolean isCollapsed;
    private int imageSize = 18;
    public static int MIN_WIDTH;
    public static int MAX_WIDTH;
    private Timer drawerTimer;
    
    private Image expand = new ImageIcon(GanttChart.class.getResource("/images/right.png")).getImage();
    private Image collapse = new ImageIcon(GanttChart.class.getResource("/images/left.png")).getImage();
    private boolean issuesShown = false;
    private static JPanel issueContainer = new JPanel();

    IssueDrawer(int min, int max) {
        MIN_WIDTH = min;
        MAX_WIDTH = max;
        isCollapsed = false;
        setPreferredSize(new Dimension(MIN_WIDTH, 700));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBorder(new EmptyBorder(10, 10, 30, 10));
        setBackground(Color.WHITE);
        init();
        setVisible(true);
    }

    private void init() {

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isInDragMargin(e)) {
                    if (e.getX() > getWidth() - (int)(1.3 * imageSize) 
                        && e.getX() < getWidth()
                        && e.getY() > imageSize
                        && e.getY() < imageSize * 2) {
                        if (isCollapsed) {
                            isCollapsed = false;
                            repaint();
                            drawerTimer.start();
                        } else {
                            isCollapsed = true;
                            if (!issuesShown) {
                                showIssues();
                                issuesShown = true;
                            }
                            repaint();
                            drawerTimer.start();
                        }
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getX() > getWidth() - (int)(1.3 * imageSize) 
                        && e.getX() < getWidth()
                        && e.getY() > imageSize
                        && e.getY() < imageSize * 2) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        drawerTimer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCollapsed) {
                    if (getWidth() < MAX_WIDTH) { 
                        setPreferredSize(new Dimension(getWidth() + (MAX_WIDTH - getWidth()) / 4, getHeight()));
                        revalidate();
                        repaint();
                    } else {
                        drawerTimer.stop();
                    }
                } else {
                    if (getWidth() > MIN_WIDTH) { 
                        setPreferredSize(new Dimension(getWidth() - (MAX_WIDTH - MIN_WIDTH) / 4, getHeight()));
                        revalidate();
                        repaint();
                    } else {
                        drawerTimer.stop();
                    }
                }
            }
        });
        drawerTimer.start();
    }

    public boolean isInDragMargin(MouseEvent e) {
        int width = getWidth();
        return e.getX() >= width - imageSize;
    }

    public void showIssues() {
        issueContainer = new JPanel();
        issueContainer.setBackground(Color.WHITE);
        issueContainer.setPreferredSize(new Dimension(300, 400));
        issueContainer.setLayout(null);
        JScrollPane issueScrollPane = new JScrollPane(issueContainer);
        issueScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        issueScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        issueScrollPane.setBounds(IssueDrawer.MIN_WIDTH, 70,
            IssueDrawer.MAX_WIDTH - IssueDrawer.MIN_WIDTH, getHeight() - 70);
        issueScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        issueScrollPane.setVerticalScrollBar(new ScrollBar());
        add(issueScrollPane);

        update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int x = width - (int)(1.3 * imageSize);
        int y = imageSize;

        setBackground(Color.LIGHT_GRAY);
        super.paintComponent(g);       
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        if (!isCollapsed) {
            g2.drawImage(expand, x - 3, y, imageSize, imageSize, null);
            g2.drawImage(expand, x + 3, y, imageSize, imageSize, null);
        } else {
            g2.drawImage(collapse, x - 3, y, imageSize, imageSize, null);
            g2.drawImage(collapse, x + 3, y, imageSize, imageSize, null);
        }
    }

    public static void update() {
        JLabel label = new JLabel("asdf");
        label.setBounds(10, 10, 100, 30);
        issueContainer.add(label);
    }

    public void addNewIssue() {
        System.out.println("NEW ISSUE");
    }

    public void addExistsIssue() {
        System.out.println("EXISTS ISSUE");
    }
}