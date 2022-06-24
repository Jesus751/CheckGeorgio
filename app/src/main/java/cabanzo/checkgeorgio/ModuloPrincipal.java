package cabanzo.checkgeorgio;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
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

import cabanzo.checkgeorgio.Adapter.AdapterServicios;
import cabanzo.checkgeorgio.Modelo.Servicios;
import cabanzo.checkgeorgio.Temporales.ObjetosTemporales;

public class   ModuloPrincipal extends AppCompatActivity implements  SearchView.OnQueryTextListener{

    ListView listaServi;

    EditText usertel,userpass;
    Button Biniciarsec;
    SearchView txtBuscar;


    String URL_SERVICIOS="http://ubiexpress.net:5610/georgio/mobil/PanelCompras.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    ArrayList<Servicios> itemServicios = new ArrayList<>();
    AdapterServicios adapterServicios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_principal);
        listaServi= findViewById(R.id.ListaServicios);
        txtBuscar = findViewById(R.id.SBuscar);
        ListarServicios();

        listaServi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final int pos = position;

                //String selectedItem = (String) adapterView.getItemAtPosition(position);

                TextView IdServicio = (TextView) view.findViewById(R.id.Text_IdServicio);
                TextView IdMarca = (TextView) view.findViewById(R.id.textV_Marca);
                TextView IdModelo = (TextView) view.findViewById(R.id.textV_Modelo);
                TextView IdAno = (TextView) view.findViewById(R.id.textV_Ano);
                TextView IdPlacas = (TextView) view.findViewById(R.id.textV_Placas);
                TextView IdCliente = (TextView) view.findViewById(R.id.textV_Cliente);

                String V_idServicio = IdServicio.getText().toString();
                String V_idMarca = IdMarca.getText().toString();
                String V_IdModelo = IdModelo.getText().toString();
                String V_IdAno = IdAno.getText().toString();
                String V_IdPlacas = IdPlacas.getText().toString();
                String V_IdCliente = IdCliente.getText().toString();

                LanzarChecklist(V_idServicio, "" + V_idMarca + " " + V_IdModelo + " " + V_IdAno + " " + V_IdPlacas, V_IdCliente);

            }

        });
        txtBuscar.setOnQueryTextListener(this);
    }


    private  void LanzarChecklist(String idS,String iunidad,String icliente){
        Bundle extras =  getIntent().getExtras();

       String  TipoRepar = extras.getString("repar");
       String  TiporRecep = extras.getString("recep");

        Log.e( "el tipo es=",  TipoRepar + "------" + TiporRecep + "--"  );

       if(TipoRepar != null){
           Intent ix = new Intent(ModuloPrincipal.this, CheckMecanico.class);
           ix.putExtra("idServicio",idS);
           ix.putExtra("Unidad",iunidad);
           ix.putExtra("Cliente",icliente);
           startActivity(ix);

       }else if(TiporRecep != null) {
           Intent ix = new Intent(ModuloPrincipal.this, CheckListRecepcion.class);
           ix.putExtra("idServicio", idS);
           ix.putExtra("Unidad", iunidad);
           ix.putExtra("Cliente", icliente);
           startActivity(ix);
       }
       /*

        Log.e( "el tipo es=", TipoRepar + TiporRecep );
        switch ( Tipo){
            case "repartidor":{
                Intent ix = new Intent(ModuloPrincipal.this, CheckMecanico.class);
                ix.putExtra("idServicio",idS);
                ix.putExtra("Unidad",iunidad);
                ix.putExtra("Cliente",icliente);
                Log.e("Si ingreso", "22");
                startActivity(ix);

            };break;
            case "recepcion":{
                Intent ix = new Intent(ModuloPrincipal.this, CheckListRecepcion.class);
                ix.putExtra("idServicio",idS);
                ix.putExtra("Unidad",iunidad);
                ix.putExtra("Cliente",icliente);
                Log.e("Si ingreso", "23");
                startActivity(ix);

            };break;
        }; */



    }

    private void ListarServicios(){
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVICIOS, new Response.Listener<String>() {
            @Override public void onResponse(String response) {
                if(!response.equalsIgnoreCase("fallo")) {
                    try {
                        String json = response;
                        //Log.e("servci",json);
                        if (json != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(json);
                                JSONArray contacs = jsonObj.getJSONArray("Consulta");
                                for (int i = 0; i < contacs.length(); i++) {
                                    JSONObject oj = contacs.getJSONObject(i);
                                    itemServicios.add(new Servicios(oj));
                                }
                                adapterServicios = new AdapterServicios(ModuloPrincipal.this, itemServicios);
                                listaServi.setAdapter(adapterServicios);
                            } catch (JSONException e) { e.printStackTrace(); }
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) { Toast.makeText(ModuloPrincipal.this,"SERVIDORES EN MANTENIMIENTO... VUELVA A INTENTAR MAS TARDE ", Toast.LENGTH_LONG).show(); }
        }){
            @Override protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();

                     params.put("opcion","53");


                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(ModuloPrincipal.this);
        requestQueue.add(stringRequest);
    }

    private void BuscarServicios(String Buscar){
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVICIOS, new Response.Listener<String>() {
            @Override public void onResponse(String response) {
                Log.d("busca",response);
                if(!response.equalsIgnoreCase("fallo")) {
                    try {
                        String json = response;
                        //Log.e("servci",json);
                        if (json != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(json);
                                JSONArray contacs = jsonObj.getJSONArray("Consulta");
                                for (int i = 0; i < contacs.length(); i++) {
                                    JSONObject oj = contacs.getJSONObject(i);
                                    itemServicios.add(new Servicios(oj));
                                }
                                adapterServicios = new AdapterServicios(ModuloPrincipal.this, itemServicios);
                                listaServi.setAdapter(adapterServicios);
                            } catch (JSONException e) { e.printStackTrace(); }
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) { Toast.makeText(ModuloPrincipal.this,"SERVIDORES EN MANTENIMIENTO... VUELVA A INTENTAR MAS TARDE ", Toast.LENGTH_LONG).show(); }
        }){
            @Override protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();

                params.put("opcion","57");
                params.put("Busqueda",""+Buscar);


                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(ModuloPrincipal.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        adapterServicios.busqueda(s);
        Log.d("holis",s);
        return false;
    }
}