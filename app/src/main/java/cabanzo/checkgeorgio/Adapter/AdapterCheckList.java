package cabanzo.checkgeorgio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import cabanzo.checkgeorgio.Modelo.ItemsCheck;
import cabanzo.checkgeorgio.R;

public class AdapterCheckList  extends  RecyclerView.Adapter<AdapterCheckList.Holder> {
    Context context;
    List<ItemsCheck> itemChecks;
    String idservX = "";


    public AdapterCheckList(Context context, List<ItemsCheck> itemsCheck, String idservicio) {
        this.context = context;
        this.itemChecks = itemsCheck;
        this.idservX = idservicio;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_check_datos,parent,false);
        return new Holder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterCheckList.Holder holder, int position) {
        holder.TDescripcion.setText(itemChecks.get(position).getDescripcion());
        holder.TIdeItem.setText(itemChecks.get(position).getIdcheck());
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return itemChecks.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView TIdeItem, TDescripcion;
        Button edit, delete;
        //context
        Context context;


        public Holder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            TIdeItem = itemView.findViewById(R.id.textView_idItem_dato);
            TDescripcion = itemView.findViewById(R.id.textViewnombreChek);
            delete =  itemView.findViewById(R.id.btnEliminar);
            edit  = itemView.findViewById(R.id.btnEditar);
        }

        void setOnClickListener(){
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Eliminado", Toast.LENGTH_SHORT).show();
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Editado", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }
}
