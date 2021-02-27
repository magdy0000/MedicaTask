package com.magdy.medicatask.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.magdy.medicatask.R;
import com.magdy.medicatask.data.models.ModelAppointments;

import java.util.List;


public class RecyclerDatesAdapter extends RecyclerView.Adapter<RecyclerDatesAdapter.Holder> {


    private int rowIndex = -1;
    private List<ModelAppointments.ItemBean.DataBean.DatesBean.DataBean2> list ;
    private static OnDateClick onDateClick ;
    private int dayNumber ;


    public void setOnDateClick(OnDateClick onDateClick) {
        this.onDateClick = onDateClick;
    }

     void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    void setList(List<ModelAppointments.ItemBean.DataBean.DatesBean.DataBean2> list ) {
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
            holder.date.setText(list.get(position).getDate());


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

                    if (onDateClick != null){
                        onDateClick.onClick(list.get(positon).getDate() , dayNumber);
                    }
                    notifyDataSetChanged();
                }
            });


        }
    }

   public interface OnDateClick {
        void onClick (String date , int dayNumber);

    }
}
