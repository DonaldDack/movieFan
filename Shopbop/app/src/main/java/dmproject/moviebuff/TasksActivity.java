package dmproject.moviebuff;

import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

import dmproject.moviebuff.Adapters.AdapterForTasks;

public class TasksActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        TextView textView = (TextView) findViewById(R.id.tvLevelActivity);

        textView.setText("level: " + Game.level);

        gridView = (GridView) findViewById(R.id.gvTasks);

        Tasks task = new Tasks(Game.level);

        AdapterForTasks adapter = new AdapterForTasks(this, task.tasks);

        gridView.setAdapter(adapter);

        gridView.setNumColumns(3);

        gridView.setHorizontalSpacing(20);

        gridView.setVerticalSpacing(10);


    }


}
