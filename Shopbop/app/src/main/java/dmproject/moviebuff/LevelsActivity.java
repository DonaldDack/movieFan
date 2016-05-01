package dmproject.moviebuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import dmproject.moviebuff.Adapters.AdapterForLevels;

public class LevelsActivity extends AppCompatActivity {

    static public int Points = 5;
    GridView gridView;
    static public int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        gridView = (GridView) findViewById(R.id.gvLevels);

        Levels levels = new Levels();

        AdapterForLevels adapter = new AdapterForLevels(this, levels.levels);

        gridView.setAdapter(adapter);

        gridView.setNumColumns(3);

        gridView.setHorizontalSpacing(20);

        gridView.setVerticalSpacing(10);



    }

}