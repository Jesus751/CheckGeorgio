package cabanzo.checkgeorgio.Modelo;

import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ItemsUsu implements Serializable {

    String idUsu="";
    String idcheck="";
    String nombre="";
    String apellido="";
    String correo="";
    String telefono ="";
    String password="";
    String tipo = "";


    public ItemsUsu(JSONObject object){
        try {
            this.idcheck= object.getString("idcheck");
            this.nombre= object.getString("nombre");
            this.apellido=object.getString("apellido");
            this.correo=object.getString("correo");
            this.telefono=object.getString("telefono");
            this.password=object.getString("password");
            this.tipo=object.getString("tipo");
        }catch (JSONException e){ e.printStackTrace();}
    }

    public  String getIdcheck(){return idcheck;}
    public  String getNombre(){return  nombre;}
    public  String getApellido(){return  apellido;}
    public  String getCorreo(){return  correo;}
    public String getTelefono(){return telefono;}
    public  String getPassword(){return  password;}
    public String getTipo(){return  tipo;}
}
