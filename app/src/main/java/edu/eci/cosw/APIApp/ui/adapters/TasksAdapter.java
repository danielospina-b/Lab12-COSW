package edu.eci.cosw.APIApp.ui.adapters;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import edu.eci.cosw.APIApp.R;
import edu.eci.cosw.APIApp.database.model.Task;
import edu.eci.cosw.APIApp.utils.StringUtils;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    List<Task> taskList = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        return new ViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.task_row, parent, false ) );
    }

    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
        Task task = taskList.get( position );
        holder.description.setText(task.description);
        holder.status.setText(task.status);
        holder.dueDate.setText(StringUtils.getFormattedDate(task.dueDate));
    }

    @Override
    public int getItemCount() {
        return taskList != null ? taskList.size() : 0;
    }

    public void updateTasks(List<Task> tasks) {
        System.out.println("Updating tasks");
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public TextView status;
        public TextView dueDate;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.task_description);
            status = itemView.findViewById(R.id.task_status);
            dueDate = itemView.findViewById(R.id.task_duedate);
        }
    }
}
