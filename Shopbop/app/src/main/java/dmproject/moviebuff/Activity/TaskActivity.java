package dmproject.moviebuff.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import dmproject.moviebuff.DBHelper;
import dmproject.moviebuff.Game;
import dmproject.moviebuff.R;
import dmproject.moviebuff.Data.TaskView;

public class TaskActivity extends AppCompatActivity implements View.OnTouchListener{

    TextView textViewAllPoints, textViewPointsForLevel;
    //ImageView imageView1, imageView2, imageView3, imageView4;
    String answerTasks, answerText;
    ArrayList<Integer> btnArr;
    ArrayList<ImageView> imgList;
    Stack<Integer> selectButton;
    LinearLayout mainLayout;
    float fromPosition;
    ArrayList<View> answerButtons;
    int curButtonForAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.setOnTouchListener(this);

        selectButton = new Stack<>();

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
        answerText = new String();

        createScreen();
    }

    public void btnWordsListener(View v){

        if (curButtonForAnswer < answerButtons.size()){

            if (selectButton.empty()){
            answerText = "";
            }

            selectButton.push(v.getId());

            Button button = (Button) findViewById(v.getId());

            answerText += button.getText().toString();

            button.setAlpha(0);
            button.setClickable(false);

            ((Button) answerButtons.get(curButtonForAnswer++)).setText(button.getText().toString());
                    //Toast.makeText(this, answers[Game.Task - 1].toString(), Toast.LENGTH_SHORT).show();

            if (TextUtils.equals(answerText, answerTasks.toString())){
                Toast.makeText(this, "поздравляем!", Toast.LENGTH_SHORT).show();
                ContentValues values = new ContentValues();
                values.put(DBHelper.NAME_COLLUMN_LEVEL, Game.level);
                int fin = 1;
                for (int i = 0; i < Game.levels.get(Game.level).FinishedTasks.size(); ++i)
                   fin *= Game.levels.get(Game.level).FinishedTasks.get(i);
                values.put(DBHelper.NAME_COLLUMN_FINISHED, fin * Game.Task);
                Game.DataBase.update(DBHelper.NAME_TABLE, values, DBHelper.NAME_COLLUMN_LEVEL + "= ?", new String[]{Game.level + ""});
                Game.levels.get(Game.level).FinishedTasks.add(Game.levels.get(Game.level).FreeTasks.get(Game.CurInd));
                Game.levels.get(Game.level).FreeTasks.remove(Game.CurInd);
                Game.PointsForAllGame++;
                Game.levels.get(Game.level - 1).PointForLevel++;
                textViewAllPoints.setText("" + Game.PointsForAllGame);
                textViewPointsForLevel.setText("" + Game.levels.levels.get(Game.level - 1).PointForLevel);
                if (Game.levels.get(Game.level).FreeTasks.size() == 0) {
                    Game.setlevel(Game.level + 1);

                }
                nextTask();
            }}
    }


    public void nextTask() {
        Game.incTask();
        createScreen();
    }

    public void previousTask() {
        Game.decTask();
        createScreen();
    }

    public void btnDelListener(View view) {
        int length = answerText.length();
        String answ = "";

        if (length > 0)
            for (int i = 0; i < length - 1; ++i)
                answ += answerText.charAt(i);

        answerText =answ;
        if (curButtonForAnswer > 0)
            ((Button) answerButtons.get(--curButtonForAnswer)).setText("");

        if (!selectButton.empty()){
            Button button = (Button) findViewById(selectButton.pop());
            button.setAlpha(1);
            button.setClickable(true);
        }
    }

    public void createPictures(){
        TaskView taskView = new TaskView(Game.getTask(), Game.level, TaskActivity.this);

        for (int i = 0; i < 4; ++i){
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
            alphaAnimation.setDuration(1000);
            imgList.get(i).startAnimation(alphaAnimation);
            imgList.get(i).setImageResource(taskView.resourses.get(i));
            alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setDuration(2000);
            imgList.get(i).startAnimation(alphaAnimation);
        }
    }

    public void createKeyboard(){
        createTiredKeyBoard();

        answerText = "";

        for (int i = 0; i < answerTasks.length(); ++i){
            boolean flag = true;
            while(flag){
                int ind = (new Random().nextInt())%(btnArr.size() - 1);
                if (ind < 0) ind = -ind;
                Button button = ((Button)findViewById(btnArr.get(ind)));
                button.setClickable(true);
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
                button.setClickable(true);
                if (TextUtils.equals(button.getText().toString(), "")){
                    int ind2 = new Random().nextInt() % 30;
                    if (ind2 < 0) ind2 =-ind2;
                    button.setText(getResources().getStringArray(R.array.alphabet)[ind2].toString());
                    flag = false;}
            }
        }
  /**/  }

    public void createAnswer(){
        answerTasks = getResources().getStringArray(R.array.answers)[(Game.level - 1) * 6 + Game.getTask() - 1];
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.AnswerLayout);
        linearLayout.removeAllViewsInLayout();
        linearLayout.setGravity(Gravity.CENTER);

        answerButtons = new ArrayList<>();
        curButtonForAnswer = 0;

        String[] arrAnswer = answerTasks.split(" ");

        if (arrAnswer.length > 1){
            ArrayList<LinearLayout> linearLayouts = new ArrayList<>();
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            answerTasks = "";
            for (int i = 0; i < arrAnswer.length; ++i){
                answerTasks += arrAnswer[i];
                LinearLayout linearLayout1 = new LinearLayout(this);
                linearLayout1.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                linearLayout1.setGravity(Gravity.CENTER_HORIZONTAL);
                for (int j = 0; j < arrAnswer[i].length(); ++j){
                    Button button = new Button(this);
                    button.setBackgroundResource(R.drawable.button_answer_num);
                    button.setWidth(getResources().getConfiguration().screenWidthDp/arrAnswer[i].length());
                    answerButtons.add(button);
                    linearLayout1.addView(button);
                }
                linearLayout.addView(linearLayout1);
            }
        }else {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i < answerTasks.length(); ++i) {
                Button button = new Button(this);
                button.setBackgroundResource(R.drawable.button_answer_num);
                //button.setWidth(getResources().getConfiguration().screenWidthDp/answerTasks.length());
                answerButtons.add(button);
                linearLayout.addView(button);
            }
        }


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
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN: // Пользователь нажал на экран, т.е. начало движения
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP: // Пользователь отпустил экран, т.е. окончание движения
                float toPosition = event.getX();
                if (fromPosition - toPosition > getResources().getConfiguration().screenWidthDp/10)
                    nextTask();
                    //Toast.makeText(TaskActivity.this, "" + (fromPosition - toPosition), Toast.LENGTH_SHORT).show();}
                else if (toPosition - fromPosition > getResources().getConfiguration().screenWidthDp/10)
                    previousTask();
            default:
                break;
        }
        return true;
    }

    public void createScreen(){
        createPictures();
        createAnswer();
        createKeyboard();
    }

    public void saveInstantState(){

    }

    public void btnBackListener(View view) {
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }


}
