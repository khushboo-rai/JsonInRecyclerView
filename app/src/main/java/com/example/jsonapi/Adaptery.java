package com.example.jsonapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {

    private Context mContext;
    private List<Modelclass> mData;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public Adaptery(Context mContext, List<Modelclass> mData,RecyclerViewClickInterface recyclerViewClickInterface) {
        this.mContext = mContext;
        this.mData = mData;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.ID.setText(mData.get(position).getId());
        holder.CITY_NAME.setText(mData.get(position).getCity_name());

        holder.CITY_CODE.setText(mData.get(position).getCity_code());

        holder.USER_ID.setText(mData.get(position).getUser_id());

        holder.CREATED_ON.setText(mData.get(position).getCreated_at());

        holder.UPDATED_ON.setText(mData.get(position).getUpdated_at());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ID, CITY_NAME,CITY_CODE,USER_ID,CREATED_ON,UPDATED_ON,Filter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ID = itemView.findViewById(R.id.id);
            CITY_NAME = itemView.findViewById(R.id.name);
            CITY_CODE = itemView.findViewById(R.id.code);
            USER_ID = itemView.findViewById(R.id.user);
            CREATED_ON = itemView.findViewById(R.id.created);
            UPDATED_ON = itemView.findViewById(R.id.upadate);
//            Filter = itemView.findViewById(R.id.filter);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemClick(getAbsoluteAdapterPosition());

                }
            });
            itemView.setOnLongClickListener(view -> {
                recyclerViewClickInterface.onLongItemClick(getAbsoluteAdapterPosition());
                return true;
            });






        }

    }

    public void filterList(ArrayList<Modelclass> filteredList) {
        mData = filteredList;
        notifyDataSetChanged();
    }

}
