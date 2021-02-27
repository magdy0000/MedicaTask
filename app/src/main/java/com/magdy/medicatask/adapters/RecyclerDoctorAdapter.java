package com.magdy.medicatask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.Glide;
import com.magdy.medicatask.R;
import com.magdy.medicatask.data.models.BranchesModel;
import com.magdy.medicatask.data.models.ModelDoctors;

import java.util.List;

public class RecyclerDoctorAdapter extends RecyclerView.Adapter<RecyclerDoctorAdapter.Holder>{


    private List<ModelDoctors.ItemBean.DataBean> list ;

    private OnItemClick onItemClick ;
    public void setList(List<ModelDoctors.ItemBean.DataBean> list) {
        this.list = list;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_doctor, parent , false ) ;

        return new Holder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.specialise.setText(list.get(position).getSpecialty_description());
        Glide.with(holder.context).load(list.get(position).getImage()).into(holder.imageView) ;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() ;
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView name  ;
        TextView specialise  ;
        ImageView imageView ;
        Context context ;
        public Holder(@NonNull View itemView) {
            super(itemView);

            context  = itemView.getContext();
            name = itemView.findViewById(R.id.text_doctor_name);
            imageView = itemView.findViewById(R.id.image);
            specialise  = itemView.findViewById(R.id.text_specialise_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClick != null) {
                        int position = getAdapterPosition();
                        onItemClick.onClick(list.get(position).getId(),
                                list.get(position).getName());
                    }
                }
            });
        }
    }

    public interface OnItemClick {

        void onClick ( int doctorId , String doctorName);
    }
}


