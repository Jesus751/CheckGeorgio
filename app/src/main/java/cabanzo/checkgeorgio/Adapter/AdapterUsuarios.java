package cabanzo.checkgeorgio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cabanzo.checkgeorgio.Modelo.ItemsUsu;
import cabanzo.checkgeorgio.R;

public class AdapterUsuarios  extends RecyclerView.Adapter<AdapterUsuarios.Holder> {

    Context context;
    List<ItemsUsu> itemsUsu;
    String idUsuario = "";

    public AdapterUsuarios(Context context, List<ItemsUsu> itemsUsu, String idUsuario ){
        this.context = context;
        this.itemsUsu =  itemsUsu;
        this.idUsuario = idUsuario;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_usuarios,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUsuarios.Holder holder, int position) {
        holder.TItem.setText(itemsUsu.get(position).getIdcheck());
        holder.TNombre.setText(itemsUsu.get(position).getIdcheck());
        holder.TApellido.setText(itemsUsu.get(position).getApellido());
        holder.TCorreo.setText(itemsUsu.get(position).getCorreo());
        holder.TTelefono.setText(itemsUsu.get(position).getTelefono());
        holder.TPassword.setText(itemsUsu.get(position).getPassword());
        holder.TTipo.setText(itemsUsu.get(position).getTipo());
        holder.setOnClickListener();



    }

    @Override
    public int getItemCount() {
      return itemsUsu.size();
    }

    public class Holder  extends   RecyclerView.ViewHolder{
        TextView TItem, TNombre, TApellido, TCorreo, TTelefono ,TPassword, TTipo;
        Button editUsu, deleteUsu;
        ImageView foto;
        Context context;

        public Holder(View itemView) {
            super(itemView);

            context =  itemView.getContext();
            TItem   =  itemView.findViewById(R.id.textView_idItem_Us);
            TNombre =  itemView.findViewById(R.id.textDatosUsuNombre);
            TApellido =  itemView.findViewById(R.id.textDatosUsuApellido);
            TCorreo =  itemView.findViewById(R.id.textDatosUsuCorreo);
            TTelefono =  itemView.findViewById(R.id.textDatosUsuTelefono);
            TPassword =  itemView.findViewById(R.id.textDatosUsuPassword);
            TTipo =  itemView.findViewById(R.id.textDatosUsuTipo);
            foto  = itemView.findViewById(R.id.imagenViewFotoUsu);
            editUsu =  itemView.findViewById(R.id.btnEditarUsu);
            deleteUsu =  itemView.findViewById(R.id.btnEliminarUsu);

        }

        void  setOnClickListener(){
            deleteUsu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Eliminando", Toast.LENGTH_SHORT).show();
                }
            });
            editUsu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Editando", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }


}

