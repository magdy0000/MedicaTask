package com.magdy.medicatask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.magdy.medicatask.R;
import com.magdy.medicatask.data.models.SpecialiseModel;

import java.util.List;

public class RecyclerAdapterSpecialises extends RecyclerView.Adapter<RecyclerAdapterSpecialises.Holer> {

    private List<SpecialiseModel.ItemBean.DataBean> list ;
    private OnItemClick onItemClick ;
    public void setList(List<SpecialiseModel.ItemBean.DataBean> list) {
        this.list = list;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylcer_specializes, parent , false ) ;

        return new Holer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holer holder, int position) {

        holder.name.setText(list.get(position).getTitle());
        Glide.with(holder.context).load(list.get(position).getImage()).into(holder.imageView) ;

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Holer extends RecyclerView.ViewHolder{

        TextView name  ;
        ImageView imageView ;
        Context context ;
        public Holer(@NonNull View itemView) {
            super(itemView);
            context  = itemView.getContext();
            name = itemView.findViewById(R.id.text_specialise_name);
            imageView = itemView.findViewById(R.id.image_specialise);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClick != null)
                    onItemClick.onClick(list.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface OnItemClick{
        void onClick(int specialiseId);

    }
}
