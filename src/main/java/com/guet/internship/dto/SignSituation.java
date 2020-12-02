package com.guet.internship.dto;

/**
 * Created by 欲隐君。 on 2020/12/2
 */
public class SignSituation {
    private Integer day;
    private Integer presentNum;
    private Integer classSum;
    private Integer absentNum;
    private Float attendanceRate;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getPresentNum() {
        return presentNum;
    }

    public void setPresentNum(Integer presentNum) {
        this.presentNum = presentNum;
    }

    public Integer getClassSum() {
        return classSum;
    }

    public void setClassSum(Integer classSum) {
        this.classSum = classSum;
    }

    public Integer getAbsentNum() {
        return absentNum;
    }

    public void setAbsentNum(Integer absentNum) {
        this.absentNum = absentNum;
    }

    public Float getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(Float attendanceRate) {
        this.attendanceRate = attendanceRate;
    }
}
