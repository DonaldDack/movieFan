package dmproject.moviebuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class ChooseLevelActivity extends AppCompatActivity {

    static public int Points = 5;
    GridView gridView;
    static public int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        gridView = (GridView) findViewById(R.id.gvLevels);

        Levels levels = new Levels();

        AdapterForLevels adapter = new AdapterForLevels(this, levels.levels);

        gridView.setAdapter(adapter);

        gridView.setNumColumns(3);

        gridView.setHorizontalSpacing(20);

        gridView.setVerticalSpacing(10);



    }

}
