package com.hfm.vdc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import co.dift.ui.SwipeToAction;

public class DBListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView dataListRecycleView= (RecyclerView) findViewById(R.id.recycleView_datalist);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        dataListRecycleView.setLayoutManager(linearLayoutManager);
        dataListRecycleView.setHasFixedSize(true);
        Adapter adapter=new Adapter(SugarPerson.listAll(SugarPerson.class));
        SwipeToAction swipeToAction=new SwipeToAction(dataListRecycleView, new SwipeToAction.SwipeListener<SugarPerson>() {
            @Override
            public boolean swipeLeft(SugarPerson itemData) {
                Toast.makeText(DBListActivity.this,itemData.phone,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean swipeRight(SugarPerson itemData) {
                return false;
            }

            @Override
            public void onClick(SugarPerson itemData) {
                itemData.delete();
            }

            @Override
            public void onLongClick(SugarPerson itemData) {

            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
