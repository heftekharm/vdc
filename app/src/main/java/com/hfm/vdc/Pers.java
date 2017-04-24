package com.hfm.vdc;

import android.content.Context;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Hosein on 4/24/2017.
 */
@Table
public class Pers extends SugarRecord {
    public String fname;
    public String lname;
    public String age;
    public String edu;
    public String organ;
    public String job;
    public String phone;

    public Pers(){
    }
    public Pers(Person person){
        fname=person.getFname();
        lname=person.getLname();
        age=person.getAge();
        edu=person.getEdu();
        organ=person.getOrgan();
        job=person.getJob();
        phone=person.getPhone();
    }

}
