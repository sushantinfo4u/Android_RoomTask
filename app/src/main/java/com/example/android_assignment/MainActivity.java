package com.example.android_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_assignment.Adapter.PatientAdapter;
import com.example.android_assignment.Room.DatabaseClient;
import com.example.android_assignment.Room.PatientData;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvLIst;
    private Button btnNewPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Home");

            rcvLIst=findViewById(R.id.rcvLIst);
            rcvLIst.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            btnNewPatient=findViewById(R.id.btnNewPatient);

            btnNewPatient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),AddNewPatientActivity.class));
                }
            });

            getDataFromRoom();
    }

    private void getDataFromRoom() {


        class GetTask extends AsyncTask<Void,Void, List<PatientData>>{


            @Override
            protected List<PatientData> doInBackground(Void... voids) {

                List<PatientData> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .patientDao()
                        .getAll();
                return taskList;            }

            @Override
            protected void onPostExecute(List<PatientData> patientData) {
                super.onPostExecute(patientData);

                if(patientData!=null&&patientData.size()>0){

                    PatientAdapter patientAdapter=new PatientAdapter(getApplicationContext(),patientData);
                    rcvLIst.setAdapter(patientAdapter);

                }


            }
        }

        new GetTask().execute();

    }
}