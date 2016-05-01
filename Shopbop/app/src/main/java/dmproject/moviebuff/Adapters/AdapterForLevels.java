package dmproject.moviebuff.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import dmproject.moviebuff.LevelsActivity;
import dmproject.moviebuff.Level;
import dmproject.moviebuff.TasksActivity;
import dmproject.moviebuff.R;

/**
 * Created by Dmitry on 01.05.2016.
 */
public class AdapterForLevels extends BaseAdapter {

    ArrayList<Level> levels;
    LayoutInflater inflater;
    Context context;
    static int level;

    public AdapterForLevels(Context cntext, ArrayList<Level> lvels){
        levels = lvels;
        context = cntext;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return levels.size();
    }

    @Override
    public Object getItem(int position) {
        return levels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.lvl_custom_view, parent, false);
        }

        Level level = (Level)getItem(position);

        for (int i = 0; i < 3; ++i){

            int firstStar = R.id.firstStar;
            int secondStar = R.id.secondStar;
            int thirdStar = R.id.thirdStar;
            int atribute = 0;
            switch (i){
                case 0:
                    atribute = firstStar;
                    break;
                case 1:
                    atribute = secondStar;
                    break;
                case 2:
                    atribute = thirdStar;
                    break;
            }

            if (level.PointForLevel <= 1 + 4 * i)
                ((ImageView) view.findViewById(atribute)).setImageResource(R.drawable.empty_star);
            else if (level.PointForLevel <= 3 + 4 * i)
                ((ImageView) view.findViewById(atribute)).setImageResource(R.drawable.half_star);
            else
                ((ImageView) view.findViewById(atribute)).setImageResource(R.drawable.full_star);
        }

        Button button = (Button) view.findViewById(R.id.btnLvl);
        if (LevelsActivity.Points >= level.PointsToPass) {
            button.setTag(level.Number);
            button.setOnClickListener(myClickListener);
            button.setText("" + level.Number);
        }
        else{
            //button.setImageResource(R.drawable.blocked);
            //button.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
            button.setText("" + level.Number);
            button.setTextColor(context.getResources().getColor(R.color.colorRed));
            }

        return view;
    }

    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, TasksActivity.class);
            LevelsActivity.level = (int)v.getTag();
            context.startActivity(intent);
        }
    };
}