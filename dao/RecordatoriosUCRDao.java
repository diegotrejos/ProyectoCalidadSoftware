package cr.ac.ucr.ecci.eseg.uhelper.infraestructure.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import androidx.room.OnConflictStrategy;



import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.RecordatoriosUCR;

@Dao
public interface RecordatoriosUCRDao {

    @Query("SELECT id, nombre, descripcion, dia, mes, anno, hora FROM RecordatoriosUCR WHERE id = :id")
    RecordatoriosUCR getRecordatorioUCR(int id);

    @Query("SELECT * FROM RecordatoriosUCR")
    List<RecordatoriosUCR> leer();

    @Query("SELECT * FROM RecordatoriosUCR WHERE mes >= :i1 AND mes <= :i2 ORDER BY mes,dia ")
    List<RecordatoriosUCR> semestre(int i1,int i2);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(RecordatoriosUCR... recordatoriosUCR);

}
