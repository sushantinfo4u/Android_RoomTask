package com.example.android_assignment.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PatientData  {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "patientName")
    private String patientName;
    @ColumnInfo(name = "patientAddress")
    private String patientAddress;
    @ColumnInfo(name = "patientDOB")
    private String patientDOB;
    @ColumnInfo(name = "patientAge")
    private String patientAge;
    @ColumnInfo(name = "patientGender")
    private String patientGender;
    @ColumnInfo(name = "patientUHID")
    private String patientUHID;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(String patientDOB) {
        this.patientDOB = patientDOB;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientUHID() {
        return patientUHID;
    }

    public void setPatientUHID(String patientUHID) {
        this.patientUHID = patientUHID;
    }


}
