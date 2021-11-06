package cr.ac.ucr.ecci.eseg.uhelper.infraestructure;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.AulasDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.AutenticacionDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.ContactosDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.CursoDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.EdificiosDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.HorarioDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.PerfilDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.RecordatoriosDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.RecordatoriosUCRDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao.TiempoClaseDao;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Aulas;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Autenticacion;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Contactos;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Curso;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Edificios;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Horario;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Perfil;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Recordatorios;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.RecordatoriosUCR;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.TiempoClase;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.scripts.PostDeployment;

@Database(entities = {Perfil.class, Contactos.class, Autenticacion.class, Curso.class,
        Edificios.class, Aulas.class, Horario.class, TiempoClase.class, Recordatorios.class, RecordatoriosUCR.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PerfilDao perfilDao();

    public abstract ContactosDao contactosDao();

    public abstract AutenticacionDao autenticacionDao();

    public abstract HorarioDao horarioDao();

    public abstract CursoDao cursoDao();

    public abstract TiempoClaseDao tiempoClaseDao();

    public abstract EdificiosDao edificiosDao();

    public abstract AulasDao aulasDao();

    public abstract RecordatoriosDao recordatoriosDao();

    public abstract RecordatoriosUCRDao recordatoriosUCRDao();

    private static AppDatabase INSTANCE;

    public synchronized static AppDatabase getInstance(Context context) {

        //Para reiniciar la base, descomentar esto y volver a comentar despues de correrlo 1 vez
//           context.deleteDatabase("Uhelper.db");

        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "Uhelper.db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                PostDeployment.run(context);
                            }
                        });
                    }
                })
                .build();
    }
}
