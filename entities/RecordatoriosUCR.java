package cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class RecordatoriosUCR implements Parcelable {
    @PrimaryKey(autoGenerate = true) @NonNull
  public int id;
    String nombre;
    String descripcion;
    // Fecha
    int dia;
    int mes;
    int anno;
    // Hora
    String hora;

public RecordatoriosUCR(String nombre, String descripcion, int dia, int mes,int anno, String hora) {

   this.nombre = nombre;
   this.descripcion = descripcion;
   this.dia = dia;
   this.mes = mes;
   this.anno = anno;
   this.hora = hora;
}

    @Ignore // To ignore this constructor in room implementation.
    public RecordatoriosUCR(int id, String nombre, String descripcion, int dia, int mes,int anno, String hora) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dia = dia;
        this.mes = mes;
        this.anno = anno;
        this.hora = hora;
    }
    public RecordatoriosUCR(){}

    protected RecordatoriosUCR(Parcel in) {

        nombre = in.readString();
        descripcion = in.readString();
        dia = in.readInt();
        mes = in.readInt();
        anno = in.readInt();
        hora = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeInt(dia);
        dest.writeInt(mes);
        dest.writeInt(anno);
        dest.writeString(hora);
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<RecordatoriosUCR> CREATOR = new Creator<RecordatoriosUCR>() {
        @Override
        public RecordatoriosUCR createFromParcel(Parcel in) {return new RecordatoriosUCR(in);}

        @Override
        public RecordatoriosUCR[] newArray(int size) { return new RecordatoriosUCR[size]; }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public static Creator<RecordatoriosUCR> getCREATOR() {
        return CREATOR;
    }

}