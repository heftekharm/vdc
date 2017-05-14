package com.hfm.vdc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ShareCompat;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static void WriteExcel(Activity activity) {
        new AsyncExceler(activity, null).execute();
    }

    public static void ShareExcel(Activity activity) {
        new AsyncExceler(activity, new RunAfterAsync() {
            @Override
            public void run(AsyncExceler asyncExceler) {
                Intent intent = ShareCompat.IntentBuilder.from(asyncExceler.getActivity()).setType("application/vnd.ms-excel").setStream(asyncExceler.getsavePathFileUri()).setChooserTitle("مسیر فرستادن فایل").getIntent();
                if (intent.resolveActivity(asyncExceler.getActivity().getPackageManager()) != null) {
                    asyncExceler.getActivity().startActivity(intent);
                }
            }
        }).execute();
    }

    static interface RunAfterAsync {
        void run(AsyncExceler asyncExceler);
    }

    static class AsyncExceler extends AsyncTask<Void,Void,Exception>{
        StringBuilder successMessag;
        RunAfterAsync runInPostExecute;
        private Activity activity;
        private File savePathFile;

        public AsyncExceler(Activity activity, RunAfterAsync runInPostExecute) {
            this.activity = activity;
            this.runInPostExecute = runInPostExecute;
        }

        public Activity getActivity() {
            return activity;
        }

        ;

        public Uri getsavePathFileUri() {
            return savePathFile != null ? Uri.fromFile(savePathFile) : null;
        }

        ;
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
                successMessag = new StringBuilder(" مسیر فایل اکسل: ").append(savePathFile.getCanonicalPath());
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return e;
            } catch (RowsExceededException e) {
                e.printStackTrace();
                return e;
            } catch (WriteException e) {
                e.printStackTrace();
                return e;
            }catch (Exception e){
                return e;
            }
        }

        @Override
        protected void onPostExecute(Exception e) {
            if(e!=null){
                Toast.makeText(activity, new StringBuilder("مانعی وجود دارد!"), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(activity, successMessag.toString(), Toast.LENGTH_LONG).show();
                if (runInPostExecute != null) {
                    runInPostExecute.run(this);
                }
            }
        }
    }

}
