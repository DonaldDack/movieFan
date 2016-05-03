package dmproject.moviebuff;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import dmproject.moviebuff.Adapters.AdapterForTask;

public class TaskActivity extends AppCompatActivity {

    TextView textViewAllPoints, textViewPointsForLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        textViewAllPoints = (TextView) findViewById(R.id.tvPoints);
        textViewPointsForLevel = (TextView) findViewById(R.id.tvPointsForLevel);
        textViewAllPoints.setText("" + Game.PointsForAllGame);


        Game.createAnswers();

        GridView gridView = (GridView) findViewById(R.id.gvTask);

        TaskView taskView = new TaskView(Game.Task, Game.level, TaskActivity.this);

        AdapterForTask adapterForTask= new AdapterForTask(this, taskView.resourses);

        gridView.setAdapter(adapterForTask);

        gridView.setNumColumns(2);

        gridView.setHorizontalSpacing(20);

        gridView.setVerticalSpacing(10);
        
    }

    public void btnWordsListener(View v){
        TextView textView = (TextView) findViewById(R.id.tvAnswer);
        if (TextUtils.equals(textView.getText().toString(), "введите ответ")){
            textView.setText("");
        }
        textView.setText(textView.getText().toString() + ((Button) findViewById(v.getId())).getText().toString());

        Toast.makeText(this, Game.answers.get(Game.level - 1)[Game.Task - 1].toString(), Toast.LENGTH_SHORT).show();

        if (TextUtils.equals(textView.getText().toString(), Game.answers.get(Game.level - 1)[Game.Task - 1].toString())){
            Toast.makeText(this, "поздравляем!", Toast.LENGTH_SHORT).show();
            Game.PointsForAllGame++;
            Game.levels.levels.get(Game.level - 1).PointForLevel++;
            textViewAllPoints.setText("" + Game.PointsForAllGame);
            textViewPointsForLevel.setText("" + Game.levels.levels.get(Game.level - 1).PointForLevel);
        }
    }
}
