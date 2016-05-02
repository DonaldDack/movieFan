package dmproject.moviebuff;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;

import dmproject.moviebuff.Adapters.AdapterForTask;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        GridView gridView = (GridView) findViewById(R.id.gvTask);

        TaskView taskView = new TaskView(1, Game.level, TaskActivity.this);

        AdapterForTask adapterForTask= new AdapterForTask(this, taskView.resourses);

        gridView.setAdapter(adapterForTask);

        gridView.setNumColumns(2);

        gridView.setHorizontalSpacing(20);

        gridView.setVerticalSpacing(10);
    }
}
