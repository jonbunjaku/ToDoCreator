package zli.uek335.todocreator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import zli.uek335.todocreator.model.AppDatabase;
import zli.uek335.todocreator.model.ToDo;
import zli.uek335.todocreator.model.ToDoDao;

public class FormPageActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.form_page);

        Button submitButton = findViewById(R.id.submitButton);
        EditText name = findViewById(R.id.name);
        EditText enddat = findViewById(R.id.enddat);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ToDo").allowMainThreadQueries().build();
        ToDoDao toDoDao = db.toDoDao();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDo toDo = new ToDo();
                toDo.name = String.valueOf(name.getText());
                toDo.enddat = String.valueOf(enddat.getText());

                toDoDao.insertAll(toDo);

                Intent intent = new Intent(FormPageActivity.this, MainActivity.class);
                startActivity(intent);

                sendBroadcast();
            }
        });
    }
    public void sendBroadcast() {
        this.sendBroadcast(new Intent(this, ToDoWidget.class));
    }
}
