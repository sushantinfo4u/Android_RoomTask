package com.example.android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android_assignment.Room.DatabaseClient;
import com.example.android_assignment.Room.PatientData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNewPatientActivity extends AppCompatActivity {

    private EditText edtPatientName,edtUHID,edtMobileNo,edtAddress,edtDOB,edtAge;
    private Button btnSubmit;
    private Spinner spnGender;
    private DatePickerDialog mDatePickerDialog;

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtDOB.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);

        setTitle("Fill Patient Details");
        edtPatientName=findViewById(R.id.edtPatientName);
        edtUHID=findViewById(R.id.edtUHID);
        edtMobileNo=findViewById(R.id.edtMobileNo);
        edtAddress=findViewById(R.id.edtAddress);
        edtDOB=findViewById(R.id.edtDOB);
        btnSubmit=findViewById(R.id.btnSubmit);
        spnGender=findViewById(R.id.spnGender);
        edtAge=findViewById(R.id.edtAge);

        edtDOB.setInputType(InputType.TYPE_NULL);

/*
        edtDOB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                new DatePickerDialog(AddNewPatientActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

*/

    setDateTimeField();

        edtDOB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                mDatePickerDialog.show();
               /* new DatePickerDialog(AddNewPatientActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
               */ return false;
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                valiDateInputData();
            }
        });



    }


    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);
                edtDOB.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker();

    }
    private void valiDateInputData() {

        String strName=edtPatientName.getText().toString();
        String strUHID=edtUHID.getText().toString();
        String strMobileNO=edtMobileNo.getText().toString();
        String strDOB=edtDOB.getText().toString();
        String strAddress=edtAddress.getText().toString();
        String strAge=edtAge.getText().toString();

            if(TextUtils.isEmpty(strName)){
                edtPatientName.requestFocus();
                edtPatientName.setError("Enter Patient Name");
            }
            else if(TextUtils.isEmpty(strUHID)){
                edtUHID.requestFocus();
                edtUHID.setError("Enter UHID");
            }
            else if(TextUtils.isEmpty(strMobileNO)){
                edtMobileNo.requestFocus();
                edtMobileNo.setError("Enter Mobile No");
            }
            else if(TextUtils.isEmpty(strDOB)){
                edtDOB.requestFocus();
                edtDOB.setError("Enter DOB");
            }
            else if(TextUtils.isEmpty(strAddress)){
                edtAddress.requestFocus();
                edtAddress.setError("Enter Address");
            }
            else if(TextUtils.isEmpty(strAge)){
                edtAge.requestFocus();
                edtAge.setError("Enter Age");
            }else {

                insertDataInRoom(strName,strAddress,strMobileNO,strUHID,strDOB,spnGender.getSelectedItem().toString(),strAge);
            }



    }

    private void insertDataInRoom(final  String strName,final  String strAddress, final  String strMobileNO,final String strUHID,final String strDOB,final String gender,final String strAge) {



        class SaveTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {

                PatientData patientData=new PatientData();
                patientData.setPatientName(strName);
                patientData.setPatientAddress(strAddress);
                patientData.setPatientAge(strAge);
                patientData.setPatientGender(gender);
                patientData.setPatientUHID(strUHID);
                patientData.setPatientDOB(strDOB);

                //adding to database
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .patientDao()
                        .insert(patientData);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(AddNewPatientActivity.this, "Patient Data added Successfully", Toast.LENGTH_SHORT).show();
            }
        }

        new SaveTask().execute();

    }

}