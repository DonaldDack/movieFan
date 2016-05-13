package dmproject.moviebuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    TextView textViewAllPoints, textViewPointsForLevel;
    ImageView imageView1, imageView2, imageView3, imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        textViewAllPoints = (TextView) findViewById(R.id.tvPoints);
        textViewPointsForLevel = (TextView) findViewById(R.id.tvPointsForLevel);
        textViewAllPoints.setText("" + Game.PointsForAllGame);


        Game.createAnswers();

        imageView2 = (ImageView)findViewById(R.id.image2);
        imageView3 = (ImageView)findViewById(R.id.image3);
        imageView4 = (ImageView)findViewById(R.id.image4);
        imageView1 = (ImageView)findViewById(R.id.image1);

        ArrayList<ImageView> imgList = new ArrayList<>(4);

        imgList.add((ImageView) findViewById(R.id.image2));
        imgList.add((ImageView)findViewById(R.id.image3));
        imgList.add((ImageView)findViewById(R.id.image4));
        imgList.add((ImageView)findViewById(R.id.image1));

        TaskView taskView = new TaskView(Game.Task, Game.level, TaskActivity.this);

        for (int i = 0; i < 4; ++i){
            imgList.get(i).setImageResource(taskView.resourses.get(i));
        }
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
