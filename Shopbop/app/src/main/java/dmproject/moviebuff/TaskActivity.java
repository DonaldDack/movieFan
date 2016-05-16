package dmproject.moviebuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class TaskActivity extends AppCompatActivity implements View.OnTouchListener{

    TextView textViewAllPoints, textViewPointsForLevel, textView;
    ImageView imageView1, imageView2, imageView3, imageView4;
    Bundle bundle;
    String answerTasks;
    ArrayList<Integer> btnArr;
    ArrayList<ImageView> imgList;
    Stack<Integer> selectButton;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.setOnTouchListener(this);

        selectButton =new Stack<>();

        imgList = new ArrayList<>(4);

        imgList.add((ImageView) findViewById(R.id.image2));
        imgList.add((ImageView) findViewById(R.id.image3));
        imgList.add((ImageView)findViewById(R.id.image4));
        imgList.add((ImageView)findViewById(R.id.image1));

        btnArr = new ArrayList<>(15);

        btnArr.add(R.id.btn1);
        btnArr.add(R.id.btn2);
        btnArr.add(R.id.btn3);
        btnArr.add(R.id.btn4);
        btnArr.add(R.id.btn5);
        btnArr.add(R.id.btn6);
        btnArr.add(R.id.btn7);
        btnArr.add(R.id.btn8);
        btnArr.add(R.id.btn9);
        btnArr.add(R.id.btn10);
        btnArr.add(R.id.btn11);
        btnArr.add(R.id.btn13);
        btnArr.add(R.id.btn14);
        btnArr.add(R.id.btn15);
        btnArr.add(R.id.btn16);

        textViewAllPoints = (TextView) findViewById(R.id.tvPoints);
        textViewPointsForLevel = (TextView) findViewById(R.id.tvPointsForLevel);
        textViewAllPoints.setText("" + Game.PointsForAllGame);
        textView = (TextView) findViewById(R.id.tvAnswer);

        createAnswer();
        createPictures();
        createKeyboard();
    }

    public void btnWordsListener(View v){


        if (selectButton.empty()){
            textView.setText("");
        }
        selectButton.push(v.getId());
        Button button = (Button) findViewById(v.getId());
        textView.setText(textView.getText().toString() + button.getText().toString());
        button.setAlpha(0);
                //Toast.makeText(this, answers[Game.Task - 1].toString(), Toast.LENGTH_SHORT).show();

        if (TextUtils.equals(textView.getText().toString(), answerTasks.toString())){
            Toast.makeText(this, "поздравляем!", Toast.LENGTH_SHORT).show();
            Game.PointsForAllGame++;
            Game.levels.levels.get(Game.level - 1).PointForLevel++;
            textViewAllPoints.setText("" + Game.PointsForAllGame);
            textViewPointsForLevel.setText("" + Game.levels.levels.get(Game.level - 1).PointForLevel);
            Game.incTask();
            createPictures();
            createAnswer();
            createKeyboard();
        }
    }


    public void nextTask() {
        Game.incTask();
        createPictures();
        createAnswer();
        createKeyboard();
    }

    public void previousTask() {
        Game.decTask();
        createPictures();
        createAnswer();
        createKeyboard();
    }

    public void btnDelListener(View view) {
        TextView textView = (TextView) findViewById(R.id.tvAnswer);
        int length = textView.getText().toString().length();
        String answ = "";

        if (length > 0)
            for (int i = 0; i < length - 1; ++i)
                answ += textView.getText().charAt(i);

        textView.setText(answ);
        if (!selectButton.empty()){
            Button button = (Button) findViewById(selectButton.pop());
            button.setAlpha(1);
        }
    }

    public void createPictures(){
        TaskView taskView = new TaskView(Game.getTask(), Game.level, TaskActivity.this);

        for (int i = 0; i < 4; ++i){
            imgList.get(i).setImageResource(taskView.resourses.get(i));
        }
    }

    public void createKeyboard(){
        createTiredKeyBoard();

        textView.setText(R.string.enterAnswer);

        for (int i = 0; i < answerTasks.length(); ++i){
            boolean flag = true;
            while(flag){
                int ind = (new Random().nextInt())%(btnArr.size() - 1);
                if (ind < 0) ind = -ind;
                    Button button = ((Button)findViewById(btnArr.get(ind)));
                if (TextUtils.equals(button.getText().toString(), "")){
                    button.setText((answerTasks.charAt(i)+"").toString());
                    flag = false;
                }
            }
        }

        for (int i = 0; i < btnArr.size() - answerTasks.length();++i){
            boolean flag = true;
            while(flag){
                int ind = new Random().nextInt()%15;
                if (ind < 0)
                    ind = -ind;
                Button button = ((Button)findViewById(btnArr.get(ind)));
                if (TextUtils.equals(button.getText().toString(), "")){
                    int ind2 = new Random().nextInt() % 30;
                    if (ind2 < 0) ind2 =-ind2;
                    button.setText(getResources().getStringArray(R.array.alphabet)[ind2].toString());
                    flag = false;}
            }
        }
  /**/  }

    public void createAnswer(){
        answerTasks = getResources().getStringArray(R.array.answers)[Game.getTask() - 1];
    }

    public void createTiredKeyBoard(){
        for(int i =0;i < btnArr.size(); ++i){
            Button button = (Button)findViewById(btnArr.get(i));
            button.setText("");
            button.setAlpha(1);
        }
        selectButton = new Stack<>();
    }

    public boolean onTouch(View view, MotionEvent event)
    {
        float fromPosition = 0;
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN: // Пользователь нажал на экран, т.е. начало движения
                // fromPosition - координата по оси X начала выполнения операции
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP: // Пользователь отпустил экран, т.е. окончание движения
                float toPosition = event.getX();
                if (fromPosition > toPosition)
                    nextTask();
                else if (fromPosition < toPosition)
                    previousTask();
            default:
                break;
        }
        return true;
    }
}
