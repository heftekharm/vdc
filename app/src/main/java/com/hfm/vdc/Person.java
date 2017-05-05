package com.hfm.vdc;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.orm.dsl.Table;

/**
 * Created by Hosein on 4/24/2017.
 */
@Table
public class Person extends BaseObservable {
    private long dbId =-1;
    private String fname="";
    private String lname="";
    private String age="";
    private String edu="";
    private String organ="";
    private String job="";
    private String phone="";
    private String email = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public void clear(){
        fname = lname = age = edu = organ = job = phone = email = "";
        dbId=-1;
        notifyChange();
    }
    @Bindable
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
        notifyPropertyChanged(BR.fname);

    }

    @Bindable
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
        notifyPropertyChanged(BR.lname);

    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);

    }
    @Bindable
    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
        notifyPropertyChanged(BR.edu);

    }
    @Bindable
    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
        notifyPropertyChanged(BR.organ);

    }
    @Bindable
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
        notifyPropertyChanged(BR.job);

    }
    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);

    }
    @Bindable
    public long getDbId() {
        return dbId;
    }

    public void setDbId(long dbId) {
        this.dbId = dbId;
        notifyPropertyChanged(BR.dbId);

    }

    public void changeFieldsBySugarPerson(SugarPerson sugarPerson) {

        edu=sugarPerson.edu;
        lname=sugarPerson.lname;
        dbId=sugarPerson.getId();
        fname=sugarPerson.fname;
        job=sugarPerson.job;
        age=sugarPerson.age;
        organ=sugarPerson.organ;
        phone=sugarPerson.phone;
        email = sugarPerson.email;
        notifyChange();

    }

}

