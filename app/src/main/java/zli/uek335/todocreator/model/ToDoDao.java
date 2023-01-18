package zli.uek335.todocreator.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ToDoDao {
    @Query("SELECT * FROM todo")
    List<ToDo> getAllToDos();

    @Insert
    void insertAll(ToDo... toDos);
}
