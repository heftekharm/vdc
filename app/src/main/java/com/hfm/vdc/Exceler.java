package com.hfm.vdc;

import android.os.Environment;

import com.orm.SugarRecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by Hosein on 4/24/2017.
 */
public class Exceler  {

    public  static void WriteExcel(){
        Iterator<SugarPerson> iterator=SugarPerson.findAll(SugarPerson.class);
        try {
            File savePathFile=new File(Environment.getExternalStorageDirectory(),"visitors.xls");
            FileOutputStream outputStream=new FileOutputStream(savePathFile);
            WritableWorkbook writableWorkbook= Workbook.createWorkbook(outputStream);
            WritableSheet wsheet=writableWorkbook.createSheet("Visitors",0);
            int row=0;
            while(iterator.hasNext()){
                int col=0;
                SugarPerson sugarPerson=iterator.next();
                wsheet.addCell(new Label(col++,row,sugarPerson.fname));
                wsheet.addCell(new Label(col++,row,sugarPerson.lname));
                wsheet.addCell(new Label(col++,row,sugarPerson.age));
                wsheet.addCell(new Label(col++,row,sugarPerson.edu));
                wsheet.addCell(new Label(col++,row,sugarPerson.organ));
                wsheet.addCell(new Label(col++,row,sugarPerson.job));
                wsheet.addCell(new Label(col++,row,sugarPerson.phone));
                row++;
            }
            writableWorkbook.write();
            writableWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

}
