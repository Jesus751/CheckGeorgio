package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    ImageView Recepccio,  Mecani, AddUsu;

    TextView nombreUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Recepccio = findViewById(R.id.imageViewRecepcion);
        Mecani =  findViewById(R.id.imageViewMecanico);
        nombreUser = findViewById(R.id.textNombreUser);
        AddUsu =  findViewById(R.id.imageViewUsuarios);

        Intent intent = getIntent();
        String nombreUse = intent.getStringExtra("nom");
        String apellidoUsa =  intent.getStringExtra("apell");
        nombreUser.setText(""+nombreUse+"  "+ apellidoUsa);


        Mecani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  =  new Intent(AdminActivity.this,AgregarCheckListMecanicoActivity.class );
                startActivity(i);
            }
        });

        Recepccio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, AgregarCheckListReccepcionActivity.class);
                startActivity(i);
            }
        });

        AddUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, AddUsuariosActivity.class);
                startActivity(i);
            }
        });


    }
}