package dmproject.moviebuff;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Dmitry on 01.05.2016.
 */
public class TaskView extends Task {

    public ArrayList<Drawable> resourses;
    public String solution;
    Context context;

    public TaskView(int NumbTsk, int lvl, Context contex) {
        super(NumbTsk, lvl);
        resourses = getResourses();
        context = contex;
    }

    public ArrayList<Drawable> getResourses() {

        ArrayList<Drawable> res = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
                Drawable drawable = Drawable.createFromPath(getPath(i + 1));
                res.add(drawable);
        }

            return res;

    }

    public String getPath(int i){
        return Uri.parse("file:///C:\\Users\\Dmitry\\Documents\\GitHub\\movieFan\\Shopbop\\app\\src\\main\\assets\\" +
                LevelNum +"_" + NumberTask + "_" + i + ".png").toString();
    }
}
