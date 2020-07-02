package com.example.android_assignment.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientDao {

    @Query("SELECT * FROM patientdata")
    List<PatientData> getAll();

    @Insert
    void insert(PatientData task);
}
