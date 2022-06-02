package cabanzo.checkgeorgio.Modelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuarios {
    String iduser;
    String Nombre;
    String Tipouser;

    public Usuarios(JSONObject object){
        try{
            this.iduser=object.getString("iduser");
            this.Nombre= object.getString("Nombre");
            this.Tipouser= object.getString("tipo");
        }
        catch (JSONException e){e.printStackTrace();}
    }

    public String getIduser(){return  iduser;}
    public String getNombre(){return  Nombre;}
    public  String getTipouser(){return  Tipouser;}
}

