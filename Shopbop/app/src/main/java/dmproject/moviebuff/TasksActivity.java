package dmproject.moviebuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import dmproject.moviebuff.Adapters.AdapterForTasks;

public class TasksActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        TextView textView = (TextView) findViewById(R.id.tvLevelActivity);

        textView.setText("level: " + LevelsActivity.level);

        gridView = (GridView) findViewById(R.id.gvTasks);

        Tasks task = new Tasks(LevelsActivity.level);

        AdapterForTasks adapter = new AdapterForTasks(this, task.tasks);

        gridView.setAdapter(adapter);

        gridView.setNumColumns(3);

        gridView.setHorizontalSpacing(20);

        gridView.setVerticalSpacing(10);
    }
}
