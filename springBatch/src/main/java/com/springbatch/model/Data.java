package com.springbatch.model;

public class Data {
    private int idx;
    private String v;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "data{" +
                "idx=" + idx +
                ", v='" + v + '\'' +
                '}';
    }
}
