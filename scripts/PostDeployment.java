package cr.ac.ucr.ecci.eseg.uhelper.infraestructure.scripts;

import android.content.Context;

import cr.ac.ucr.ecci.eseg.uhelper.components.Singleton;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Aulas;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Edificios;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Recordatorios;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.RecordatoriosUCR;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.firebase_repositories.FBAulasRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.firebase_repositories.FBAutenticacionRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.firebase_repositories.FBCursosRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.firebase_repositories.FBEdificiosRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.firebase_repositories.FBHorarioRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.firebase_repositories.FBPerfilRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.firebase_repositories.FBTiempoClaseRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories.AulasRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories.ContactosRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories.EdificiosRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.firebase_repositories.FBContactsRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories.MisRecordatoriosRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories.RecordatoriosUCRRepo;

public class PostDeployment {

    public static void run(Context context){
        //Setting repos
        ContactosRepo contactosRepo = Singleton.getContactosRepo(context);
        EdificiosRepo edificiosRepo = Singleton.getEdificiosRepo(context);
        AulasRepo aulasRepo = Singleton.getAulasRepo(context);
        RecordatoriosUCRRepo recordatoriosUCRRepo = Singleton.getRecordatoriosUCRRepo(context);
        FBContactsRepo fbContactsRepo = Singleton.getfbContactsRepo();
        FBEdificiosRepo fbEdificiosRepo = Singleton.getfbEdificiosRepo();
        FBAulasRepo fbAulasRepo = Singleton.getfbAulasRepo();
        FBAutenticacionRepo fbAutenticacionRepo = Singleton.getFBAutenticacionRepo();
        FBPerfilRepo fbPerfilRepo = Singleton.getFBPerfilRepo();
        FBCursosRepo fbCursosRepo = Singleton.getFBCursoRepo();
        FBHorarioRepo fbHorarioRepo = Singleton.getfbHorarioRepo();
        FBTiempoClaseRepo fbTiempoClaseRepo = Singleton.getFBTiempoClaseRepo();

        // Insertar usuarios
        fbAutenticacionRepo.getUsersFromFirebase(Singleton.getAutenticacionRepo(context));

        // Insertar perfil
        fbPerfilRepo.getPerfilesFromFirebase(Singleton.getPerfilRepo(context));

        // Insertar horarios
        fbHorarioRepo.getHorariosFromFirebase(Singleton.getHorarioRepo(context));

        // Insertar cursos
        fbCursosRepo.getCursosFromFirebase(Singleton.getCursoRepo(context));

        // Insertar tiempo clase
        fbTiempoClaseRepo.getTiempoClasesFromFirebase(Singleton.getTiempoClaseRepo(context));

        //insertar Recordatorios UCR
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(1,"Inicia primer ciclo 2021","Inicia el primer ciclo lectivo", 5,4,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(2,"Guía de Cursos y Horarios","Publicación de guia de horarios", 16,4,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(9,"Prematrícula"," Arranca Prematricula web en linea hasta el 26 de abril", 23,4,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(4,"Publicacion de citas","Se publican las citas de matricula ordinaria", 27,4,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(5,"Matricula en Línea","Arranca matricula web hasta el 29 de abril", 28,4,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(6,"Renuncia de Cursos","Renuncia de cursos sin costo adicional", 30,4,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(7,"Inicio de Lecciones","Arranca ciclo lectivo", 30,4,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(8,"Retiro de matricula","Retiro de cursos con costos y la beca no los cubre, hasta el 7 de mayo", 30,4,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(3,"Publicación de cupos para inclusión","Se publican fechas y cursos disponibles para la matricula por inclusion", 4,5,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(10,"Prematrícula de inclusión","Prematricula web para la matricula de inclusion dura hasta el 6 de mayo", 5,5,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(11,"Publicación de citas para inclusión","Se publican citas de matricula de inclusion", 7,5,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(12,"Matricula de Inclusión","Arranca matricual de inclusion web hasta el 11 de mayo", 10,5,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(13,"Fin del primer Ciclo 2021","Termina el primer ciclo lectivo", 24,7,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(14,"Empieza  interciclo de Agosto","Arranca periodo de vacaciones", 26,7,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(15,"Publicacion de cursos y Horarios","Publicacion de guía de horarios", 28,7,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(16,"Prematrícula","Arranca Prematricula web en linea hasta el 4 de agosto", 30,7,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(17,"Publicacion de citas","Se publican las citas de matricula ordinaria", 5,8,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(18,"Matricula en Línea","Arranca matricula web hasta el 12 de agosto", 9,8,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(19,"Renuncia de Cursos","Renuncia de cursos sin costo adicional", 13,8,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(20,"Retiro de matricula","Retiro de cursos con costos y la beca no los cubre, hasta el 21 de agosto", 16,8,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(21,"Fin del  interciclo de Agosto"," Termina periodo de vacaciones", 14,8,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(22,"Empieza Segundo Ciclo 2021","Arranca el segundo ciclo lectivo", 16,8,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(23,"Fin del segundo Ciclo 2021","Termina el segundo ciclo", 4,12,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(24,"Empieza Interciclo de Diciembre ","Arranca Periodo de vacaciones", 6,12,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(25,"Fin de Interciclo de Diciembre","Termina periodo de vacaciones", 17,12,2021,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(26,"Inicia tercer ciclo 2022","Arranque de tercer ciclo", 3,1,2022,"12:00"));
        recordatoriosUCRRepo.insertRecordatoriosUCR(new RecordatoriosUCR(27,"Termina tercer ciclo 2022","Termina tercer ciclo", 26,2,2022,"12:00"));



        // Insertar contactos
        fbContactsRepo.getContactsFromFirebase(contactosRepo);
//        fbContactsRepo.setCurrentContacts(); // Sets all contacts to the firebase db. Uncomment if the contacts in the firebase are accidentally deleted.

        // Insertar edificios
        fbEdificiosRepo.getEdificiosFromFirebase(edificiosRepo);
//        fbEdificiosRepo.setCurrentEdificios(); // Sets all edificios to the firebase db. Uncomment if the edificios in the firebase are accidentally deleted.

        // Insertar aulas
        fbAulasRepo.getAulasFromFirebase(aulasRepo);
//        fbAulasRepo.setCurrentAulas(); // Sets all aulas to the firebase db. Uncomment if the aulas in the firebase are accidentally deleted.
    }

}
