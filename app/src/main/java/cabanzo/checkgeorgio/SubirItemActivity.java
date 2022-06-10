package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import cabanzo.checkgeorgio.Adapter.AdapterServicios;
import cabanzo.checkgeorgio.Modelo.Servicios;

public class SubirItemActivity extends AppCompatActivity {

    ListView listaCheck;

    EditText nombreCheckList;
    Button guardar;
    TextView nombreCATE;


    String UPLOAD_URL = "http://ubiexpress.net:5610/georgio/mobil/PanelCompras.php";
    String KEY_NOMBRE_CHECK = "nombreCheck";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    ArrayList<Servicios> itemServicios = new ArrayList<>();
    AdapterServicios adapterServicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_item);

        listaCheck = findViewById(R.id.ListaMecRec);
        nombreCheckList = findViewById(R.id.editTextTextNombreCheck);
        guardar =  findViewById(R.id.btbguardar);
        nombreCATE =  findViewById(R.id.txtServicioc);




        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombreCheckList.length() > 5){
                    Toast.makeText(SubirItemActivity.this, "Se Guardo Exitosamente", Toast.LENGTH_SHORT).show();
                    subirCheckList();
                }else{
                    Toast.makeText(SubirItemActivity.this, "Todos los campos obligatorios", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    // Subir Nombre Check List
    public void subirCheckList(){
        final ProgressDialog dialog =  ProgressDialog.show(this,"subiendo","Espere por favor");
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("fallo", "1" + response);
                        dialog.dismiss();
                        Toast.makeText(SubirItemActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fallo", "2"+error.getMessage());
                dialog.dismiss();
                Toast.makeText(SubirItemActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String nombre = nombreCheckList.getText().toString().trim().toUpperCase();

                Map<String, String> params = new Hashtable<String, String>();
                params.put(KEY_NOMBRE_CHECK, nombre);
                params.put("opcion", "56" );
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}