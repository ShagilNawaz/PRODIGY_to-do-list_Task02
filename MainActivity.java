package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnAddTask;
    private ArrayList<String> tasks;
    private ArrayAdapter<String> tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAddTask = findViewById(R.id.btnAddTask);

        // Initialize the list of tasks
        tasks = new ArrayList<>();

        // Create an ArrayAdapter to bind the list of tasks to the ListView
        tasksAdapter = new ArrayAdapter<>(this, R.layout.task_item, R.id.taskText, tasks);
        listView.setAdapter(tasksAdapter);

        // Add Task Button Listener
        btnAddTask.setOnClickListener(v -> showAddTaskDialog());
    }

    // Show a dialog or input field to add a task
    private void showAddTaskDialog() {
        final EditText taskInput = new EditText(MainActivity.this);
        taskInput.setHint("Enter task");

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add New Task")
                .setView(taskInput)
                .setPositiveButton("Add", (dialog, which) -> {
                    String task = taskInput.getText().toString();
                    if (!task.isEmpty()) {
                        tasks.add(task);  // Add task to the list
                        tasksAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
