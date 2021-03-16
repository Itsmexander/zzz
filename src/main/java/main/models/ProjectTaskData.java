package main.models;

public class ProjectTaskData extends TaskData {
    private String responsibleName;

    public ProjectTaskData(String taskName, String type, String start, String finish, String priorityLevel, String workStatus, String responsibleName) {
        super(taskName, type, start, finish, priorityLevel, workStatus);
        this.responsibleName = responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    @Override
    public String toString() {
        return "ProjectTaskData{"+"taskName='" + getTaskName() + '\'' +
                ", start=" + getStart() +
                ", finish=" + getFinish() +
                ", priorityLevel='" + getPriorityLevel() + '\'' +
                ", workStatus='" + getWorkStatus() + '\'' +
                ", responsibleName='" + responsibleName + '\'' +
                '}';
    }
}