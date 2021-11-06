package cr.ac.ucr.ecci.eseg.uhelper.app.reminders;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cr.ac.ucr.ecci.eseg.uhelper.R;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories.RecordatoriosUCRRepo;

public class ReminderUCRFragment3 extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecordatoriosUCRRepo recordatoriosUCRRepo;
    RecyclerView mRecyclerView;

    public ReminderUCRFragment3() {
        // Required empty public constructor
    }


    public static ReminderUCRFragment3 newInstance(int columnCount) {
        ReminderUCRFragment3 fragment = new ReminderUCRFragment3();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list_reminderucr, container, false);

        mRecyclerView = view.findViewById(R.id.ucr_reminder_list);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recordatoriosUCRRepo = new RecordatoriosUCRRepo(requireContext());
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mRecyclerView.setAdapter(new MyRemindersUcrRecyclerViewAdapter(recordatoriosUCRRepo.getSemestre3RecordatoriosUCR()));
        }
        return view;
    }
}