package com.yixiao.nycschools.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yixiao.nycschools.R;
import com.yixiao.nycschools.network.model.School;

import java.util.ArrayList;

public class SchoolDirectoryAdapter extends RecyclerView.Adapter<SchoolDirectoryAdapter.ViewHolder> {
    private ArrayList<School> mSchools;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(School school);
    }

    public SchoolDirectoryAdapter(ArrayList<School> schools, OnItemClickListener listener) {
        this.mSchools = schools;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SchoolDirectoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolDirectoryAdapter.ViewHolder holder, int position) {
        holder.mName.setText(mSchools.get(position).schoolName);
        holder.mLocation.setText(mSchools.get(position).location);
        holder.mPhoneNumber.setText(mSchools.get(position).phoneNumber);
        holder.mSchoolEmail.setText(mSchools.get(position).schoolEmail);
        holder.itemView.setOnClickListener(view -> mListener.onItemClick(mSchools.get(position)));
    }

    @Override
    public int getItemCount() {
        return mSchools.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mName;
        TextView mLocation;
        TextView mPhoneNumber;
        TextView mSchoolEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.school_name);
            mLocation = itemView.findViewById(R.id.location);
            mPhoneNumber = itemView.findViewById(R.id.phone_number);
            mSchoolEmail = itemView.findViewById(R.id.school_email);
        }
    }
}
