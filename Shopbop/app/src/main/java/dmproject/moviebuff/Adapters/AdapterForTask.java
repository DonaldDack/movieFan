package dmproject.moviebuff.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import dmproject.moviebuff.R;
import dmproject.moviebuff.Task;
import dmproject.moviebuff.TaskActivity;

/**
 * Created by Dmitry on 01.05.2016.
 */
public class AdapterForTask extends BaseAdapter {

    ArrayList<Integer> drawables;
    LayoutInflater inflater;
    Context context;

    public AdapterForTask(Context cntext, ArrayList<Integer> res){
        drawables = res;
        for (int x : drawables){
            Log.d("mylog", "" + x);
        }
        context = cntext;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return drawables.size();
    }

    @Override
    public Object getItem(int position) {
        return drawables.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.item, parent, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imgCustomMain);
        Log.d("mylog", "" + (context.getResources().getConfiguration().screenHeightDp));
        imageView.setImageResource(drawables.get(position));
        return view;
    }
}
