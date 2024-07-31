package com.farvees.ganttchart;

import java.time.LocalDate;

public class Issue {
    private String devId;
    private String issueName;
    private LocalDate issueStart;
    private LocalDate issueEnd;

    public Issue() {
    }

    public Issue(String devId, String issueName, LocalDate issueStart, LocalDate issueEnd) {
        this.devId = devId;
        this.issueName = issueName;
        this.issueStart = issueStart;
        this.issueEnd = issueEnd;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public LocalDate getIssueStart() {
        return issueStart;
    }

    public void setIssueStart(LocalDate issueStart) {
        this.issueStart = issueStart;
    }

    public LocalDate getIssueEnd() {
        return issueEnd;
    }

    public void setIssueEnd(LocalDate issueEnd) {
        this.issueEnd = issueEnd;
    }

}
