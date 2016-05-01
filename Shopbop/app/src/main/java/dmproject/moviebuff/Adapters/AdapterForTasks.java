package dmproject.moviebuff.Adapters;

import android.content.Context;
import android.content.Intent;
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
public class AdapterForTasks extends BaseAdapter {

    ArrayList<Task> tasks;
    LayoutInflater inflater;
    Context context;

    public AdapterForTasks(Context cntext, ArrayList<Task> task){
        tasks = task;
        context = cntext;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.task_view, parent, false);
        }

        Button button = (Button) view.findViewById(R.id.btnTasks);
        button.setText("" + (position + 1));
        button.setOnClickListener(myClickListener);


        return view;
    }

    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, TaskActivity.class);
            context.startActivity(intent);
        }
    };
}
