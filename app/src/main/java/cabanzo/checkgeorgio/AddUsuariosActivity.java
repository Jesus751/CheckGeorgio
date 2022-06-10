package cabanzo.checkgeorgio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

public class AddUsuariosActivity extends AppCompatActivity {

    EditText nombreUsua, apellidoUsua,correoUsua, telefonoUsua, contraseñaUsua, tipoUsua;
    ImageView fotoUsua, imagenFotoUsua;
    Button guardarUsUsua;

    String UPLOAD_URL = "http://ubiexpress.net:5610/georgio/mobil/PanelCompras.php";
    String KEY_NOMBRE = "nombre";
    String KEY_APELLIDO = "apellido";
    String KEY_CORREO = "correo";
    String KEY_TELEFONO = "telefono";
    String KEY_PASSWORD = "password";
    String KEY_FOTO = "foto";
    String KEY_TIPO = "tipo";

    Bitmap bitmap;
    private static final int REQUEST_IMAGE_CAMERA = 101;
    private  static final  int REQUEST_PERMISSION_CAMERA = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usuarios);

        nombreUsua =  findViewById(R.id.editTextNombre);
        apellidoUsua = findViewById(R.id.editTextApellido);
        correoUsua =  findViewById(R.id.editTextCorreo);
        telefonoUsua = findViewById(R.id.editTextTelefono);
        contraseñaUsua = findViewById(R.id.editTextPassword);
        tipoUsua = findViewById(R.id.editTextTipo);
        fotoUsua = findViewById(R.id.btnFoto);
        imagenFotoUsua =  findViewById(R.id.imagenViewFoto);
        guardarUsUsua =  findViewById(R.id.btnGuardarUsu);


        // BOTON TOMAR FOTO
        fotoUsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ActivityCompat.checkSelfPermission(AddUsuariosActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        abrirCamara();
                    }else{
                        ActivityCompat.requestPermissions(AddUsuariosActivity.this, new String[]{Manifest.permission.CAMERA},REQUEST_PERMISSION_CAMERA);
                    }
                }else {
                    abrirCamara();
                }
            }
        });

    }




    // FUNCION PARA ABRIR LA CAMARA

    private void abrirCamara(){
        Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,REQUEST_IMAGE_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAMERA){
            if(permissions.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                abrirCamara();
            }else{
                Toast.makeText(this, "Se Necesita Habilitar Los Permisos", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAMERA){
            if (resultCode == Activity.RESULT_OK){
                bitmap = (Bitmap) data.getExtras().get("data");
                imagenFotoUsua.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    //Comvertir la imagen a un string
    public  String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos =  new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] imageBytes = baos. toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    // Subir Usuario Ala Base De Datos

    public void  guardarUsuario (){
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Subiendo", "Espere Por Favor");
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Fallo", "1" + response);
                        progressDialog.dismiss();
                        Toast.makeText(AddUsuariosActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Fallo", "2"+ error.getMessage());
                progressDialog.dismiss();
                Toast.makeText(AddUsuariosActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String nombre = nombreUsua.getText().toString().trim().toUpperCase();
                String apellido = apellidoUsua.getText().toString().trim().toUpperCase();
                String correo = correoUsua.getText().toString().trim().toUpperCase();
                String telefono = telefonoUsua.getText().toString().trim().toUpperCase();
                String contraseña = contraseñaUsua.getText().toString().trim().toUpperCase();
                String tipo = tipoUsua.getText().toString().trim().toUpperCase();
                String imagen = getStringImagen(bitmap);

                Map<String, String> params = new Hashtable<String, String>();
                params.put(KEY_NOMBRE, nombre);
                params.put(KEY_APELLIDO, apellido);
                params.put(KEY_CORREO, correo);
                params.put(KEY_TELEFONO, telefono);
                params.put(KEY_PASSWORD, contraseña);
                params.put(KEY_TIPO, tipo);
                params.put(KEY_FOTO, imagen);
                params.put("opcion", "56" );
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}