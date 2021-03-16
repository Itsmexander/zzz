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

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTCount() {
        return tCount;
    }

    public void setTCount(int tCount) {
        this.tCount = tCount;
    }

    public int getFTCount() {
        return fTCount;
    }

    public void setFTCount(int fTCount) {
        this.fTCount = fTCount;
    }

    public int getWTCount() {
        return wTCount;
    }

    public void setWTCount(int wTCount) {
        this.wTCount = wTCount;
    }

    public int getPTCount() {
        return pTCount;
    }

    public void setPTCount(int pTCount) {
        this.pTCount = pTCount;
    }

    public int getSTCount() {
        return sTCount=tCount+pTCount+wTCount+fTCount;
    }

    public void setSTCount(int sTCount) {
        this.sTCount = sTCount;
    }

    public void addTcount(){
        tCount+=1;
    }
    public void addPTcount(){
        pTCount+=1;
    }
    public void addWTcount(){
        wTCount+=1;
    }
    public void addFTcount(){
        fTCount+=1;
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
