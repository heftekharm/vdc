package com.hfm.vdc;

import android.Manifest;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DBListActivity extends AppCompatActivity {
    private final int REQUEST_TO_WRITE_EXCEL_FILE = 2017;
    private final int REQUEST_TO_SHARE_EXCEL_FILE = 2018;

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        perpareRecycleView();
        FloatingActionButton save_as_excel = (FloatingActionButton) findViewById(R.id.save_as_excel);
        save_as_excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExcel();
            }
        });
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void perpareRecycleView() {
        final RecyclerView dataListRecycleView = (RecyclerView) findViewById(R.id.recycleView_datalist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        dataListRecycleView.setLayoutManager(linearLayoutManager);
        dataListRecycleView.setHasFixedSize(true);
        dataListRecycleView.setNestedScrollingEnabled(false);
        new AsyncTaskLoadingFromDB(this, dataListRecycleView).execute();
    }

    private void writeExcel(){
        boolean grant=checkWritingPermission(REQUEST_TO_WRITE_EXCEL_FILE);
        if (grant)
            Exceler.WriteExcel(this);

    }

    private void shareExcel() {
        boolean grant = checkWritingPermission(REQUEST_TO_SHARE_EXCEL_FILE);
        if (grant)
            Exceler.ShareExcel(this);
    }

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
            case REQUEST_TO_SHARE_EXCEL_FILE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    shareExcel();
                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dbcontent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_excel:
                writeExcel();
                break;
            case R.id.action_share_excel:
                shareExcel();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
