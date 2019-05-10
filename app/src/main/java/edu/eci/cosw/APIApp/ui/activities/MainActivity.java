package edu.eci.cosw.APIApp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.eci.cosw.APIApp.R;
import edu.eci.cosw.APIApp.database.AppDatabase;
import edu.eci.cosw.APIApp.database.model.Task;
import edu.eci.cosw.APIApp.network.APIClient;
import edu.eci.cosw.APIApp.network.model.TodoTask;
import edu.eci.cosw.APIApp.network.services.TaskService;
import edu.eci.cosw.APIApp.storage.Storage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_logout) {
            handleLogout();
            return true;
        }
        else if (id == R.id.action_getTasks) {
            printTasks();
            return true;
        }
        else if (id == R.id.action_testDatabase) {
            printTasksDatabase();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void printTasks() {
        Storage storage = new Storage(this);
        System.out.println(storage.getToken());
        TaskService taskService = APIClient.getTaskService(storage.getToken());
        Call<List<TodoTask>> call = taskService.getAllTasks();
        call.enqueue(new Callback<List<TodoTask>>() {
            @Override
            public void onResponse(Call<List<TodoTask>> call, Response<List<TodoTask>> response) {
                ArrayList<TodoTask> result = (ArrayList<TodoTask>) response.body();
                for (TodoTask task : result) {
                    System.out.println(task.toString());
                    // Could be better, pero implicaria modificar la API //TODO ...
                    saveTaskOnDatabase(new Task((int) (Math.random()*10000), task.getDescription(), task.getStatus(), task.getDueDate()));
                }
                try {
                    if (!response.isSuccessful()) {
                        System.out.println("Error: " + response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<TodoTask>> call, Throwable t) {
                System.out.println("onFailure: " + t.getMessage());
            }
        });
    }

    private void saveTaskOnDatabase(Task task) {
        AppDatabase adb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        adb.taskDao().insertTask(task);
    }

    private void printTasksDatabase() {
        AppDatabase adb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        List<Task> tasks= adb.taskDao().loadAllTasks();
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    private void handleLogout() {
        Storage storage = new Storage(this);
        storage.clearToken();
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
