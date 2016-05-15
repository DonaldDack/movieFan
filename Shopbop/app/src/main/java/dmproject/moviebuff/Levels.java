package dmproject.moviebuff;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Dmitry on 01.05.2016.
 */
public class Levels {

    public ArrayList<Level> levels;


    public Levels(){
        levels = new ArrayList<>();
        levels.add(new Level(1, 0));
        levels.add(new Level(2, 1));
        levels.add(new Level(3, 2));
        levels.add(new Level(4, 3));
        levels.add(new Level(5, 4));
        levels.add(new Level(6, 5));
        levels.add(new Level(7, 6));
        levels.add(new Level(8, 7));
        levels.add(new Level(9, 8));
        levels.add(new Level(10, 9));
        levels.add(new Level(11, 10));
        levels.add(new Level(12, 11));
        Game.PointsForAllGame = 50;
    }

    public Levels(Map<Integer, Integer> m){
        levels = new ArrayList<>();
        for (int i = 0; i < m.size(); ++i){
            levels.add(new Level(i + 1, m.get(i + 1)));
        }
    }
}
