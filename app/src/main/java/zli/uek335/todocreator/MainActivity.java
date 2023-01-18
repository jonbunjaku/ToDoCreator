package zli.uek335.todocreator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zli.uek335.todocreator.model.AppDatabase;
import zli.uek335.todocreator.model.ToDo;
import zli.uek335.todocreator.model.ToDoDao;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter arrayAdapter;
    private ArrayList<ToDo> toDos = new ArrayList<>();
    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "todo").allowMainThreadQueries().build();
    ToDoDao toDoDao = db.toDoDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List toDo =
        toDos = new ArrayList<String>(toDoDao.getAllToDos());

        ListView lv = (ListView) findViewById(R.id.lv);
        arrayAdapter = new ToDo_ListAdapter(this, toDos);
        lv.setAdapter(arrayAdapter);

        displayData();
    }
    private void displayData() {
        Cursor c = db.query("SELECT * FROM ToDo", null);
        while (c.moveToNext()) { //Loop through all the records
            //Now on the variable 'c' there is one record.

            int name = c.getColumnIndex("name"); //Get the index of the column from your table.
            String nameval = c.getString(name); //Get the value from the column from the current record.

            int enddat = c.getColumnIndex("enddat");
            String enddatVal = c.getString(enddat);


        }

        arrayAdapter.notifyDataSetChanged(); //Notify, that you have changed some data in the array list.
    }
}