package com.example.android_assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_assignment.R;
import com.example.android_assignment.Room.PatientData;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {

    private Context mCtx;
    private List<PatientData> patientDataList;

    public PatientAdapter(Context mCtx, List<PatientData> patientDataList) {
        this.mCtx = mCtx;
        this.patientDataList = patientDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mCtx).inflate(R.layout.patient_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PatientData patientData=patientDataList.get(position);

        if(patientData!=null){

            holder.patientName.setText(patientData.getPatientName()!=null?patientData.getPatientName():"NA");
            holder.patientUHID.setText(patientData.getPatientUHID()!=null?patientData.getPatientUHID():"NA");
            holder.patientAddress.setText(patientData.getPatientAddress()!=null?patientData.getPatientAddress():"NA");
            holder.patientAge.setText(patientData.getPatientAge()!=null?patientData.getPatientAge():"NA");

        }

    }

    @Override
    public int getItemCount() {
        return patientDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView patientName,patientUHID,patientAge,patientAddress;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            patientName=itemView.findViewById(R.id.patientName);
            patientUHID=itemView.findViewById(R.id.patientUHID);
            patientAge=itemView.findViewById(R.id.patientAge);
            patientAddress=itemView.findViewById(R.id.patientAddress);

        }
    }
}
