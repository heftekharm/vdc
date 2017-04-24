package com.hfm.vdc;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    public  static void WriteExcel(Context context){
        try {
            File savePathFile=new File(Environment.getExternalStorageDirectory(),"visitors.xls");
            FileOutputStream outputStream=new FileOutputStream(savePathFile);
            WritableWorkbook writableWorkbook= Workbook.createWorkbook(outputStream);
            WritableSheet wsheet=writableWorkbook.createSheet("Visitors",0);
            wsheet.addCell(new Label(1,1,"شیت"));
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
