package main.services;

import main.models.Type;
import main.models.TypeList;
import main.models.WeeklyTaskData;

import java.io.*;

public class TypeFileDataSource {
    private String fileDirectoryName;
    private String fileName;
    private TypeList typeList;

    public TypeFileDataSource(String fileDirectoryName, String fileName) {
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
            Type name = new Type(data[0].trim(),Integer.parseInt(data[1].trim()),Integer.parseInt(data[2].trim()),
                    Integer.parseInt(data[3].trim()),Integer.parseInt(data[4].trim()),Integer.parseInt(data[5].trim()));
            typeList.addList(name);
        }
        reader.close();
    }
    public TypeList getTaskData() {
        try {
            typeList = new TypeList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return typeList;
    }
    public void setFileData(TypeList typeList){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Type type: typeList.toList()) {
                String line = type.getTypeName()+","+type.getTCount()+","+type.getFTCount()+","+type.getWTCount()+","+type.getPTCount()+","+type.getSTCount();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}