package com.company;

import java.util.Iterator;

public class Task implements Comparable<Task> {
    private String title;
    private String description;
    private int priority;

    public Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task: " + title +
                " Description= " + description + ", Priority= " + priority;
    }


    @Override
    public int compareTo(Task task) {
        if (this.priority == task.priority) {
            return this.title.compareTo(task.title);
        } else if (this.priority > task.priority) {
            return 1;
        } else {
            return -1;
        }
    }
}
