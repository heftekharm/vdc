package com.hfm.vdc;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hfm.vdc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    Person person;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        person=new Person();
        binding.setPerson(person);
        prepareSomeUIElements();

    }


    private void prepareSomeUIElements(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save_as_excel);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextActivity();
            }
        });
        ((AppCompatButton)findViewById(R.id.button_correct)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct(v);
            }
        });

        ((AppCompatButton)findViewById(R.id.button_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(v);
            }
        });

            ((AppCompatButton)findViewById(R.id.button_remove)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(v);
                }
            });
        ((AppCompatImageButton)findViewById(R.id.clear_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.clear();
            }
        });

    }
    private void goToNextActivity(){
        Intent intent=new Intent(MainActivity.this,DBListActivity.class);
        startActivityForResult(intent,Statics.REQ_RECORD_ID);
    }
    private void setDataOnPerson(){
        person.setFname(binding.editTextFname.getText().toString());
        person.setLname(binding.editTextLname.getText().toString());
        person.setAge(binding.editTextAge.getText().toString());
        person.setEdu(binding.editTextEdu.getText().toString());
        person.setOrgan(binding.editTextOrgan.getText().toString());
        person.setJob(binding.editTextJob.getText().toString());
        person.setPhone(binding.editTextPhone.getText().toString());
    }
    public void add(View v){
        setDataOnPerson();
        SugarPerson sugarPerson =new SugarPerson(person);
        sugarPerson.save();
        person.clear();
        Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();
    }
    public void correct(View v){
        setDataOnPerson();
        SugarPerson sugarPerson =new SugarPerson(person);
        sugarPerson.setId(person.getDbId());
        sugarPerson.save();
    }
    public void remove(View v){
        SugarPerson.findById(SugarPerson.class,person.getDbId()).delete();
        person.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getGroupId()){
            case R.id.action_observe_db:
                goToNextActivity();
                return true;
            case R.id.action_clear_fields:
                person.clear();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Statics.REQ_RECORD_ID:
                if(resultCode== Activity.RESULT_OK){
                    SugarPerson sugarPerson=SugarPerson.findById(SugarPerson.class,data.getLongExtra(Statics.ITEM_ID_RETURNED_BY_SWIPE_RIGHT,0));
                    person.chageFieldsBySugarPerson(sugarPerson);
                }


        }
    }
}
