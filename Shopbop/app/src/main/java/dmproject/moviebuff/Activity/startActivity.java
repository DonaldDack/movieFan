package dmproject.moviebuff.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dmproject.moviebuff.Activity.LevelsActivity;
import dmproject.moviebuff.DBHelper;
import dmproject.moviebuff.Game;
import dmproject.moviebuff.R;

public class startActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void clickToStart(View v){
        Intent intent = new Intent(this, LevelsActivity.class);
        //Game.saveName(((EditText)findViewById(R.id.edName)).getText().toString());
        Game.createDB(this);
        startActivity(intent);
    }
}
