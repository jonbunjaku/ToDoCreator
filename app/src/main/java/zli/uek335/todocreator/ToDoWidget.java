package zli.uek335.todocreator;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

import zli.uek335.todocreator.model.AppDatabase;
import zli.uek335.todocreator.model.ToDo;
import zli.uek335.todocreator.model.ToDoDao;

/**
 * Implementation of App Widget functionality.
 */
public class ToDoWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "ToDo").allowMainThreadQueries().build();
        ToDoDao toDoDao = db.toDoDao();

        List<ToDo> toDos = toDoDao.getAllToDos();

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.to_do_widget);
        if (toDos == null) {
            views.setTextViewText(R.id.appwidget_text, widgetText);
        } else {
            for(int i = 0; i < toDos.size(); i++) {
                views.setTextViewText(R.id.appwidget_text, toDos.get(i).name + "\n" + toDos.get(i).enddat + "\n");
            }
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "ToDo").allowMainThreadQueries().build();
        ToDoDao toDoDao = db.toDoDao();

        List<ToDo> toDos = toDoDao.getAllToDos();

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.to_do_widget);
        for(int i = 0; i < toDos.size(); i++) {
            views.setTextViewText(R.id.appwidget_text, toDos.get(i).name + "\n" + toDos.get(i).enddat + "\n");
        }
        AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, ToDoWidget.class), views);
    }
}