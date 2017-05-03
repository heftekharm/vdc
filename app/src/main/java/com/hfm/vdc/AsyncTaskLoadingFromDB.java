package com.hfm.vdc;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

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
        final Adapter adapter=new Adapter(list,activity);
        dataListRecycleView.setAdapter(adapter);
        activity.findViewById(R.id.progressbar).setVisibility(View.GONE);
        dataListRecycleView.setVisibility(View.VISIBLE);

    }
}
