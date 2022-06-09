package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckListRecepcion extends AppCompatActivity {

    ImageView BAccesorios, BCarroceria,BLlantas,BGasolina;
    TextView Dunidad;
    TextView Dcliente;
    Button cerrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list_recepcion);

        BAccesorios =  findViewById(R.id.imageViewAccesorios);
        BCarroceria =  findViewById(R.id.imageViewCarroceria);
        BLlantas = findViewById(R.id.imageViewLlantas);
        BGasolina = findViewById(R.id.imageViewGasolina);
        Dunidad =  findViewById(R.id.textDatosUnida);
        Dcliente =  findViewById(R.id.textDatosClien);
        cerrar = findViewById(R.id.cerrarSesio);
        Intent intent =  getIntent();

        String idServ=intent.getStringExtra("idServicio");
        String iCliente=intent.getStringExtra("Cliente");
        String iUnidad=intent.getStringExtra("Unidad");

        Dunidad.setText(""+iUnidad);
        Dcliente.setText((""+ iCliente));


        BAccesorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CheckListRecepcion.this, CheckCategoria.class);
                intent.putExtra("idcategoria","1");
                intent.putExtra("idservicio",idServ);
                startActivity(intent);
            }
        });

        BCarroceria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckListRecepcion.this, CheckCategoria.class);
                intent.putExtra("idcategoria","1");
                intent.putExtra("idservicio",idServ);
                startActivity(intent);
            }
        });

        BLlantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(CheckListRecepcion.this, CheckCategoria.class);
                intent.putExtra("idcategoria","1");
                intent.putExtra("idservicio",idServ);
                startActivity(intent);
            }
        });

        BGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(CheckListRecepcion.this, CheckCategoria.class);
                intent.putExtra("idcategoria","1");
                intent.putExtra("idservicio",idServ);
                startActivity(intent);
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