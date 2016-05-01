package dmproject.moviebuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class LevelActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        TextView textView = (TextView) findViewById(R.id.tvLevelActivity);

        textView.setText("level: " + ChooseLevelActivity.level);

        gridView = (GridView) findViewById(R.id.gvTasks);

        Tasks task = new Tasks(ChooseLevelActivity.level);

        AdapterForTasks adapter = new AdapterForTasks(this, task.tasks);

        gridView.setAdapter(adapter);

        gridView.setNumColumns(3);

        gridView.setHorizontalSpacing(20);

        gridView.setVerticalSpacing(10);
    }
}
