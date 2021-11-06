package cr.ac.ucr.ecci.eseg.uhelper.app.reminders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.StringTokenizer;

import cr.ac.ucr.ecci.eseg.uhelper.R;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.RecordatoriosUCR;

public class RemainderUCRDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "recordatorios";

    private RecordatoriosUCR recordatoriosUCR;

    public RemainderUCRDetailFragment() {
        // Required empty public constructor
    }

    public RemainderUCRDetailFragment(RecordatoriosUCR recordatoriosUCR) {
        this.recordatoriosUCR = recordatoriosUCR;
    }

    public static RemainderDetailFragment newInstance(RecordatoriosUCR recordatoriosUCR) {
        RemainderDetailFragment fragment = new RemainderDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, recordatoriosUCR);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recordatoriosUCR = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_remainder_ucr_detail, container, false);

        Button buttonAdd = view.findViewById(R.id.buttonAdd);

        if (recordatoriosUCR != null) {
            TextView textNombre = (TextView) view.findViewById(R.id.textNombre);
            textNombre.setText(recordatoriosUCR.getNombre());
            TextView textDetalles = (TextView) view.findViewById(R.id.textDetalles);
            textDetalles.setText(recordatoriosUCR.getDescripcion());
            TextView textFecha = (TextView) view.findViewById(R.id.textFecha);
            String fecha = "";
            fecha = recordatoriosUCR.getDia() + "/" + recordatoriosUCR.getMes() + "/" + recordatoriosUCR.getAnno();
            textFecha.setText(fecha);
            TextView textHora = (TextView) view.findViewById(R.id.textHora);
            textHora.setText(recordatoriosUCR.getHora());
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringTokenizer tokenizer = new StringTokenizer(recordatoriosUCR.getHora(), ":");
                int hour = Integer.parseInt(tokenizer.nextToken());
                int minute = Integer.parseInt(tokenizer.nextToken());

                Calendar remainderTime = Calendar.getInstance();
                remainderTime.set(recordatoriosUCR.getAnno(), recordatoriosUCR.getMes() - 1, recordatoriosUCR.getDia(), hour, minute);
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, remainderTime.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, remainderTime.getTimeInMillis())
                        .putExtra(CalendarContract.Events.TITLE, recordatoriosUCR.getNombre())
                        .putExtra(CalendarContract.Events.DESCRIPTION, recordatoriosUCR.getDescripcion());
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}