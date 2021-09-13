package com.springbatch.model;

public class Data {
    private int idx;
    private String type;
    private String val;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Data{" +
                "idx=" + idx +
                ", type='" + type + '\'' +
                ", val='" + val + '\'' +
                '}';
    }
}
