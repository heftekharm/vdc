package com.hfm.vdc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.dift.ui.SwipeToAction;

/**
 * Created by Hosein on 4/26/2017.
 */
public class AsyncTaskLoadingFromDB extends android.os.AsyncTask<Void,Void,List> {
    RecyclerView dataListRecycleView;
    Activity activity;
    public AsyncTaskLoadingFromDB(Context context,RecyclerView recyclerView){
    this.dataListRecycleView=recyclerView;
        this.activity= (Activity) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected List doInBackground(Void... params) {

        return SugarPerson.listAll(SugarPerson.class);
    }

    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
        final Adapter adapter=new Adapter(list);
        dataListRecycleView.setAdapter(adapter);
        activity.findViewById(R.id.progressbar).setVisibility(View.GONE);
        dataListRecycleView.setVisibility(View.VISIBLE);
        SwipeToAction swipeToAction=new SwipeToAction(dataListRecycleView, new SwipeToAction.SwipeListener<Integer>() {
            @Override
            public boolean swipeLeft(Integer pos) {
                adapter.removeItem(pos);
                return false;
            }

            @Override
            public boolean swipeRight(Integer pos) {
                Intent intent=new Intent();
                long id=adapter.getItemId(pos);
                intent.putExtra(Statics.ITEM_ID_RETURNED_BY_SWIPE_RIGHT,id);
                activity.setResult(Activity.RESULT_OK,intent);
                activity.finish();
                return true;
            }

            @Override
            public void onClick(Integer itemData) {

            }

            @Override
            public void onLongClick(Integer itemData) {

            }


        });
    }
}
