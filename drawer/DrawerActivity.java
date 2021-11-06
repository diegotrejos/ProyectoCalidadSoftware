package cr.ac.ucr.ecci.eseg.uhelper.components.drawer;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import cr.ac.ucr.ecci.eseg.uhelper.app.HomeActivity;
import cr.ac.ucr.ecci.eseg.uhelper.app.auth.LoginActivity;
import cr.ac.ucr.ecci.eseg.uhelper.app.contacts.ContactosActivity;
import cr.ac.ucr.ecci.eseg.uhelper.app.location.locationActivity;
import cr.ac.ucr.ecci.eseg.uhelper.app.profile.ProfileActivity;
import cr.ac.ucr.ecci.eseg.uhelper.R;

import cr.ac.ucr.ecci.eseg.uhelper.app.reminders.MyRemindersActivity;
import cr.ac.ucr.ecci.eseg.uhelper.app.reminders.ReminderUCRActivity;
import cr.ac.ucr.ecci.eseg.uhelper.app.schedule.AddCourseActivity;
import cr.ac.ucr.ecci.eseg.uhelper.app.schedule.ScheduleActivity;
import cr.ac.ucr.ecci.eseg.uhelper.components.Constants;
import cr.ac.ucr.ecci.eseg.uhelper.components.SessionManager;
import cr.ac.ucr.ecci.eseg.uhelper.components.Singleton;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.entities.Autenticacion;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.repositories.PerfilRepo;
import cr.ac.ucr.ecci.eseg.uhelper.infraestructure.subqueries_objects.AuthDto;


import android.view.MenuItem;

import org.jetbrains.annotations.NotNull;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public NavigationView navigationView;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    //Para usuarios
    private AuthDto user;

   //Permite mas adelante evitar que un vista brinque a si misma
    int layoutactual;

    public void setDrawer(int navId) {
    layoutactual = navId;
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
       // navigationView.setNavigationItemSelectedListener();
        // para que el drawer se muestre
        drawerLayout = (DrawerLayout) findViewById(navId);
        actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close) {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }
                };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // para mostrar icono de drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    //permite que el drawer se muestre al hacer click al hamburguer icon
    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {


        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //si se sale de la aplicacion con el drawer abierto
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    //logica de botones del drawer
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.contactos_ucr_drawer:
                if(layoutactual != R.id.contactos_layout) {
                  this.user = Singleton.getUser();
                   irContactos();
                }
                return true;
            case R.id.Home_drawer:
                if(layoutactual != R.id.home_layout) {
                    this.user = Singleton.getUser();
                    irHome();
                }
                return true;
            case R.id.buscar_aula_drawer:
                if(layoutactual != R.id.location_layout) {
                    this.user = Singleton.getUser();
                    goToLocation();
                }
             return true;
            case R.id.horario_drawer:
                if(layoutactual != R.id.schedule_layout) {
                    this.user = Singleton.getUser();
                    goToSchedule();
                }
              return true;
            case R.id.mi_perfil_drawer:
                if(layoutactual != R.id.profile_layout) {
                    this.user = Singleton.getUser();
                    goToProfile();
                }
                return true;
            case R.id.fechas_importantes_drawer:
                if(layoutactual != R.id.fechas_importantes_drawer) {
                    this.user = Singleton.getUser();
                    irMisNotificaciones();
                }
                return true;
            case R.id.fechas_importantesUCR_drawer:
                if(layoutactual != R.id.fechas_importantesUCR_drawer) {
                    this.user = Singleton.getUser();
                    irNotificacionUCR();
                }
                return true;

            case R.id.cerrar_sesion_drawer:
                this.closeSession();
                return true;

        }
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }


    private void goToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("Profile", user);
        startActivity(intent);
    }

    private void goToSchedule(){
        Intent intent = new Intent(this, ScheduleActivity.class);
        intent.putExtra("Schedule", user);
        startActivity(intent);
    }

    private void irContactos() {
        Intent intent = new Intent(this, ContactosActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    private void irHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
    private void goToLocation(){
        Intent intent = new Intent(this, locationActivity.class);
        intent.putExtra("Location", user);
        startActivity(intent);
    }
    private void irNotificacionUCR(){
        Intent intent = new Intent(this, ReminderUCRActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
    private void irMisNotificaciones(){
        Intent intent = new Intent(this, MyRemindersActivity.class);
        startActivity(intent);
    }

    private void closeSession() {
        SessionManager.getInstance(this).closeSession();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void setUser(AuthDto user){
        this.user = user;
    }
}



