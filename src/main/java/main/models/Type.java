package main.models;

public class Type {
    private String typeName;
    public int tCount,fTCount,wTCount,pTCount,sTCount;

    public Type(String typeName, int tCount, int fTCount, int wTCount, int pTCount, int sTCount) {
        this.typeName = typeName;
        this.tCount = tCount;
        this.fTCount = fTCount;
        this.wTCount = wTCount;
        this.pTCount = pTCount;
        this.sTCount = sTCount;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getTCount() {
        return tCount;
    }

    public int getFTCount() {
        return fTCount;
    }

    public int getWTCount() {
        return wTCount;
    }

    public int getPTCount() {
        return pTCount;
    }

    public int getSTCount() {
        return sTCount=tCount+pTCount+wTCount+fTCount;
    }

    public void addTCount(){
        tCount+=1;
    }
    public void addPTCount(){
        pTCount+=1;
    }
    public void addWTCount(){
        wTCount+=1;
    }
    public void addFTCount(){
        fTCount+=1;
    }
    public void removeTcount(){
        tCount-=1;
    }
    public void removePTcount(){
        pTCount-=1;
    }
    public void removeWTcount(){
        wTCount-=1;
    }
    public void removeFTcount(){
        fTCount-=1;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeName='" + typeName + '\'' +
                ", tCount=" + tCount +
                ", fTCount=" + fTCount +
                ", wTCount=" + wTCount +
                ", pTCount=" + pTCount +
                ", sTCount=" + sTCount +
                '}';
    }
}
