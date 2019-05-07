package edu.eci.cosw.APIApp.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.eci.cosw.APIApp.database.model.Task;

@Dao
public interface TaskDao {

    @Query("select * from task")
    List<Task> loadAllTasks();

    @Insert()
    void insertTask(Task task);

}
