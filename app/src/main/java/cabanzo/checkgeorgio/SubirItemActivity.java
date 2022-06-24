package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import cabanzo.checkgeorgio.Adapter.AdapterCheckList;
import cabanzo.checkgeorgio.Adapter.AdapterServicios;
import cabanzo.checkgeorgio.Modelo.ItemsCategoria;
import cabanzo.checkgeorgio.Modelo.ItemsCheck;
import cabanzo.checkgeorgio.Modelo.Servicios;

public class SubirItemActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText nombreCheckList;
    Button guardar;
    TextView nombreCATE,cate ;


    String UPLOAD_URL = "http://ubiexpress.net:5610/WebServiceGeorgioMovil/PanelCheckList.php";
    String URL_CATEGORIAS= "http://ubiexpress.net:5610/WebServiceGeorgioMovil/PaneltemsMecanico.php";
    String KEY_NOMBRE_CHECK = "nombreCheck";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    ArrayList<ItemsCheck> itemsChecks = new ArrayList<ItemsCheck>();
    AdapterCheckList adapterCheckList;

    String iDCategoria="";
    String iDServicio="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_item);

        Intent intent=getIntent();
        iDCategoria=intent.getStringExtra("iditem");
        iDServicio=intent.getStringExtra("idservicio");
        recyclerView = findViewById(R.id.ReciclerCheckList);



        nombreCheckList = findViewById(R.id.editTextTextNombreCheck);
        guardar =  findViewById(R.id.btbguardar);
        nombreCATE =  findViewById(R.id.txtServicioc);

        DescargaItemsCheckListDisponibles(iDCategoria);
        // Guardar Ckeck List
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
                params.put("idcate",iDCategoria);
                params.put("opcion", "1" );
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    // Descargar Items de CheckList

    private  void  DescargaItemsCheckListDisponibles( String idcategoria){
        stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("mens", "ss" + response);
                if (!response.isEmpty()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Consulta");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject ob = jsonArray.getJSONObject(i);
                            itemsChecks.add(new ItemsCheck(ob));
                        }
                        adapterCheckList = new AdapterCheckList(SubirItemActivity.this, itemsChecks, iDServicio);
                        recyclerView.setAdapter(adapterCheckList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SubirItemActivity.this, LinearLayoutManager.VERTICAL, false));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SubirItemActivity.this, "No se pudo descargar los check list disponibles", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
                    protected Map<String,String> getParams() throws  AuthFailureError{
                HashMap<String, String> params  =  new HashMap<>();
                params.put("opcion","4");

                return params;
            }
        };
        requestQueue =  Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);
    }





}