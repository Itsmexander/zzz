package main.models;

import java.util.ArrayList;

public class TypeList {
    public ArrayList<Type> typeList;

    public TypeList() {
        typeList = new ArrayList<>();
    }

    public void addList(Type type){
        typeList.add(type);
    }

    public ArrayList<Type> toList() {
        return typeList;
    }

    public boolean checkNameDup(String name){
        for (Type type: typeList) {
            if (type.getTypeName().equals(name)){
                return false;
            }
        }
        return true;
    }
    public void addSelectedTypeCount(String type,String taskType){
        for (Type t:toList()) {
            if (t.getTypeName().equals(type)){
                switch (taskType) {
                    case "Generic":
                        t.addTCount();
                        break;
                    case "Project":
                        t.addPTCount();
                        break;
                    case "Weekly":
                        t.addWTCount();
                        break;
                    case "Forwarding":
                        t.addFTCount();
                        break;
                }
            }
        }
    }
    public void changeSelectedTypeCount(String before_type,String after_type,String taskType){
        for (Type t:toList()) {
            if (t.getTypeName().equals(after_type)){
                switch (taskType) {
                    case "Generic":
                        t.addTCount();
                        break;
                    case "Project":
                        t.addPTCount();
                        break;
                    case "Weekly":
                        t.addWTCount();
                        break;
                    case "Forwarding":
                        t.addFTCount();
                        break;
                }
            }
            else if (t.getTypeName().equals(before_type)){
                switch (taskType) {
                    case "Generic":
                        t.removeTcount();
                        break;
                    case "Project":
                        t.removePTcount();
                        break;
                    case "Weekly":
                        t.removeWTcount();
                        break;
                    case "Forwarding":
                        t.removeFTcount();
                        break;
                }
            }
        }
    }
}
