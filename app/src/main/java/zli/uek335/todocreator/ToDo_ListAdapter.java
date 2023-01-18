package zli.uek335.todocreator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import zli.uek335.todocreator.model.ToDo;

public class ToDo_ListAdapter extends ArrayAdapter<ToDo> {
    private ArrayList<ToDo> objects;
    private Context context;
    public ToDo_ListAdapter(Context context, ArrayList<ToDo> objects) {
        super(context, R.layout.activity_main, objects);
        this.objects = objects;
        this.context = context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.activity_main, parent, false);

        // 3. Get the two text view from the rowView
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView enddat = (TextView) rowView.findViewById(R.id.enddat);
        LinearLayout item = (LinearLayout) rowView.findViewById(R.id.layout);

        // 4. Set the text for textView
        name.setText(objects.get(position).getName());
        enddat.setText(objects.get(position).getEnddat());


        // 5. return rowView
        return rowView;
    }
}
