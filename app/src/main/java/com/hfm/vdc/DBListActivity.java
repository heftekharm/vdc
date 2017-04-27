package com.hfm.vdc;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import co.dift.ui.SwipeToAction;

public class DBListActivity extends AppCompatActivity {
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final RecyclerView dataListRecycleView= (RecyclerView) findViewById(R.id.recycleView_datalist);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        dataListRecycleView.setLayoutManager(linearLayoutManager);
        dataListRecycleView.setHasFixedSize(true);
        new AsyncTaskLoadingFromDB(this,dataListRecycleView).execute();
        FloatingActionButton save_as_excel = (FloatingActionButton) findViewById(R.id.save_as_excel);
        save_as_excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExcel();
            }
        });

    }
    private void writeExcel(){
        boolean grant=checkWritingPermission(REQUEST_TO_WRITE_EXCEL_FILE);
        if (grant)
            Exceler.WriteExcel(this);

    }

    private final int REQUEST_TO_WRITE_EXCEL_FILE=2017;
    private  boolean checkWritingPermission(int reqcode){
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck== PackageManager.PERMISSION_GRANTED) return true;
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},reqcode);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_TO_WRITE_EXCEL_FILE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    writeExcel();
                }
            }

        }
    }
}
