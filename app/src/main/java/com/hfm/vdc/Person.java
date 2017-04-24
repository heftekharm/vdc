package com.hfm.vdc;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.hfm.vdc.BR;
import com.orm.dsl.Table;

/**
 * Created by Hosein on 4/24/2017.
 */
@Table
public class Person extends BaseObservable {
    //final String add="اضافه",update="تصحیح", remove="حذف";
    private long dbId =-1;
    private String fname="نام";
    private String lname="نام خانوادگی";
    private String age="سن";
    private String edu="تحصیلات";
    private String organ="سازمان";
    private String job="شغل";
    private String phone="شماره تلفن";

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

}

