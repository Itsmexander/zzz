package main.models;

public class ForwardingTaskData extends TaskData {
    private String responsibleName;

    public ForwardingTaskData(String taskName, String type, String start, String finish, String priorityLevel, String workStatus, String responsibleName) {
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
        return "ForwardingTaskData{" +"taskName='" + getTaskName() + '\'' +
                ", start=" + getStart() +
                ", finish=" + getFinish() +
                ", priorityLevel='" + getPriorityLevel() + '\'' +
                ", workStatus='" + getWorkStatus() + '\'' +
                ", responsibleName='" + responsibleName + '\'' +
                '}';
    }
}

