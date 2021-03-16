package main.models;

public class WeeklyTaskData extends TaskData{
    public String day;

    public WeeklyTaskData(String taskName, String type, String start, String finish, String priorityLevel, String workStatus, String day) {
        super(taskName, type, start, finish, priorityLevel, workStatus);
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "WeeklyTaskData{"+"taskName='" + getTaskName() + '\'' +
                ", start=" + getStart() +
                ", finish=" + getFinish() +
                ", priorityLevel='" + getPriorityLevel() + '\'' +
                ", workStatus='" + getWorkStatus() + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}