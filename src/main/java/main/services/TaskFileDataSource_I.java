package main.services;
import main.models.TaskList;

public interface TaskFileDataSource_I {
    TaskList getTaskData();
    void setFileData(TaskList taskList);
}
