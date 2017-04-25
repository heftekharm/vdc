package com.hfm.vdc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;

/**
 * Created by Hosein on 4/25/2017.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<SugarPerson> dataList;
    //List<SugarPerson> dataList;
    class Holder extends SwipeToAction.ViewHolder<Integer>{
        TextView nameTextView;
        ImageView iconImageView;
        public Holder(View v) {
            super(v);
            nameTextView= (TextView) v.findViewById(R.id.item_name);
        }
    }

    public Adapter(List<SugarPerson> dataList){
        //this.dataList=dataList;
        this.dataList=new ArrayList(dataList);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SugarPerson sugarPerson=dataList.get(position);
        Holder hldr=(Holder)holder;
        hldr.nameTextView.setText(new StringBuilder(sugarPerson.fname).append(' ').append(sugarPerson.lname));
        hldr.data=position;

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void removeItem(Integer pos){
        SugarPerson sugarPerson= dataList.get(pos);
        dataList.remove(pos.intValue());
        notifyItemRemoved(pos);
        sugarPerson.delete();
    }

    @Override
    public long getItemId(int position) {
        return dataList.get(position).getId(); //super.getItemId(position);
    }
}
