package main.services;

import main.models.ProjectTaskData;
import main.models.TaskList;

import java.io.*;

public class ProjectTaskFileDataSource implements TaskFileDataSource_I {
    private String fileDirectoryName;
    private String fileName;
    private TaskList projectTaskList;

    public ProjectTaskFileDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    private void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            ProjectTaskData name = new ProjectTaskData(data[0].trim(),data[1].trim(),data[2].trim(),
                    data[3].trim(),data[4].trim(),data[5].trim(),data[6].trim());
            projectTaskList.addPList(name);
        }
        reader.close();
    }
    @Override
    public TaskList getTaskData() {
        try {
            projectTaskList = new TaskList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return projectTaskList;
    }


    public void setFileData(TaskList projectTaskList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (ProjectTaskData data: projectTaskList.toPList()) {
                String line = data.getTaskName()+","+data.getType()+","+data.getStart()+","+data.getFinish()
                        +","+data.getPriorityLevel()+","+data.getWorkStatus()+","+data.getResponsibleName();
//                String line = data.getTaskName()+","+data.getType()+","+data.getStart()+","+data.getFinish()
//                        +","+data.getPriorityLevel()+","+data.getWorkStatus()+","+data.getResponsibleName();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }


}