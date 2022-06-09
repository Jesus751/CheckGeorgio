package cabanzo.checkgeorgio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import cabanzo.checkgeorgio.Temporales.ObjetosTemporales;

public class MainActivity extends AppCompatActivity {

    EditText usertel,userpass;
    Button Biniciarsec;
    String URL_USUARIOS="http://ubiexpress.net:5610/georgio/mobil/PanelUsusarios.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    String TIPO="";
    String ID_USER="";
    String NOMBRE="";
    String TELEFONO="";
    String CLAVE="";
    CheckBox checkBox;

    SharedPreferences misSharedPreferences;

    private static final String PREFS_KEY = "sesionactivado";
    private static final String ESTADO_BOTON = "estado.boton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        misSharedPreferences = getSharedPreferences("Mispreferencias", Activity.MODE_PRIVATE);

        usertel = findViewById(R.id.editTextTextPersonName);
        userpass = findViewById(R.id.editTextTextPassword);
        checkBox = findViewById(R.id.checkbox_login);

        Biniciarsec = findViewById(R.id.buttonLogin);

      //  if (!ObtieneUsuario().isEmpty() && !ObtienePass().isEmpty()){
        //    ValidarUsuario(ObtieneUsuario(),ObtienePass());
        //}


        Biniciarsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel= usertel.getText().toString();
                String pass=userpass.getText().toString();
                ValidarUsuario(tel,pass);
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { if (checkBox.isChecked()){ Guardar_estado_boton(); GuardarUsuario(); }
            } });

    }


    public void Guardar_estado_boton(){
        SharedPreferences settings = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putBoolean(ESTADO_BOTON, checkBox.isChecked());
        editor.apply();
    }

    public  boolean obtener_estado_boton( ){
        SharedPreferences settings = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        return settings.getBoolean(ESTADO_BOTON,false);
    }
    public static void  CambiarstadoButoon(Context c, boolean b){
        SharedPreferences preferences = c.getSharedPreferences(PREFS_KEY,MODE_PRIVATE);
        preferences.edit().putBoolean(ESTADO_BOTON,b).apply();
    }

    public void  GuardarUsuario(){
        SharedPreferences.Editor editor = misSharedPreferences.edit();
        editor.putString("sesionuser", ""+usertel.getText().toString());
        editor.putString("sesionpass", ""+userpass.getText().toString());
        editor.commit();
    }

    public String ObtieneUsuario(){
        String getUser;
        getUser = misSharedPreferences.getString("sesionuser","");
        return getUser;
    }

    public String ObtienePass(){
        String getPass;
        getPass = misSharedPreferences.getString("sesionpass","");
        return getPass;
    }


    private void ValidarUsuario(final String tel, final String pas){
        stringRequest = new StringRequest(Request.Method.POST, URL_USUARIOS, new Response.Listener<String>() {
            @Override public void onResponse(String response) { //Log.w("LOG_","RES "+response);
                if(!response.equalsIgnoreCase("fallo")) {
                    try {
                        String json = response;
                        if (json != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(json);
                                JSONArray contacs = jsonObj.getJSONArray("Consulta");
                                for (int i = 0; i < contacs.length(); i++) {
                                    JSONObject oj = contacs.getJSONObject(i);
                                    ID_USER = oj.getString("idUsuario_movil");
                                    NOMBRE = oj.getString("nombre");
                                    TIPO = oj.getString("tipo");

                                }
                            } catch (JSONException e) { e.printStackTrace(); }
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                    switch (TIPO){
                        case "repartidor":{
                            //ObjetosTemporales.GuardarUsuario(ID_USER, NOMBRE, TELEFONO, CLAVE, TIPO);
                            //startActivity(new Intent(getApplicationContext(), ModuloPrincipal.class));
                            Intent  i =  new Intent(getApplicationContext(),ModuloPrincipal.class);
                            i.putExtra("repar", TIPO);
                            startActivity(i);
                        };break;
                        case "chofer":{
                            //ObjetosTemporales.GuardarUsuario(ID_USER, NOMBRE, TELEFONO, CLAVE, TIPO);
                            startActivity(new Intent(getApplicationContext(), CheckMecanico.class));
                        };break;
                        case "recepcion":{
                           // ObjetosTemporales.GuardarUsuario(ID_USER, NOMBRE, TELEFONO, CLAVE, TIPO);
                           // startActivity(new Intent(getApplicationContext(),ModuloPrincipal.class));
                            Intent i =  new Intent(getApplicationContext(),ModuloPrincipal.class);
                            i.putExtra("recep", TIPO);
                            startActivity(i);

                        }; break;
                        case "admin":{

                        }; break;
                    }
                } else {
                    usertel.setError("LOS DATOS SON INCORRECTOS");
                    userpass.setError("LA CLAVE ES INCORRECTA"); }
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) { Log.w("LOG_","ERROR "+error);
                Toast.makeText(getApplicationContext(),"SERVIDORES EN MANTENIMIENTO... VUELVA A INTENTAR MAS TARDE ", Toast.LENGTH_LONG).show();
            }
        }){
            @Override protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("opcion","4");
                params.put("telefono", tel);
                params.put("password", pas); //Log.w("LOG_","PARAMS "+params);
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}