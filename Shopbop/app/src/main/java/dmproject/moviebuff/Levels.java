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
        for (int i = 1; i < 13; i++){
            levels.add(new Level(i));
        }

        Game.PointsForAllGame = getAllPoints();
    }

    public Levels(Map<Integer, Integer> m){
        levels = new ArrayList<>();
        for (int i = 0; i < m.size(); ++i){
            levels.add(new Level(i + 1, m.get(i + 1)));
        }
    }

    public int getAllPoints(){
        return 0;
    }
}
