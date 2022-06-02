package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckMecanico extends AppCompatActivity {

    ImageView BMotor,BTrasmicion,BFrenos,BElectrico,BSeguridad,Botros;
    TextView Dunidad;
    TextView Dcliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_mecanico);

        BMotor = findViewById(R.id.imageViewMotor);
        BTrasmicion = findViewById(R.id.imageViewCaja);
        BFrenos= findViewById(R.id.imageViewFrenos);
        BElectrico= findViewById(R.id.imageViewElectrico);
        BSeguridad= findViewById(R.id.imageViewSeguridad);
        Botros= findViewById(R.id.imageViewOtros);
        Dunidad = findViewById(R.id.textDatosUnidad);
        Dcliente = findViewById(R.id.textDatosCliente);
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

        Botros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ix= new Intent(CheckMecanico.this, CheckCategoria.class);
                ix.putExtra("idcategoria","1");
                ix.putExtra("idservicio",idServ);
                startActivity(ix);
            }
        });
    }
}