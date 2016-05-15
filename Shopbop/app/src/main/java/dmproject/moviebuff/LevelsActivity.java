package dmproject.moviebuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import dmproject.moviebuff.Adapters.AdapterForLevels;

public class LevelsActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        gridView = (GridView) findViewById(R.id.gvLevels);

        Game.levels = new Levels();

        AdapterForLevels adapter = new AdapterForLevels(this, Game.levels.levels);

        gridView.setAdapter(adapter);

        gridView.setNumColumns(3);

        gridView.setHorizontalSpacing(20);

        gridView.setVerticalSpacing(10);

    }

}
