package dmproject.moviebuff;

import java.util.ArrayList;

/**
 * Created by Dmitry on 02.05.2016.
 */
public class Game {
    static public int level;
    static public String name;
    static public int PointsForAllGame;
    static public int Task;
    static public ArrayList<String[]> answers;
    static public Levels levels;

    static public void createAnswers(){
        answers = new ArrayList<>();
        String[] strings = new String[]{
                "мстители",
                "призрак",
                "зверополис",
                "Головоломка"
        };
        answers.add(strings);
    }




}
