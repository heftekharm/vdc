package com.hfm.vdc;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

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

    public  static boolean WriteExcel(Context context){
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
            Toast.makeText(context,new StringBuilder("Successfully Generated At:").append(savePathFile.getCanonicalPath()),Toast.LENGTH_LONG).show();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context,new StringBuilder("Something Went Wrong!"),Toast.LENGTH_LONG).show();
        } catch (RowsExceededException e) {
            e.printStackTrace();
            Toast.makeText(context,new StringBuilder("Something Went Wrong!"),Toast.LENGTH_LONG).show();
        } catch (WriteException e) {
            e.printStackTrace();
            Toast.makeText(context,new StringBuilder("Something Went Wrong!"),Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,new StringBuilder("Something Went Wrong!"),Toast.LENGTH_LONG).show();
        }
        return false;
    }
    static class AsyncExceler extends AsyncTask<Void,Void,Exception>{
        private Context context;
        private File savePathFile;
        StringBuilder successMessag;
        public AsyncExceler(Context context){
            this.context=context;
        }
        @Override
        protected Exception doInBackground(Void... params) {
            Iterator<SugarPerson> iterator=SugarPerson.findAll(SugarPerson.class);
            try {
                savePathFile=new File(Environment.getExternalStorageDirectory(),"visitors.xls");
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
                successMessag=new StringBuilder("Successfully Generated At:").append(savePathFile.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context,new StringBuilder("Something Went Wrong!"),Toast.LENGTH_LONG).show();
            } catch (RowsExceededException e) {
                e.printStackTrace();
                Toast.makeText(context,new StringBuilder("Something Went Wrong!"),Toast.LENGTH_LONG).show();
            } catch (WriteException e) {
                e.printStackTrace();
                Toast.makeText(context,new StringBuilder("Something Went Wrong!"),Toast.LENGTH_LONG).show();
            }catch (Exception e){
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception e) {
            if(e!=null){
                Toast.makeText(context,new StringBuilder("Something Went Wrong!"),Toast.LENGTH_LONG).show();
            }else{
                    Toast.makeText(context,successMessag.toString(),Toast.LENGTH_LONG).show();

            }
        }
    }

}
