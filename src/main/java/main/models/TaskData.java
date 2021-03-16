package main.models;

public class TaskData {
    private String taskName;
    private String type;
    private String start;
    private String finish;
    private String priorityLevel;
    private String workStatus;

    public TaskData(String taskName, String type, String start, String finish, String priorityLevel, String workStatus) {
        this.taskName = taskName;
        this.type = type;
        this.start = start;
        this.finish = finish;
        this.priorityLevel = priorityLevel;
        this.workStatus = workStatus;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TaskData{" +
                "taskName='" + taskName + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", priorityLevel='" + priorityLevel + '\'' +
                ", workStatus='" + workStatus + '\'' +
                '}';
    }
}