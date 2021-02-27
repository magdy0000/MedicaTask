package com.magdy.medicatask.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magdy.medicatask.R;
import com.magdy.medicatask.data.models.ModelAppointments;
import com.magdy.medicatask.utils.DayHelper;

import java.util.List;

public class RecyclerAdapterDays extends RecyclerView.Adapter<RecyclerAdapterDays.Holder> {

    int row_index   ;
    private RecyclerDatesAdapter adapter = new RecyclerDatesAdapter();
    private List<ModelAppointments.ItemBean.DataBean> list ;

    public void setList(List<ModelAppointments.ItemBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reycler_days , parent , false) ;

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.dayName.setText(DayHelper.days[list.get(position).getDay_number()]);

        if(row_index==position){

            adapter.setList(list.get(position).getDates().getData());
            holder.dates.setAdapter(adapter);
            adapter.setDayNumber(list.get(position).getDay_number());
            holder.dates.setVisibility(View.VISIBLE);
        }
        else
        {
          holder.dates.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {
        return list== null ? 0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder{


        TextView dayName ;
        RecyclerView dates ;

        public Holder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.text_day) ;
            dates  = itemView.findViewById(R.id.recycler_dates) ;
            dayName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    row_index= position;
                    adapter.setDayNumber(list.get(position).getDay_number());
                    notifyDataSetChanged();
                }
            });


        }
    }
}
