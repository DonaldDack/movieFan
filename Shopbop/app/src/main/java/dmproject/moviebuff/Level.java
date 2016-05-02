package dmproject.moviebuff;

import android.content.Context;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitry on 30.04.2016.
 */
public class Level {

    public int Number;//номер уровня
    final public int PointsToPass;//очки нужные для открытия этого уровня
    public int PointForLevel;//очки заработанные на этом уровне
    protected int ImageNumber;//номер уровня в картинке

    public Level(int Numb){
        Number = Numb;
        PointsToPass = Numb * 3;
        PointForLevel = 0;
    }

    public Level(int Numb, int pnt){
        Number = Numb;
        PointsToPass = Numb * 3;
        PointForLevel = pnt;
    }
}
