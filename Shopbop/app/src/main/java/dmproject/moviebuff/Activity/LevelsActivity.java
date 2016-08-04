package dmproject.moviebuff.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import dmproject.moviebuff.Adapters.AdapterForLevels;
import dmproject.moviebuff.Game;
import dmproject.moviebuff.R;

public class LevelsActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        gridView = (GridView) findViewById(R.id.gvLevels);

       //Game.fillVariablesFromDataBase();

        AdapterForLevels adapter = new AdapterForLevels(this, Game.levels.levels);

        gridView.setAdapter(adapter);

        gridView.setNumColumns(2);

        gridView.setHorizontalSpacing(20);

        gridView.setVerticalSpacing(10);

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, startActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

}
