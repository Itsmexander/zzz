package main.models;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<TaskData> taskList;
    private ArrayList<WeeklyTaskData> weeklyTaskList;
    private ArrayList<ProjectTaskData> projectTaskList;
    private ArrayList<ForwardingTaskData> forwardingTaskList;
    public TaskList(){
        this.taskList=new ArrayList<>();
        this.weeklyTaskList=new ArrayList<>();
        this.projectTaskList=new ArrayList<>();
        this.forwardingTaskList=new ArrayList<>();
    }
    public void addList(TaskData taskData) {
        this.taskList.add(taskData);
    }
    public void addWList(WeeklyTaskData weeklyTaskData){
        this.weeklyTaskList.add(weeklyTaskData);
    }
    public void addPList(ProjectTaskData projectTaskData){
        this.projectTaskList.add(projectTaskData);
    }
    public void addFList(ForwardingTaskData forwardingTaskData){
        this.forwardingTaskList.add(forwardingTaskData);
    }
    public ArrayList<TaskData> toList() {
        return taskList;
    }
    public ArrayList<WeeklyTaskData> toWList(){
        return weeklyTaskList;
    }
    public ArrayList<ProjectTaskData> toPList(){
        return projectTaskList;
    }
    public ArrayList<ForwardingTaskData> toFList(){
        return forwardingTaskList;
    }
}
