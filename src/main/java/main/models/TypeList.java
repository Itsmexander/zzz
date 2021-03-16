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
}
