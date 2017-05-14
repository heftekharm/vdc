package com.hfm.vdc;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hosein on 4/25/2017.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static int EDIT = 10;
    public final static int DEL = 11;
    public final static int EXPAND = 12;
    ArrayList<SugarPerson> dataList;
    Activity activity;

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
        hldr.nameTextView.setText(String.format("%s %s", sugarPerson.fname, sugarPerson.lname));
        hldr.setClickListeners(position);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    @Override
    public long getItemId(int position) {
        return dataList.get(position).getId(); //super.getItemId(position);
    }


    class Holder extends RecyclerView.ViewHolder {
        //Views
        TextView nameTextView;
        Button deleteButton;
        Button editButton;
        View expandButton;
        LinearLayoutCompat expandableItemsContainer;
        ExpandableLayout expandableItemsContainerExpandableLayout;
        ObjectAnimator expandButtonAnimator;
        //non-Views
        private SugarPerson sugarPerson;
        public Holder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.item_name);
            deleteButton = (Button) v.findViewById(R.id.list_item_delete);
            editButton = (Button) v.findViewById(R.id.list_item_edit);
            expandButton = v.findViewById(R.id.expand_Button);
            expandButtonAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(expandButton.getContext(), R.animator.rotate180);
            expandButtonAnimator.setTarget(expandButton);
            expandButtonAnimator.setInterpolator(new AccelerateInterpolator());
            expandableItemsContainer = (LinearLayoutCompat) v.findViewById(R.id.expandableItemsContainer);
            expandableItemsContainerExpandableLayout = (ExpandableLayout) v.findViewById(R.id.expandableItemsContainerExpandableLayout);
        }

        public void setClickListeners(int position) {
            sugarPerson = dataList.get(position);
            deleteButton.setOnClickListener(new ClickListener(DEL));
            editButton.setOnClickListener(new ClickListener(EDIT));
            expandButton.setOnClickListener(new ClickListener(EXPAND));
        }

        private void setExpandableItemsContainerItems() {
            ((TextView) expandableItemsContainer.findViewWithTag("phone")).setText(sugarPerson.phone);
            ((TextView) expandableItemsContainer.findViewWithTag("age")).setText(sugarPerson.age);
            ((TextView) expandableItemsContainer.findViewWithTag("organ")).setText(sugarPerson.organ);
            ((TextView) expandableItemsContainer.findViewWithTag("edu")).setText(sugarPerson.edu);
            ((TextView) expandableItemsContainer.findViewWithTag("email")).setText(sugarPerson.email);
            ((TextView) expandableItemsContainer.findViewWithTag("job")).setText(sugarPerson.job);
        }

        private void expand() {
            if (!expandableItemsContainerExpandableLayout.isExpanded()) {
                setExpandableItemsContainerItems();
                expandableItemsContainerExpandableLayout.expand();
                expandButtonAnimator.start();
            } else {
                expandableItemsContainerExpandableLayout.collapse();
                expandButtonAnimator.reverse();
            }
        }

        class ClickListener implements Button.OnClickListener {
            private int action;

            public ClickListener(int action) {
                this.action = action;
            }

            @Override
            public void onClick(View v) {
                switch (action) {
                    case EDIT: {
                        Intent intent = new Intent();
                        long id = sugarPerson.getId();
                        intent.putExtra(Statics.ITEM_ID_RETURNED_BY_SWIPE_RIGHT, id);
                        activity.setResult(Activity.RESULT_OK, intent);
                        activity.finish();
                        break;
                    }
                    case DEL: {
                        if (sugarPerson.delete()) {
                            int pos = dataList.indexOf(sugarPerson);
                            dataList.remove(pos);
                            notifyItemRemoved(pos);
                        } else {
                            Toast.makeText(activity, "مشکلی رخ داد", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case EXPAND: {
                        expand();
                    }
                }
            }
        }


    }


}
