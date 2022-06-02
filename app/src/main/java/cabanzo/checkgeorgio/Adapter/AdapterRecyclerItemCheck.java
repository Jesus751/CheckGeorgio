package cabanzo.checkgeorgio.Adapter;

import static android.app.Activity.RESULT_OK;
import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cabanzo.checkgeorgio.CheckCategoria;
import cabanzo.checkgeorgio.Modelo.ItemsCategoria;
import cabanzo.checkgeorgio.Modelo.ItemsTemporal;
import cabanzo.checkgeorgio.R;
import cabanzo.checkgeorgio.cameraActivity;

public class AdapterRecyclerItemCheck extends RecyclerView.Adapter<AdapterRecyclerItemCheck.Holder>{
    Context context;
    List<ItemsCategoria> itemChecks;
    List<ItemsTemporal> itemsTemporals=new ArrayList<>();
    String idservX="",idMecanicoX;
    Button BGuardar;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String URL_CATEGORIAS = "http://ubiexpress.net:5610/georgio/mobil/PanelCompras.php";



    public static final int REQUEST_CODE_TAKE_VIDEO =  1;

    public AdapterRecyclerItemCheck(Context context, List<ItemsCategoria> itemsCategorias, String idservicio, String idMecanico, Button ButonGuardar){
        this.context=context;
        this.itemChecks=itemsCategorias;
        this.idservX=idservicio;
        this.idMecanicoX=idMecanico;
        this.BGuardar=ButonGuardar;

    }
    @Override public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.item_check, parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position){
        holder.TDescripcion.setText(itemChecks.get(position).getDescripcion());
        holder.TIdeItem.setText(itemChecks.get(position).getIdcheck());
        holder.setOnClickListener();
    }




    @Override
    public int getItemCount() {
        return itemChecks.size();
    }



    public class Holder extends RecyclerView.ViewHolder {
        //ImageView ImgProductoTemp;
        CheckBox ICheck;
        TextView TIdeItem,TDescripcion,Tfoto;
        ImageView ImgComentarios,ICamaras;
        EditText TComentarios;
        ImageView TimgViewFoto;

        //context
        Context context;



        public Holder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            TIdeItem = itemView.findViewById(R.id.textView_idItem);
            ICheck = itemView.findViewById(R.id.checkBoxMec);
            TDescripcion = itemView.findViewById(R.id.textViewDescrip);
            ImgComentarios = itemView.findViewById(R.id.imageViewComent);
            ICamaras = itemView.findViewById(R.id.imageViewCamera);
            TComentarios = itemView.findViewById(R.id.editTextTextMultiLine);
            TimgViewFoto = itemView.findViewById(R.id.imageView);
            Tfoto = itemView.findViewById(R.id.Textnamefoto);
        }

        public CheckBox getICheck() {
            return ICheck;
        }

        public ImageView getICamaras() {
            return ICamaras;
        }

        public ImageView getImgComentarios() {
            return ImgComentarios;
        }

        public TextView getTDescripcion() {
            return TDescripcion;
        }
        public TextView getTfoto(){return Tfoto;}


        void setOnClickListener(){
            //ICheck.setOnClickListener(this);
            //ICamaras.setOnClickListener(this);
            //ImgComentarios.setOnClickListener(this);
            ICamaras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, cameraActivity.class);
                    intent.putExtra("headerCode",TIdeItem.getText());
                    context.startActivity(intent);

                }
            });


            ICheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(!buscarLista(itemsTemporals,TIdeItem.getText().toString())){
                        itemsTemporals.add(new ItemsTemporal(TIdeItem.getText().toString(),
                                TIdeItem.getText().toString(),
                                TDescripcion.getText().toString(),
                                TComentarios.getText().toString(),
                                "ACTUALIZADO",
                                ""+idMecanicoX,
                                "1",
                                ""+idservX,
                                "NA",
                                "NA","",""));
                    }
                    else {
                        Log.d("richi","Se actualiza lista");
                        itemsTemporals=ActualizaLista(itemsTemporals,TIdeItem.getText().toString());
                    }




                }
            });
            ImgComentarios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // recorrerlista(itemsTemporals);

                    abrirCamara();

                }
            });

            BGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.e("Xrichi","Click click click click");
                    SubirCambios();
                }
            });
        }



        private  void  SubirCambios(){

            String Idcheck="";
            String Descripcion="";
            String Id_item_mec="";
            String Id_ser_venta="";
            String Categoria="";
            String Estado="";
            String Iditem="";
            String Comentarios="";
            String Urlfoto="";
            String Urlvideo="";
            for (ItemsTemporal profilo : itemsTemporals) {
                
                profilo.getIdcheck();
                profilo.getDescripcion();
                profilo.getId_item_mec();
                profilo.getId_ser_venta();
                profilo.getCategoria();
                profilo.getEstado();
                profilo.getIditem();
                profilo.getComentarios();
                profilo.getUrlfoto();
                profilo.getUrlvideo();
                Log.e("Update ","UPDATE ser_checklist_mecanico" +
                        "                SET" +
                        "                iditem = "+profilo.getIditem() +
                        "descripcion = "+profilo.getDescripcion()  +
                        "comentarios = "+profilo.getComentarios() +
                        "estado =  'activo'," +
                        "id_item_mec = "+profilo.getId_item_mec() +
                        "categoria = "+profilo.getCategoria() +
                        "id_ser_venta "+profilo.getId_ser_venta()  +
                        "urlvideo "+ profilo.getUrlvideo()  +
                        "urlfoto "+ profilo.getUrlfoto() +"WHERE idcheck ="+ profilo.getIdcheck()+";");

                ActualizaItemsCategoria(
                        profilo.getIdcheck(),
                        profilo.getIditem(),
                        profilo.getDescripcion(),
                        profilo.getComentarios(),
                        profilo.getEstado(),
                        profilo.getId_item_mec(),
                        profilo.getCategoria(),
                        profilo.getId_ser_venta(),
                        profilo.getUrlvideo(),
                        profilo.getUrlfoto() );
Log.e("HOLA FOTO",profilo.getUrlfoto()+"---"+profilo.getNombrefoto());


                File image = new File(profilo.getUrlfoto(), profilo.getNombrefoto());
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(profilo.getUrlfoto(),bmOptions);
                Log.w("HOLA FOTO: ","X0");
                //bitmap = Bitmap.createScaledBitmap(bitmap,30,30,true);
                Log.w("HOLA FOTO33: ",profilo.getNombrefoto());
                SubirFoto(bitmap,profilo.getNombrefoto());





            }
        }



        //COMPRIME LA IMAGEN
        public String getStringImagen(final Bitmap bmp) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            return encodedImage;
        }

        private void SubirFoto(Bitmap Imagenx,String nombrex){
            Log.w("HOLA FOTO: ","XXX2"+nombrex);
            final ProgressDialog loading = ProgressDialog.show(context, "Cargando Fotografia", "Por favor espere.");
            stringRequest = new StringRequest(Request.Method.POST, URL_CATEGORIAS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.w("HOLA FOTO ERROR: ",""+response);
                    if (response.equals("exitoso")){
                        loading.dismiss();
                        //objetosTemporales.GuardarFotoUsuario(KEY_NOMBRE);
                    }
                }
            }, new Response.ErrorListener() {
                @Override public void onErrorResponse(VolleyError error) {
                    Log.w("HOLA FOTO errrro: ",""+error.toString());
                    loading.dismiss();
                    Toast.makeText(context,"SERVIDORES EN MANTENIMIENTO... REINTENTE MAS TARDE ",Toast.LENGTH_LONG).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    String imagen = getStringImagen(Imagenx);
                    String nombre = nombrex;
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("opcion","56");
                    params.put("foto", imagen);
                    params.put("name", nombre);
                    Log.w("HOLA FOTO: ",""+nombre);
                    return params;

                }
            };
            requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }


        private void ActualizaItemsCategoria(
                String Zidcheck ,
                String Ziditem ,
                String Zdescripcion ,
                String Zcomentarios ,
                String Zestado ,
                String Zid_item_mec ,
                String Zcategoria ,
                String Zid_ser_venta ,
                String Zurlvideo ,
                String Zurlfoto ){
            stringRequest = new StringRequest(Request.Method.POST, URL_CATEGORIAS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("mens", "ss" + response);
                    if (!response.isEmpty()) {

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,"No se ACTUALIZO el check",Toast.LENGTH_LONG).show();
                }

            }){
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
                    HashMap<String,String> params = new HashMap<String,String>();
                    params.put("opcion","55");
                    params.put("idcheck",Zidcheck);
                    params.put("iditem",Ziditem);
                    params.put("descripcion",Zdescripcion);
                    params.put("comentarios",Zcomentarios);
                    params.put("estado","REVIZADO");
                    params.put("id_item_mec",Zid_item_mec);
                    params.put("categoria",Zcategoria);
                    params.put("id_ser_venta",Zid_ser_venta);
                    params.put("urlvideo",Zurlvideo);
                    params.put("urlfoto",Zurlfoto);
                    return params;

                }
            };
            requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }

        private void recorrerlista(List<ItemsTemporal> ListaCheck){
            Log.w("iter1","aqui entro");
            Iterator<ItemsTemporal> it = ListaCheck.iterator();
// mientras al iterador queda proximo juego
            while(it.hasNext()){
                ItemsTemporal item=it.next();
                Log.w("iter1",item.toString());
                Log.w("iter2" , item.getDescripcion());
            }
        }

        private Boolean buscarLista(List<ItemsTemporal> ListaCheck,String idBuscar){
            Boolean resultado=false;
                for (ItemsTemporal profilo : ListaCheck) {
                    Log.e("Busqueda::",profilo.getIdcheck()+"="+idBuscar);
                    if (profilo.getIdcheck().equals(idBuscar)) {
                        //System.out.println(" " + profilo.getNombre());
                        resultado=true;
                    }else{
                        System.out.println("NO SE ENCONTRO");
                    }
                }
            return resultado;
        }

        private List<ItemsTemporal> ActualizaLista(List<ItemsTemporal> ListaCheck,String idBuscar){
            List<ItemsTemporal> itemsAct=new ArrayList<>();
            int indice=0;
            for (ItemsTemporal profilo : ListaCheck) {
                Log.e("Busqueda33::",profilo.getIdcheck()+"="+idBuscar);

                if (profilo.getIdcheck().equals(idBuscar)) {
                    //System.out.println(" " + profilo.getNombre());
                    profilo.setDescripcion(profilo.getDescripcion()+"== Server");
                    Log.e("Busqueda actualiza::",profilo.getIdcheck()+"="+idBuscar);
                    //ListaCheck.get(indice).setDescripcion("actualiza"+ indice);
                }else{
                    System.out.println("NO SE ENCONTRO");
                }
                indice++;
            }
            itemsAct=ListaCheck;
            return itemsAct;
        }

        private List<ItemsTemporal> ActualizaListaFoto(List<ItemsTemporal> ListaCheck,String idBuscar,String urlfoto,String namefoto){
            List<ItemsTemporal> itemsAct=new ArrayList<>();
            int indice=0;
            for (ItemsTemporal profilo : ListaCheck) {
                Log.e("Busqueda33::",profilo.getIdcheck()+"="+idBuscar);

                if (profilo.getIdcheck().equals(idBuscar)) {
                    //System.out.println(" " + profilo.getNombre());
                    profilo.setNombrefoto(namefoto);
                    profilo.setUrlfoto(urlfoto);
                    Log.e("Busqueda actualiza::",profilo.getIdcheck()+"="+idBuscar);
                    //ListaCheck.get(indice).setDescripcion("actualiza"+ indice);
                }else{
                    System.out.println("NO SE ENCONTRO");
                }
                indice++;
            }
            itemsAct=ListaCheck;
            return itemsAct;
        }


        static final int REQUEST_VIDEO_CAPTURE = 1;

        private void dispatchTakeVideoIntent() {
            Intent intento1 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            Log.e("video",itemView.getContext().getExternalFilesDir(null).toString());

            File video = new File(context.getExternalFilesDir(null), "video"+System.currentTimeMillis()+".mp4");
            intento1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(video));
            context.startActivity(intento1);
        }


private void cameraIntent() {

    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
        File photoFile = null;
        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
        photoFile =root; //createImageFile();

        if (photoFile != null) {
            Uri uri = FileProvider.getUriForFile(context.getApplicationContext(),
                    "packagename.fileprovider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
           // startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_VIDEO);
        }
    }
}

        // Tomar Foto
        private void abrirCamara() {




        }


    }

}

