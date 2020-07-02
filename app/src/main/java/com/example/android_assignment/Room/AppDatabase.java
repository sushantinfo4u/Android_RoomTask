package com.example.android_assignment.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {PatientData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PatientDao patientDao();


}
