package com.hfm.vdc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.dift.ui.SwipeToAction;

/**
 * Created by Hosein on 4/25/2017.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<SugarPerson> dataList;
    class Holder extends SwipeToAction.ViewHolder<SugarPerson>{
        TextView nameTextView;
        ImageView iconImageView;
        public Holder(View v) {
            super(v);
            nameTextView= (TextView) v.findViewById(R.id.item_name);
        }
    }

    public Adapter(List<SugarPerson> dataList){
        this.dataList=dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.front_item,parent);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SugarPerson sugarPerson=dataList.get(position);
        Holder hldr=(Holder)holder;
        hldr.nameTextView.setText(new StringBuilder(sugarPerson.fname).append(sugarPerson.lname));
        hldr.data=sugarPerson;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
