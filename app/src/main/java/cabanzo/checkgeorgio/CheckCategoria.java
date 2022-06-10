package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.util.Map;

import cabanzo.checkgeorgio.Adapter.AdapterRecyclerItemCheck;
import cabanzo.checkgeorgio.Modelo.ItemsCategoria;
import cabanzo.checkgeorgio.Modelo.Servicios;

public class CheckCategoria extends AppCompatActivity {
    RecyclerView recyclerView;
    Button ButonGuardar;

    String URL_CATEGORIAS = "http://ubiexpress.net:5610/georgio/mobil/PanelCompras.php";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    ArrayList<ItemsCategoria> itemsCategorias = new ArrayList<ItemsCategoria>();
    AdapterRecyclerItemCheck adapterRecyclerItemCheck;
    String iDCategoria="";
    String iDServicio="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_categoria);

        Intent intent=getIntent();
        iDCategoria=intent.getStringExtra("idcategoria");
        iDServicio=intent.getStringExtra("idservicio");

        recyclerView = findViewById(R.id.ReciclerCheck);
        DescargaItemsCategoria(iDCategoria,iDServicio);


    }
    private void DescargaItemsCategoria(String idcategoria,String idventa){
        stringRequest = new StringRequest(Request.Method.POST, URL_CATEGORIAS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("mens", "ss" + response);
                if (!response.isEmpty()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Consulta");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject oj = jsonArray.getJSONObject(i);

                            itemsCategorias.add(new ItemsCategoria(oj));

                        }
                        adapterRecyclerItemCheck = new AdapterRecyclerItemCheck(CheckCategoria.this, itemsCategorias,iDServicio,"2",ButonGuardar);
                        recyclerView.setAdapter(adapterRecyclerItemCheck);
                        recyclerView.setLayoutManager(new LinearLayoutManager(CheckCategoria.this, LinearLayoutManager.VERTICAL, false));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CheckCategoria.this,"No se descargo el check",Toast.LENGTH_LONG).show();
                }

            }){
        @Override
                protected Map<String,String> getParams() throws AuthFailureError{
            HashMap<String,String> params = new HashMap<String,String>();
            params.put("opcion","54");
            params.put("categoria",idcategoria);
            params.put("idventa",idventa);
            Log.e("checksx",idcategoria+"-"+idventa);
            return params;

        }
        };
        requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);
    }
}