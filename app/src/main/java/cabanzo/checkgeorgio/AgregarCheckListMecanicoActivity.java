package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AgregarCheckListMecanicoActivity extends AppCompatActivity {

    ImageView AMotor,ACaja,AFrenos, AElectrico,ASeguridad,ACabina,ABCofre, ABAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_check_list_mecanico);


        AMotor =  findViewById(R.id.imageViewMotorAdmin);
        ACaja =  findViewById(R.id.imageViewCajaAdmin);
        AFrenos =  findViewById(R.id.imageViewFrenosAdmin);
        AElectrico = findViewById(R.id.imageViewElectricoAdmin);
        ASeguridad =  findViewById(R.id.imageViewSeguridadAdmin);
        ACabina =  findViewById(R.id.imageViewCabinaAdmin);
        ABCofre = findViewById(R.id.imageViewBajoCofreAdmin);
        ABAuto = findViewById(R.id.imageViewBajoAutoAdmin);

        AMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarCheckListMecanicoActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","18");
                i.putExtra("categoria","Motor");
                startActivity(i);
            }
        });

        ACaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarCheckListMecanicoActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","19");
                i.putExtra("categoria","Caja de cambio y trasmisión ");
                startActivity(i);
            }
        });

        AFrenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarCheckListMecanicoActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","20");
                i.putExtra("categoria","Sistema de frenos");
                startActivity(i);
            }
        });
        AElectrico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarCheckListMecanicoActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","21");
                i.putExtra("categoria","Sistema eléctrico ");
                startActivity(i);
            }
        });

        ASeguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarCheckListMecanicoActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","22");
                i.putExtra("categoria","Equipos de seguridad");
                startActivity(i);
            }
        });
        ACabina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarCheckListMecanicoActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","23");
                i.putExtra("categoria","Cabina");
                startActivity(i);
            }
        });

        ABCofre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarCheckListMecanicoActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","24");
                i.putExtra("categoria","Bajo el cofre");
                startActivity(i);
            }
        });
        ABAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarCheckListMecanicoActivity.this, SubirItemActivity.class);
                i.putExtra("iditem","25");
                i.putExtra("categoria","Bajo el auto");
                startActivity(i);
            }
        });
    }
}