package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuAdminActivity extends AppCompatActivity {
    ImageView chackl, usua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);


        chackl = findViewById(R. id.imageViewCheckl);
        usua = findViewById(R.id.imageViewListaUsuarios);
        Intent intent = getIntent();
        String nombreUse = intent.getStringExtra("nom");
        String apellidoUsa =  intent.getStringExtra("apell");

        chackl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuAdminActivity.this, AdminActivity.class);
                Intent intent = getIntent();

                i.putExtra("nom", nombreUse);
                i.putExtra("apell",apellidoUsa);
                startActivity(i);
            }
        });

        usua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuAdminActivity.this, UsuariosActivity.class);
                startActivity(i);
            }
        });
    }

}