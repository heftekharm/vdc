package com.hfm.vdc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hfm.vdc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    Person person;
    ActivityMainBinding binding;
    //EditText editText_fname,editText_lname,editText_age,editText_edu,editText_organ,editText_job,editText_phone;
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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExcel();
            }
        });
    }
    private void writeExcel(){
        boolean grant=checkWritingPermission(REQUEST_TO_WRITE_EXCEL_FILE);
        if (grant) Exceler.WriteExcel();
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
        Toast.makeText(this, sugarPerson.getId().toString(),Toast.LENGTH_SHORT).show();
    }
    public void correct(View v){
        setDataOnPerson();
        SugarPerson sugarPerson =new SugarPerson(person);
        sugarPerson.setId(person.getDbId());
        sugarPerson.save();
    }
    public void remove(View v){

    }
    public void clear(View v){
        //Exceler.WriteExcel();
       //Toast.makeText(this, SugarPerson.listAll(SugarPerson.class).size(),Toast.LENGTH_SHORT).show();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
