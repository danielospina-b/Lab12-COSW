package edu.eci.cosw.APIApp.network.services;

import java.util.List;

import edu.eci.cosw.APIApp.network.model.TodoTask;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskService {

    @GET("api")
    Call<List<TodoTask>> getAllTasks();
}
