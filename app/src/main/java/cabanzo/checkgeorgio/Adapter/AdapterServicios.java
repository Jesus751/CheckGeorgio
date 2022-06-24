package cabanzo.checkgeorgio.Adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import cabanzo.checkgeorgio.Modelo.Servicios;
import cabanzo.checkgeorgio.R;

public class AdapterServicios extends BaseAdapter {
    Context context;
    ArrayList<Servicios> itemServicios;
    ArrayList<Servicios> itemsBusqueda;

    public AdapterServicios(Context context, ArrayList<Servicios> itemServicios){
        this.context=context;
        this.itemServicios = itemServicios;
        this.itemsBusqueda = new ArrayList<>();
        this.itemsBusqueda.addAll(itemServicios);
    }

    public  void  busqueda(final String txtbuscar){
        int longitud = txtbuscar.length();
        if(longitud == 0){
            itemServicios.clear();
            itemServicios.addAll(itemsBusqueda);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                List<Servicios> collecion = itemServicios.stream()
                        .filter(i ->i.getNombre().toLowerCase().contains(txtbuscar.toLowerCase()))
                        .collect(Collectors.toList());
                itemServicios.clear();
                itemServicios.addAll(collecion);
            }else{
                for (Servicios c: itemsBusqueda){
                    if(c.getNombre().toLowerCase().contains(txtbuscar.toLowerCase())){
                        itemServicios.add(c);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemServicios.size();
    }

    @Override
    public Object getItem(int i) {
        return itemServicios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v= view;
        if(v== null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_servicios_mecanicos,null);
        }
        Servicios serv=itemServicios.get(i);

        TextView txtIdservicio= v.findViewById(R.id.Text_IdServicio);
        TextView txtFecha= v.findViewById(R.id.Text_Fecha);
        TextView txtMarca= v.findViewById(R.id.textV_Marca);
        TextView txtModelo= v.findViewById(R.id.textV_Modelo);
        TextView txtAno= v.findViewById(R.id.textV_Ano);
        TextView txtMotor= v.findViewById(R.id.textV_Motor);
        TextView txtVin= v.findViewById(R.id.textV_Vin);
        TextView txtCliente= v.findViewById(R.id.textV_Cliente);
        TextView txtPlacas = v.findViewById(R.id.textV_Placas);
        TextView txtServicio = v.findViewById(R.id.textV_Servicio);
        ProgressBar BarChecklist = v.findViewById(R.id.progressBarCheck);
        ImageView imagenUnidad = v.findViewById(R.id.imageViewUnidad);


        txtIdservicio.setText(""+serv.getId_ser_venta());
        txtFecha.setText(""+serv.getFecha_ingreso());
        txtMarca.setText(""+serv.getMarca());
        txtModelo.setText(""+serv.getModelo());
        txtAno.setText(""+serv.getAnio());
        txtMotor.setText(""+serv.getMotor());
        txtVin.setText(""+serv.getVin());
        txtCliente.setText(""+serv.getNombre());
        txtPlacas.setText(""+serv.getPlacas());
        txtServicio.setText(""+serv.getMotivoingreso());
        BarChecklist.setMax(100); // 100 maximum value for the progress value
        BarChecklist.setProgress(50); // 50 default progress value for the progress bar
        Log.e("uiamgeb","http://ubiexpress.net:5610/georgio/web/imagenes/principal/"+serv.getFoto());
        Picasso.with(context).load("http://ubiexpress.net:5610/georgio/web/imagenes/principal/"+serv.getFoto()).into(imagenUnidad);


        return v;
    }
}
