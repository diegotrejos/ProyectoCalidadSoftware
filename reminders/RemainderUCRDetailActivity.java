package cr.ac.ucr.ecci.eseg.uhelper.app.reminders;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import cr.ac.ucr.ecci.eseg.uhelper.R;
import cr.ac.ucr.ecci.eseg.uhelper.components.drawer.DrawerActivity;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Recordatorios;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.RecordatoriosUCR;

public class RemainderUCRDetailActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder_ucrdetail);
        getSupportActionBar().setTitle("Información del recodatorio");
        //To show and set drawer
        setDrawer(R.id.remainder_detalles_layout);
        RecordatoriosUCR item = getIntent().getExtras().getParcelable("datosRecordatorio");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.remainder_detalle_fragment, new RemainderUCRDetailFragment(item));
        ft.commit();
    }
}
