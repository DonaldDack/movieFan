package dmproject.moviebuff;

import android.content.res.Resources;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Dmitry on 02.05.2016.
 */
public class Game {
    static public int level;
    static public String name;
    static public int PointsForAllGame;
    static public int Task;
    static public Levels levels;

    static public int getTask(){
        return Task < 1? 1: Task;
    }

    static public int getLevel(){
        return level < 1? 1: level;
    }

    static public void saveName(String name){
    }

    static public void incTask() {
        Task = (Task == 12? 1: (++Task)%13);
    }

    static public void decTask() {
        if (Task == 1)
            Task = 12;
        else
            --Task;
    }
}
