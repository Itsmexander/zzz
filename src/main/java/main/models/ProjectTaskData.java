package main.models;

public class ProjectTaskData extends TaskData {
    private String managerName;

    public ProjectTaskData(String taskName, String type, String start, String finish, String priorityLevel, String workStatus, String managerName) {
        super(taskName, type, start, finish, priorityLevel, workStatus);
        this.managerName = managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    @Override
    public String toString() {
        return "ProjectTaskData{"+"taskName='" + getTaskName() + '\'' +
                ", start=" + getStart() +
                ", finish=" + getFinish() +
                ", priorityLevel='" + getPriorityLevel() + '\'' +
                ", workStatus='" + getWorkStatus() + '\'' +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}