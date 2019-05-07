package edu.eci.cosw.APIApp.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey
    @NonNull
    public int id;

    public String description;

    public String status;

    public String dueDate;

    public Task(int id, String description, String status, String dueDate) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
