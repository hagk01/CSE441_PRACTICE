package com.example.ex13_1;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class countryadaptal extends RecyclerView.Adapter<countryadaptal.CountryViewHolder> {
    private List<country> countries;

    public countryadaptal(List<country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        country country = countries.get(position);
        holder.imvFlag.setImageResource(country.getFlag());
        holder.txtName.setText(country.getCountryName());
        holder.txtCapital.setText(country.getCountryCapital());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder{

        ImageView imvFlag;
        TextView txtName;
        TextView txtCapital;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            imvFlag = itemView.findViewById(R.id.imvflag);
            txtName = itemView.findViewById(R.id.txtname);
            txtCapital = itemView.findViewById(R.id.txtcap);
        }
    }
}