package com.magdy.medicatask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.magdy.medicatask.R;
import com.magdy.medicatask.data.models.ModelAppointments;
import com.magdy.medicatask.data.models.TimeModel;

import java.util.List;

public class RecyclerAdapterTime extends RecyclerView.Adapter<RecyclerAdapterTime.Holder> {

    private int rowIndex = -1;
    private List<TimeModel.ItemBean.DataBean> list ;
    private OnTimeClick onTimeClick ;



    public void setOnTimeClick(OnTimeClick onTimeClick) {
        this.onTimeClick = onTimeClick;
    }



   public void setList(List<TimeModel.ItemBean.DataBean> list ) {
        this.list = list;
        rowIndex = -1 ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dates , parent , false) ;

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.date.setText(list.get(position).getTime());


        if(rowIndex==position){
            holder.date.setBackgroundResource(R.color.primary_blue);
            holder.date.setTextColor(ContextCompat.getColor(holder.context , R.color.white));
        }
        else
        {
            holder.date.setBackgroundResource(R.color.white);
            holder.date.setTextColor(ContextCompat.getColor(holder.context , R.color.black));

        }
    }

    @Override
    public int getItemCount() {
        return list == null ?0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder{


        Context context ;
        TextView date ;
        public Holder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext() ;
            date = itemView.findViewById(R.id.text_date);

            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positon = getAdapterPosition() ;
                    rowIndex=positon;
                    notifyDataSetChanged();
                    if (onTimeClick != null){
                        onTimeClick.onClick(list.get(positon).getTime());
                    }
                }
            });


        }
    }

    public interface OnTimeClick {
        void onClick (String time );

    }
}
