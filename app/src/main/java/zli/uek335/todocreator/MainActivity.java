package zli.uek335.todocreator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import zli.uek335.todocreator.model.AppDatabase;
import zli.uek335.todocreator.model.ToDoDao;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "todo").allowMainThreadQueries().build();
        ToDoDao toDoDao = db.toDoDao();
        toDoDao.getAllToDos();
    }
}