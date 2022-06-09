package cabanzo.checkgeorgio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckMecanico extends AppCompatActivity {



    ImageView BMotor,BTrasmicion,BFrenos,BElectrico,BSeguridad,BoCabina,BBCofre, BBAuto;
    TextView Dunidad;
    TextView Dcliente;
    Button cerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_mecanico);

        BMotor = findViewById(R.id.imageViewMotor);
        BTrasmicion = findViewById(R.id.imageViewCaja);
        BFrenos= findViewById(R.id.imageViewFrenos);
        BElectrico= findViewById(R.id.imageViewElectrico);
        BSeguridad= findViewById(R.id.imageViewSeguridad);
        BoCabina= findViewById(R.id.imageViewOtros);
        Dunidad = findViewById(R.id.textDatosUnidad);
        Dcliente = findViewById(R.id.textDatosCliente);
        BBCofre =  findViewById(R.id.imageViewBajoCofre);
        BBAuto = findViewById(R.id.imageViewBajoAuto);
        cerrar =  findViewById( R.id.cerrarSesion);


        Intent intent = getIntent();

        String idServ=intent.getStringExtra("idServicio");
        String iCliente=intent.getStringExtra("Cliente");
        String iUnidad=intent.getStringExtra("Unidad");
        Dunidad.setText(""+iUnidad);
        Dcliente.setText(""+iCliente);




        BMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ix= new Intent(CheckMecanico.this, CheckCategoria.class);
                ix.putExtra("idcategoria","1");
                ix.putExtra("idservicio",idServ);
                startActivity(ix);
            }
        });

        BTrasmicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ix= new Intent(CheckMecanico.this, CheckCategoria.class);
                ix.putExtra("idcategoria","2");
                ix.putExtra("idservicio",idServ);
                startActivity(ix);
            }
        });

        BFrenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ix= new Intent(CheckMecanico.this, CheckCategoria.class);
                ix.putExtra("idcategoria","3");
                ix.putExtra("idservicio",idServ);
                startActivity(ix);
            }
        });

        BElectrico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ix= new Intent(CheckMecanico.this, CheckCategoria.class);
                ix.putExtra("idcategoria","4");
                ix.putExtra("idservicio",idServ);
                startActivity(ix);
            }
        });

        BSeguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ix= new Intent(CheckMecanico.this, CheckCategoria.class);
                ix.putExtra("idcategoria","5");
                ix.putExtra("idservicio",idServ);
                startActivity(ix);
            }
        });

        BoCabina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ix= new Intent(CheckMecanico.this, CheckCategoria.class);
                ix.putExtra("idcategoria","1");
                ix.putExtra("idservicio",idServ);
                startActivity(ix);
            }
        });

        BBCofre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ix =  new Intent(CheckMecanico.this, CheckCategoria.class);
                ix.putExtra("idcategoria","7");
                ix.putExtra("idservicio",idServ);
                startActivity(ix);
            }
        });

        BBAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ix =  new Intent(CheckMecanico.this, CheckCategoria.class);
                ix.putExtra("idcategoria","1");
                ix.putExtra("idservicio",idServ);
                startActivity(ix);
            }
        });
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getBaseContext().getPackageManager() .getLaunchIntentForPackage(getBaseContext().getPackageName()); intent .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); startActivity(intent);
            }
        });

    }

}