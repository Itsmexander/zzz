package main.services;

import main.models.TaskList;
import main.models.WeeklyTaskData;

import java.io.*;

public class WeeklyTaskFileDataSource implements TaskFileDataSource_I {
    private String fileDirectoryName;
    private String fileName;
    private TaskList weeklyTaskList;

    public WeeklyTaskFileDataSource(String fileDirectoryName, String fileName) {
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
            WeeklyTaskData w1 = new WeeklyTaskData(data[0].trim(),data[1].trim()
                    ,data[2].trim(),data[3].trim(),data[4].trim(),data[5].trim(),data[6].trim());
            weeklyTaskList.addWList(w1);
        }
        reader.close();
    }
    @Override
    public TaskList getTaskData() {
        try {
            weeklyTaskList = new TaskList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return weeklyTaskList;
    }
    public void setFileData(TaskList weeklyTaskList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (WeeklyTaskData data: weeklyTaskList.toWList()) {
                String line = data.getTaskName()+","+data.getType()+","+data.getStart()+","+data.getFinish()
                        +","+data.getPriorityLevel()+","+data.getWorkStatus()+","+data.getDay();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }


}