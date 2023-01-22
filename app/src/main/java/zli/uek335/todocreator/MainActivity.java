package zli.uek335.todocreator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import zli.uek335.todocreator.model.AppDatabase;
import zli.uek335.todocreator.model.ToDo;
import zli.uek335.todocreator.model.ToDoDao;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.layout);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "todo").allowMainThreadQueries().build();
        ToDoDao toDoDao = db.toDoDao();

        List<ToDo> allToDos = toDoDao.getAllToDos();

        for(int i = 0; i < allToDos.size(); i++) {
            TextView ToDo = new TextView(this);
            ToDo.setText(allToDos.get(i).name + "\t\t" + allToDos.get(i).enddat);
        }


    }
/*    private void displayData() {
        Cursor c = db.query("SELECT * FROM ToDo", null);
        while (c.moveToNext()) { //Loop through all the records
            //Now on the variable 'c' there is one record.

            int name = c.getColumnIndex("name"); //Get the index of the column from your table.
            String nameval = c.getString(name); //Get the value from the column from the current record.

            int enddat = c.getColumnIndex("enddat");
            String enddatVal = c.getString(enddat);


        }

        arrayAdapter.notifyDataSetChanged(); //Notify, that you have changed some data in the array list.
    }*/
}