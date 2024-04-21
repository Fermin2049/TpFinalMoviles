package com.example.exploradordelugaresturisticos;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exploradordelugaresturisticos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar); // Asegúrate de tener esto si usas una Toolbar

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        toggle = new ActionBarDrawerToggle(
                this, drawer, binding.appBarMain.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState(); // Sincroniza el estado del icono con el DrawerLayout

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_LugaresTuristico, R.id.nav_mapa, R.id.nav_configuracion, R.id.nav_salir)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void solicitarPermiso(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M
                &&((checkSelfPermission(ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)||
                (checkSelfPermission(ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED))){
            requestPermissions(new String[]{ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION},1000);
        }
    }
}