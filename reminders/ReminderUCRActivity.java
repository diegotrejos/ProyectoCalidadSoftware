package cr.ac.ucr.ecci.eseg.uhelper.app.reminders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import cr.ac.ucr.ecci.eseg.uhelper.R;
import cr.ac.ucr.ecci.eseg.uhelper.components.Singleton;
import cr.ac.ucr.ecci.eseg.uhelper.components.drawer.DrawerActivity;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories.PerfilRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.subqueries_objects.AuthDto;

public class ReminderUCRActivity extends DrawerActivity {

    protected static final String PROFILE_OBJECT = "Profile";
    private static final String AUTHENTICATION_FROM_LOGIN = "User";
    private final int MAX_WELCOME_LENGTH =  22;
    private PerfilRepo perfilRepo;
    private AuthDto user;
    boolean isFirstSemesterVisible = true;
    boolean isSecondSemesterVisible = true;
    boolean isThirdSemesterVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_ucr);
        getSupportActionBar().setTitle("Recordatorios UCR");
        setDrawer(R.id.reminderucr_layout);
        this.user = Singleton.getUser();
        setListeners();
    }

    public void setListeners(){
        TableRow header1 = findViewById(R.id.tableRowHeader1);
        TableRow header2 = findViewById(R.id.tableRowHeader2);
        TableRow header3 = findViewById(R.id.tableRowHeader3);
        header1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFirstSemesterVisible) {
                    findViewById(R.id.fragmentList1).setVisibility(View.GONE);
                    findViewById(R.id.add_class_time_image_up1).setVisibility(View.GONE);
                    findViewById(R.id.add_class_time_image).setVisibility(View.VISIBLE);
                    isFirstSemesterVisible = false;
                } else {
                    findViewById(R.id.fragmentList1).setVisibility(View.VISIBLE);
                    findViewById(R.id.add_class_time_image_up1).setVisibility(View.VISIBLE);
                    findViewById(R.id.add_class_time_image).setVisibility(View.GONE);
                    isFirstSemesterVisible = true;
                }

            }
        });

        header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Second Semester
                if(isSecondSemesterVisible) {
                    findViewById(R.id.fragmentList2).setVisibility(View.GONE);
                    findViewById(R.id.add_class_time_image_up2).setVisibility(View.GONE);
                    findViewById(R.id.add_class_time_image2).setVisibility(View.VISIBLE);
                    isSecondSemesterVisible = false;
                } else {
                    findViewById(R.id.fragmentList2).setVisibility(View.VISIBLE);
                    findViewById(R.id.add_class_time_image_up2).setVisibility(View.VISIBLE);
                    findViewById(R.id.add_class_time_image2).setVisibility(View.GONE);
                    isSecondSemesterVisible = true;
                }
            }
        });

        header3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Third semester
                if(isThirdSemesterVisible) {
                    findViewById(R.id.fragmentList3).setVisibility(View.GONE);
                    findViewById(R.id.add_class_time_image_up3).setVisibility(View.GONE);
                    findViewById(R.id.add_class_time_image3).setVisibility(View.VISIBLE);
                    isThirdSemesterVisible = false;
                } else {
                    findViewById(R.id.fragmentList3).setVisibility(View.VISIBLE);
                    findViewById(R.id.add_class_time_image_up3).setVisibility(View.VISIBLE);
                    findViewById(R.id.add_class_time_image3).setVisibility(View.GONE);
                    isThirdSemesterVisible = true;
                }
            }
        });
    }



}