package cabanzo.checkgeorgio.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import cabanzo.checkgeorgio.Modelo.ItemsCheck;
import cabanzo.checkgeorgio.R;

public class AdapterCheckList  extends  RecyclerView.Adapter<AdapterCheckList.Holder> {
    Context context;
    List<ItemsCheck> itemChecks;
    String idservX = "";
    String UPLOAD_URL = "http://ubiexpress.net:5610/WebServiceGeorgioMovil/PanelCheckList.php";

    public AdapterCheckList(Context context, List<ItemsCheck> itemsCheck, String idservicio) {
        this.context = context;
        this.itemChecks = itemsCheck;
        this.idservX = idservicio;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_check_datos, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCheckList.Holder holder, int position) {
        holder.TDescripcion.setText(itemChecks.get(position).getDescripcion());
        holder.TIdeItem.setText(itemChecks.get(position).getIdcheck());
        holder.Tcategoria.setText(itemChecks.get(position).getCategoria());
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return itemChecks.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView TIdeItem, TDescripcion, Tcategoria;

        Button edit, delete;
        EditText nombrreCheck;
        //context
        Context context;


        public Holder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            TDescripcion = itemView.findViewById(R.id.textViewnombreChek);
            delete = itemView.findViewById(R.id.btnEliminar);
            edit = itemView.findViewById(R.id.btnEditar);
            TIdeItem = itemView.findViewById(R.id.textView_idItem_dato);
            Tcategoria = itemView.findViewById(R.id.textView_idItem_Cate);
            nombrreCheck = itemView.findViewById(R.id.editTextTextNombreCheck);
        }

        void setOnClickListener() {

            // Eliminar Check List
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                    alerta.setMessage("Deseas Eliminar ")

                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    eliminarChecklist();
                                    notifyItemRemoved(getPosition());
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("Eliminar");
                    titulo.show();

                }

                private void eliminarChecklist() {
                    final ProgressDialog dialog = ProgressDialog.show(context, "Eliminando", "Espere por favor");
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("fallo", "1" + response);
                                    dialog.dismiss();
                                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("fallo", "2" + error.getMessage());
                            dialog.dismiss();
                            Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new Hashtable<String, String>();
                            params.put("idck", TIdeItem.getText().toString());
                            params.put("opcion", "3");
                            Log.e("Respuesta ", " --- :" + params);
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(stringRequest);
                }
            });

            // Editar Check List
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = TIdeItem.getText().toString();
                    String descripcheck = TDescripcion.getText().toString();
                    VentaModificar(id, descripcheck);
                }
                // funcion que genera el alertDialog y la retornamos a donde sea llamada
                private Dialog VentaModificar(final String idcheck, String Descripcion) {

                    final Dialog builder = new Dialog(context);
                    builder.setContentView(R.layout.editcheck);

                    EditText txtDescripcion = builder.findViewById(R.id.editTextTextNombreCheckk);
                    Button btnGuardar = builder.findViewById(R.id.buttonGuardar);

                    builder.show();
                    txtDescripcion.setText(Descripcion);

                    btnGuardar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Descripcionnueva = txtDescripcion.getText().toString();
                            editarCheckList(Descripcionnueva);
                        }
                    });

                    return builder;
                }


                private void editarCheckList(String Descripcionnueva) {
                    final ProgressDialog dialog = ProgressDialog.show(context, "Editando", "Espere por favor");
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("repuesta ---", "fallo en--" + response);
                                    dialog.dismiss();
                                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Respuesta --", "Fallo en --" + error.getMessage());
                            dialog.dismiss();
                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            String nombreCheck = Descripcionnueva.trim().toUpperCase();
                            Map<String, String> params = new Hashtable<String, String>();
                            params.put("nombechecLi", nombreCheck);
                            params.put("idiChLi", TIdeItem.getText().toString());
                            params.put("idcat", Tcategoria.getText().toString());
                            params.put("opcion", "2");
                            Log.e("Error---", "Enn --" + params);
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(stringRequest);
                }
            });
        }

    }
}


