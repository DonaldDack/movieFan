package dmproject.moviebuff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.ArrayList;

import dmproject.moviebuff.Data.Level;
import dmproject.moviebuff.Data.Levels;

/**
 * Created by Dmitry on 02.05.2016.
 */
public class Game {
    static public int level;//номер текущего уровень
    static public String name;//имя игрока
    static public int PointsForAllGame;//очки заработанные за всю игру
    static public int Task;//текущее выполняемое задание
    static public int CurInd;//текущее выполняемое задание
    static public Levels levels;//массив уровней
    //static public int CountLevel;//массив уровней
    static public SQLiteDatabase DataBase;//база данных

    static public int getCountLevel(){
        return 6;
    }

    static public void createDB(Context context){
        DBHelper db = new DBHelper(context);

        DataBase = db.getWritableDatabase();
        fillVariablesFromDataBase();
    }

    static public void fillVariablesFromDataBase(){
        Cursor cursor = DataBase.query(DBHelper.NAME_TABLE,
                new String[]{DBHelper.NAME_COLLUMN_LEVEL, DBHelper.NAME_COLLUMN_FINISHED},
                null,null,null,null,null);
        int ind = 1;
        levels = new Levels();
        while (cursor.moveToNext()){
            ArrayList<Integer> arrayList = getArrayFromInt(cursor.getInt(cursor.getColumnIndex(DBHelper.NAME_COLLUMN_FINISHED)));
            Level level = new Level(ind++, arrayList);
            levels.add(level);
        }
        if (levels.size() == 0){
            ContentValues contentValues = new ContentValues();
            for (int i = 1; i < 7; ++i) {
                contentValues.put(DBHelper.NAME_COLLUMN_LEVEL, i);
                contentValues.put(DBHelper.NAME_COLLUMN_FINISHED, 1);
            }
            Game.levels = new Levels();

            for (int i = 1; i < 7; ++i)
                Game.levels.add(new Level(i, new ArrayList<Integer>()));
        }

        //fillpointsforlevel
    }

    private static ArrayList<Integer> getArrayFromInt(int finished) {
        ArrayList<Integer> arrayList = new ArrayList<>();
           if (finished % 2 == 0) arrayList.add(1);
           if (finished % 3 == 0) arrayList.add(2);
           if (finished % 5 == 0) arrayList.add(3);
           if (finished % 7 == 0) arrayList.add(4);
           if (finished % 11 == 0) arrayList.add(5);
           if (finished % 13 == 0) arrayList.add(6);
        return arrayList;
    }

    public static  int getIntFromArray(ArrayList<Integer> arrayList) {
        int finished =  1;
        for (int x : arrayList) {
            if (x == 1) finished *= 2;
            else if (x == 2) finished *= 3;
            else if (x == 3) finished *= 5;
            else if (x == 4) finished *= 7;
            else if (x == 5) finished *= 11;
            else if (x == 6) finished *= 13;
        }
        return finished;
    }

    static public int getTask(){
        Task = firstFreeTasks();
        return Task;
    }

    static public int getLevel(){
        return level < 1? 1: level;
    }

    static public void saveName(String name){
    }

    static public void incTask() {
        //Task = (Task == 12? 1: (++Task)%13);
        CurInd = (++CurInd)%levels.get(level).FreeTasks.size();
        Task = levels.get(level).FreeTasks.get(CurInd);
    }

    static public void decTask() {
        /*if (Task == 1)
            Task = 12;
        else
            --Task;*/
        CurInd = (--CurInd)%levels.get(level).FreeTasks.size();
        if (CurInd == -1)
            CurInd = levels.get(level).FreeTasks.size() - 1;
        Task = levels.get(level).FreeTasks.get(CurInd);
    }

    public static void setlevel(int level) {
        Game.level = level;
    }

    public static int firstFreeTasks() {
        return levels
                .get(level)
                .FreeTasks
                .get(CurInd);
    }
}
