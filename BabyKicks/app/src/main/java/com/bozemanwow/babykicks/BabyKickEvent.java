package com.bozemanwow.babykicks;

/**
 * Created by Hippie on 4/30/2016.
 */
public class BabyKickEvent {

    private int id;
    private String date;
    private String start;
    private String end;
    private float length;

    public int getKicks() {
        return kicks;
    }

    public void setKicks(int kicks) {
        this.kicks = kicks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    private int kicks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
