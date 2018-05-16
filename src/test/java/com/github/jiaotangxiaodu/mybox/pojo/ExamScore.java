package com.github.jiaotangxiaodu.mybox.pojo;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 */
public class ExamScore implements Comparable<ExamScore> {
    private int range;
    private String name;

    public ExamScore(int range, String name) {
        this.range = range;
        this.name = name;
    }

    public ExamScore() {
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(ExamScore o) {
        return this.range - o.range;
    }
}
