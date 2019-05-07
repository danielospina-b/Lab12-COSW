package edu.eci.cosw.APIApp.network.model;

import java.util.HashMap;

public class TodoTask {
    private String description;
    private String status;
    private String dueDate;
    private HashMap<String, String> responsible;

    public TodoTask() {

    }

    public TodoTask(String description, String status, String dueDate, HashMap<String, String> responsible) {
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.responsible = responsible;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public HashMap<String,String> getResponsible() {
        return this.responsible;
    }

    public void setResponsible(HashMap<String,String> responsible) {
        for (String key: responsible.keySet()) {
            System.out.println("Key: " + key);
        }
        for (String value: responsible.values()) {
            System.out.println("Value: " + value);
        }
        this.responsible = responsible;
    }

    @Override
    public String toString() {
        return "TodoTask{" +
                "description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", responsible=" + responsible.toString() +
                '}';
    }
}
