package com.farvees.ganttchart;

import java.util.LinkedList;
import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class IssueList<T> extends LinkedList<T> {
    private IssueList<T> critalPath;

    public void addNewIssue(T issue) {
        add(issue);
    }

    public void resPositionIssue() {

    }

    public void addDependentIssue() {

    }

    public void makeIssueDependent() {

    }

    public Iterator<T> getIssues() {
        return this.iterator();
    }
}
