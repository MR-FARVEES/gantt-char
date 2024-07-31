package com.farvees.ganttchart;

import java.awt.*;
import java.time.*;
import java.time.format.*;

import javax.swing.*;

public class GanttChart extends JPanel {
    private IssueDrawer issuePane;
    private JPanel chartPane;
    private JPanel header;
    private String fname = "Barlow";

    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate = LocalDateTime.now();
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy");

    private JLabel task = new JLabel("Task Name Should be here");
    private JLabel start = new JLabel("Start date : ");
    private JLabel end = new JLabel("End date : ");
    private JLabel duration = new JLabel("Duration : ");
    private JLabel start_value = new JLabel(startDate.format(dateFormat));
    private JLabel end_value = new JLabel(endDate.format(dateFormat));
    private JLabel duration_value = new JLabel("Some duration");

    private ButtonDropDown addIssue = new ButtonDropDown();
    public static IssueList<Issue> issues = new IssueList<>();

    public GanttChart() {
        setLayout(new BorderLayout(0, 0));
        initComponents();
    }

    private void initComponents() {
        for (int i = 0; i < 10; i++) {
            issues.addNewIssue(new Issue("~DEV ID", "THIS IS AN ISSUE " + i,
                LocalDate.now(), LocalDate.now()));
        }

        header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setLayout(null);
        header.setPreferredSize(new Dimension(getWidth(), 100));

        task.setBounds(30, 20, 400, 40);
        task.setFont(new Font(fname, Font.BOLD, 22));
        add(task);
        
        start.setBounds(30, 55, 120, 30);
        start.setFont(new Font(fname, Font.BOLD, 16));
        add(start);
        
        start_value.setBounds(120, 55, 120, 30);
        start_value.setFont(new Font(fname, Font.PLAIN, 16));
        add(start_value);

        end.setBounds(240, 55, 120, 30);
        end.setFont(new Font(fname, Font.BOLD, 16));
        add(end);

        end_value.setBounds(320, 55, 120, 30);
        end_value.setFont(new Font(fname, Font.PLAIN, 16));
        add(end_value);
        
        duration.setBounds(430, 55, 120, 30);
        duration.setFont(new Font(fname, Font.BOLD, 16));
        add(duration);

        duration_value.setBounds(510, 55, 120, 30);
        duration_value.setFont(new Font(fname, Font.PLAIN, 16));
        add(duration_value);

        issuePane = new IssueDrawer(30, 500);
        issuePane.setLayout(null);

        addIssue.setBounds(IssueDrawer.MIN_WIDTH, 10, 120, 30);
        addIssue.setFont(new Font(fname, Font.BOLD, 14));
        issuePane.add(addIssue);
        OptButton btn1 = new OptButton("New Issue");
        OptButton btn2 = new OptButton("Exists Issue");
        btn1.setBounds(0, 0, addIssue.getWidth(), 30);
        btn2.setBounds(0, 0, addIssue.getWidth(), 30);
        addIssue.addOption(btn1, "addNewIssue");
        addIssue.addOption(btn2, "addExistsIssue");

        JPanel head = new JPanel() {
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
                
                g2.setStroke(new BasicStroke(2));
                g2.setColor(Color.decode("#dfdfdf"));
                g2.drawLine(0, 0, width, 0);
                g2.drawLine(0, height, width, height);
                g2.setStroke(new BasicStroke(1));
                g2.setColor(Color.decode("#bbbbbb"));
                g2.drawLine(0, 0, width, 0);
                g2.drawLine(0, height, width, height);
            }
        };
        head.setLayout(null);
        head.setBounds(IssueDrawer.MIN_WIDTH, 45, IssueDrawer.MAX_WIDTH - IssueDrawer.MIN_WIDTH, 30);
        issuePane.add(head);

        JLabel summaryLabel = new JLabel("Issue Summary");
        JLabel estimationLabel = new JLabel("Estimation");
        JLabel startLabel = new JLabel("Start Date");

        summaryLabel.setBounds(0, 0, 100, 30);
        summaryLabel.setForeground(Color.GRAY);
        summaryLabel.setFont(new Font(fname, Font.PLAIN, 12));
        head.add(summaryLabel);

        estimationLabel.setBounds(300, 0, 100, 30);
        estimationLabel.setForeground(Color.GRAY);
        estimationLabel.setFont(new Font(fname, Font.PLAIN, 12));
        head.add(estimationLabel);

        startLabel.setBounds(380, 0, 100, 30);
        startLabel.setForeground(Color.GRAY);
        startLabel.setFont(new Font(fname, Font.PLAIN, 12));
        head.add(startLabel);

        chartPane = new CharPanel();
        chartPane.setBackground(Color.LIGHT_GRAY);

        add(header, BorderLayout.NORTH);
        add(issuePane, BorderLayout.WEST);
        add(chartPane, BorderLayout.CENTER);
    }
}
