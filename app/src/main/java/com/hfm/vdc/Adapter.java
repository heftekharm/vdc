package com.hfm.vdc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hosein on 4/25/2017.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static int EDIT = 10;
    public final static int DEL = 11;
    ArrayList<SugarPerson> dataList;
    Activity activity;
    private boolean removingitem = false;

    public Adapter(List<SugarPerson> dataList, Activity activity) {
        this.dataList = new ArrayList(dataList);
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SugarPerson sugarPerson = dataList.get(position);
        Holder hldr = (Holder) holder;
        hldr.nameTextView.setText(new StringBuilder(sugarPerson.fname).append(' ').append(sugarPerson.lname));
        hldr.deleteButton.setOnClickListener(new ClickListener(DEL, position));
        hldr.editButton.setOnClickListener(new ClickListener(EDIT, position));


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void removeItem(Integer pos) {
        if (!removingitem) {
            removingitem = true;
            final SugarPerson sugarPerson = dataList.get(pos);
            if (sugarPerson.delete()) {
                dataList.remove(pos.intValue());
                notifyItemRemoved(pos);
            } else {
                Toast.makeText(activity, "مشکلی رخ داد", Toast.LENGTH_SHORT).show();
            }
        }
        removingitem = false;

    }

    @Override
    public long getItemId(int position) {
        return dataList.get(position).getId(); //super.getItemId(position);
    }

    //List<SugarPerson> dataList;
    class Holder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        Button deleteButton;
        Button editButton;
        ImageView iconImageView;

        public Holder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.item_name);
            deleteButton = (Button) v.findViewById(R.id.list_item_delete);
            editButton = (Button) v.findViewById(R.id.list_item_edit);
        }
    }

    class ClickListener implements Button.OnClickListener {
        private int action;
        private int pos;

        public ClickListener(int action, int pos) {
            this.action = action;
            this.pos = pos;
        }

        @Override
        public void onClick(View v) {
            switch (action) {
                case EDIT: {
                    Intent intent = new Intent();
                    long id = getItemId(pos);
                    intent.putExtra(Statics.ITEM_ID_RETURNED_BY_SWIPE_RIGHT, id);
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();
                    break;
                }
                case DEL: {
                    removeItem(pos);
                }

            }
        }
    }


}
