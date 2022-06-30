package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Map;

import cabanzo.checkgeorgio.Adapter.AdapterUsuarios;
import cabanzo.checkgeorgio.Modelo.ItemsUsu;

public class UsuariosActivity extends AppCompatActivity {


    String URL_USUARIOS = "http://ubiexpress.net:5610/WebServiceGeorgioMovil/PanelUsuarios.php";
    RecyclerView recyclerUsu;
    StringRequest  stringReques;
    RequestQueue requestQueue;
    ArrayList<ItemsUsu> itemsUsus =  new ArrayList<>();
    AdapterUsuarios adapterUsuarios;

    String IdUser = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        recyclerUsu = findViewById(R.id.ReciclerUsuarios);

        Intent i = getIntent();
        IdUser =  i.getStringExtra("iduser");
        descargarUsuarios(IdUser);
    }

    private void descargarUsuarios(String idUser){
        stringReques =  new StringRequest(Request.Method.POST, URL_USUARIOS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("mens", "ss" + response);
                if (!response.isEmpty()) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Consulta");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject oj = jsonArray.getJSONObject(i);

                            itemsUsus.add(new ItemsUsu(oj));
                        }
                        adapterUsuarios = new AdapterUsuarios(UsuariosActivity.this, itemsUsus, idUser);
                        recyclerUsu.setAdapter(adapterUsuarios);
                        recyclerUsu.setLayoutManager(new LinearLayoutManager(UsuariosActivity.this, LinearLayoutManager.VERTICAL, false));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UsuariosActivity.this, "No se descargo los usuarios", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params =  new HashMap<>();
                params.put("opcion","3");
                return  params;
            }
        };
        requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringReques);
    }
}