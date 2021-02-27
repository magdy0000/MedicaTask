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
import com.magdy.medicatask.data.models.BranchesModel;

import java.util.List;

public class RecyclerBranchesAdapter extends RecyclerView.Adapter<RecyclerBranchesAdapter.Holder> {

    private List<BranchesModel.ItemBean.DataBean> list ;

    private OnItemClick onItemClick ;
    public void setList(List<BranchesModel.ItemBean.DataBean> list) {
        this.list = list;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_branches, parent , false) ;
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name. setText(list.get(position).getInstitution_title());
        holder.rate. setText(list.get(position).getRate()+"");
        holder.specialise. setText(list.get(position).getSpecialty());
        Glide.with(holder.context).load(list.get(position).getImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder{
       TextView name ;
       TextView rate ;
       TextView specialise  ;
       ImageView image ;
       Context context ;

        public Holder(@NonNull View itemView) {
            super(itemView);
             context = itemView.getContext();
            name = itemView.findViewById(R.id.text_name);
            rate = itemView.findViewById(R.id.text_rate);
            specialise = itemView.findViewById(R.id.text_sp);
            image = itemView.findViewById(R.id.image_branch);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClick != null)
                        onItemClick.onClick(list.get(getAdapterPosition()).getBranch_id()
                                , list.get(getAdapterPosition()).getInstitution_id());
                }
            });
        }
    }

    public interface OnItemClick {

        void  onClick (int branchId ,  int institutionId) ;
    }
}
