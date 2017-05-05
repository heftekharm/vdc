package com.hfm.vdc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by Hosein on 4/26/2017.
 */
public class AsyncTaskLoadingFromDB extends android.os.AsyncTask<Void, Void, Adapter> {
    RecyclerView dataListRecycleView;
    Activity activity;
    ProgressDialog progressDialog;

    public AsyncTaskLoadingFromDB(Context context,RecyclerView recyclerView){
    this.dataListRecycleView=recyclerView;
        this.activity= (Activity) context;
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("صبر کنید...");
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Adapter doInBackground(Void... params) {
        List list = SugarPerson.listAll(SugarPerson.class);
        final Adapter adapter = new Adapter(list, activity);
        return adapter;
    }

    @Override
    protected void onPostExecute(Adapter adapter) {
        super.onPostExecute(adapter);
        dataListRecycleView.setAdapter(adapter);
        dataListRecycleView.setVisibility(View.VISIBLE);
    }
}
