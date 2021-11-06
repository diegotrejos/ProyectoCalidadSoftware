package cr.ac.ucr.ecci.eseg.uhelper.app;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cr.ac.ucr.ecci.eseg.uhelper.app.auth.LoginActivity;
import cr.ac.ucr.ecci.eseg.uhelper.app.location.locationActivity;
import cr.ac.ucr.ecci.eseg.uhelper.components.SessionManager;
import cr.ac.ucr.ecci.eseg.uhelper.components.Singleton;
import cr.ac.ucr.ecci.eseg.uhelper.components.drawer.DrawerActivity;
import cr.ac.ucr.ecci.eseg.uhelper.app.profile.ProfileActivity;
import cr.ac.ucr.ecci.eseg.uhelper.R;
import cr.ac.ucr.ecci.eseg.uhelper.app.schedule.AddCourseActivity;
import cr.ac.ucr.ecci.eseg.uhelper.components.Constants;
import cr.ac.ucr.ecci.eseg.uhelper.app.schedule.ScheduleActivity;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Autenticacion;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Perfil;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.firebase_repositories.FBMisRecordatoriosRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories.PerfilRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.scripts.PostDeployment;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.subqueries_objects.AuthDto;


import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends DrawerActivity {

    private static final String AUTHENTICATION_FROM_LOGIN = "UserEntity";
    private static final String AUTHENTICATION_FROM_DRAWER = "User";
    private final int MAX_WELCOME_LENGTH =  22;
    private PerfilRepo perfilRepo;
    private Autenticacion user;
    private AuthDto userDto;
    // Base de datos firebase y su referencia
    FirebaseDatabase firebaseDatabase;
    DatabaseReference fb_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FBMisRecordatoriosRepo fbMisRecordatoriosRepo = Singleton.getFBMisRecordatoriosRepo();
        fbMisRecordatoriosRepo.getRecordatoriosFromFirebase(Singleton.getMisRecordatoriosRepo(this));
        perfilRepo = Singleton.getPerfilRepo(HomeActivity.this);
        getSupportActionBar().setTitle("Inicio");
        //importa el drawer usando el extend anterior
        setDrawer(R.id.home_layout);
        //Boton perfil
        this.getSessionDetailsFromFromBundle();
        //Revisa si ya hay un usuario en sesion (sesiones anteriores) para luego verificar
        // si es el mismo que recien inicio sesion, de lo contrario es nulo
        AuthDto userInSession = Singleton.getUser() == null ?
                new AuthDto(user.getCorreo(), user.getId(), user.getPerfilId()) : null;
        // Agrega el usuario en sesion como usuario actual en el app.
        if(Singleton.getUser() == null || userInSession != Singleton.getUser()) {
            Singleton.setUser(this.userDto);
        }
        //Mensaje de bienvenido
        changeWelcomeMsg();
        Button buttonProfile = findViewById(R.id.PerfilButton);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });
        Button buttonSchedule = findViewById(R.id.HorarioButton);
        buttonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSchedule();
            }
        });
        Button buttonLocation = findViewById(R.id.UbicarClaseButton);
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLocation();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean userLoggedIn = SessionManager.getInstance(HomeActivity.this).isUserLoggedIn();
        if(!userLoggedIn) {
            this.goToLogin();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        changeWelcomeMsg();
    }

    public void changeWelcomeMsg() {
        TextView welcome = findViewById(R.id.welcome);
        Perfil profile = perfilRepo.getProfile(userDto.getId());
        String textToAdd = "Bienvenido\n";
        textToAdd += profile.getNombre() + " " + profile.getPrimerApellido() + " " + profile.getSegundoApellido();
        //Check name length
        /*if(profile.getNombre().length() + profile.getPrimerApellido().length() < MAX_WELCOME_LENGTH){
            textToAdd += profile.getNombre() + " " + profile.getPrimerApellido();
        } else {
            textToAdd += profile.getNombre();
        }*/
        welcome.setText(textToAdd);
    }

    private void getSessionDetailsFromFromBundle() {
        Bundle bundle = getIntent().getExtras();
        boolean extrasAvailable = bundle != null;
        if (extrasAvailable) {
            //Settea user de clase Autenticacion
            this.user = bundle.getParcelable(AUTHENTICATION_FROM_LOGIN);
            //Settea user dto
            this.userDto = bundle.getParcelable(AUTHENTICATION_FROM_DRAWER);
            //Settea AuthDto que es un dto que la diferencia con la clase Autenticacion es que no tiene la contrasena.
            //Esto incrementa la seguridad al no manejar la contrasena por el app.
            if (this.userDto == null)
                this.userDto = new AuthDto(user.getCorreo(), user.getId(), user.getPerfilId());
        }
    }

    private void goToProfile() {
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        intent.putExtra("Profile", userDto);
        startActivity(intent);
    }

    private void goToSchedule(){
        Intent intent = new Intent(HomeActivity.this, ScheduleActivity.class);
        intent.putExtra("Schedule", userDto);
        startActivity(intent);
    }

    private void goToLocation(){
        Intent intent = new Intent(HomeActivity.this, locationActivity.class);
        intent.putExtra("Location", userDto);
        startActivity(intent);
    }

    private void goToLogin() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
