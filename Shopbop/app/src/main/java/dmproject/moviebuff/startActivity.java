package dmproject.moviebuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class startActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void clickToStart(View v){
        Intent intent = new Intent(this, LevelsActivity.class);
        //Game.saveName(((EditText)findViewById(R.id.edName)).getText().toString());
        startActivity(intent);
    }
}
