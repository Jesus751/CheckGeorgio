package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AgregarCheckListReccepcionActivity extends AppCompatActivity {

    ImageView ACesorios, ACarroceria, ALlantas, AGasolina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_check_list_reccepcion);

        ACesorios = findViewById(R.id.imageViewAccesoriosAdmin);
        ACarroceria =  findViewById(R.id.imageViewCarroceriaAdmin);
        ALlantas = findViewById(R.id.imageViewLlantasAdmin);
        AGasolina =  findViewById(R.id.imageViewGasolinaAdmin);



        ACesorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(AgregarCheckListReccepcionActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","14");
                i.putExtra("categoria","Accesorios y herramientas");
                startActivity(i);
            }
        });

        ACarroceria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(AgregarCheckListReccepcionActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","15");
                i.putExtra("categoria","Carrocería e interiores ");
                startActivity(i);
            }
        });

        ALlantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(AgregarCheckListReccepcionActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","16");
                i.putExtra("categoria","Llantas");
                startActivity(i);
            }
        });
        AGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(AgregarCheckListReccepcionActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","17");
                i.putExtra("categoria","Gasolina");
                startActivity(i);
            }
        });
    }
}