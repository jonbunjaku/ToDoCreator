package zli.uek335.todocreator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        final Button createTodoButton = findViewById(R.id.addToDoButton);

        linearLayout = findViewById(R.id.layout);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "ToDo").allowMainThreadQueries().build();
        ToDoDao toDoDao = db.toDoDao();

        List<ToDo> allToDos = toDoDao.getAllToDos();

        for(int i = 0; i < allToDos.size(); i++) {
            TextView ToDo = new TextView(this);
            ToDo.setText(allToDos.get(i).name + "\t\t" + allToDos.get(i).enddat);
            ToDo.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
            ToDo.setPadding(0,20,0,0);
            linearLayout.addView(ToDo);
        }

    createTodoButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent createToDo = new Intent(MainActivity.this, FormPageActivity.class);
            startActivity(createToDo);
        }
    });
    }
}