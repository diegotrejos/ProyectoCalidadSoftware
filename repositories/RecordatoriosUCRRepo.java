package cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutionException;

import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.AppDatabase;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Contactos;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.RecordatoriosUCR;

public class RecordatoriosUCRRepo {
    AppDatabase db;
    List<RecordatoriosUCR> recordatoriosUCRLista;

    public RecordatoriosUCRRepo(Context context){this.db = AppDatabase.getInstance(context); }

    public RecordatoriosUCRRepo(){};

    public List<RecordatoriosUCR> getRecordatoriosUCR(){
        try {
            recordatoriosUCRLista = new getRecordatoriosUCRAsync().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return recordatoriosUCRLista;
    }

    public List<RecordatoriosUCR> getSemestre1RecordatoriosUCR(){
        try {
            recordatoriosUCRLista = new getSemestre1RecordatoriosUCRAsync().execute().get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return recordatoriosUCRLista;
    }
    public List<RecordatoriosUCR> getSemestre2RecordatoriosUCR(){
        try {
            recordatoriosUCRLista = new getSemestre2RecordatoriosUCRAsync().execute().get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return recordatoriosUCRLista;
    }
    public List<RecordatoriosUCR> getSemestre3RecordatoriosUCR(){
        try {
            recordatoriosUCRLista = new getSemestre3RecordatoriosUCRAsync().execute().get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return recordatoriosUCRLista;
    }


    private class getRecordatoriosUCRAsync extends AsyncTask<Void, Void, List<RecordatoriosUCR>> {
        @Override
        protected List<RecordatoriosUCR> doInBackground(Void... voids) { return db.recordatoriosUCRDao().leer(); }
    }

    private class getSemestre1RecordatoriosUCRAsync extends AsyncTask<Void, Void, List<RecordatoriosUCR>> {
        @Override
        protected List<RecordatoriosUCR> doInBackground(Void... voids) { return db.recordatoriosUCRDao().semestre(3,7); }
    }
    private class getSemestre2RecordatoriosUCRAsync extends AsyncTask<Void, Void, List<RecordatoriosUCR>> {
        @Override
        protected List<RecordatoriosUCR> doInBackground(Void... voids) { return db.recordatoriosUCRDao().semestre(8,12); }
    }
    private class getSemestre3RecordatoriosUCRAsync extends AsyncTask<Void, Void, List<RecordatoriosUCR>> {
        @Override
        protected List<RecordatoriosUCR> doInBackground(Void... voids) { return db.recordatoriosUCRDao().semestre(1,2); }
    }



    public void insertRecordatoriosUCR(RecordatoriosUCR recordatoriosUCR){
        new insertRecordatoriosUCRAsync().execute(recordatoriosUCR);
    }

    private class insertRecordatoriosUCRAsync extends AsyncTask<RecordatoriosUCR, Void, Void> {
        @Override
        protected Void doInBackground(RecordatoriosUCR... recordatoriosUCR) {
            db.recordatoriosUCRDao().insertar(recordatoriosUCR[0]);
            return null;
        }
    }


    public void setDb(Context context) {
        db = AppDatabase.getInstance(context);
    }
}