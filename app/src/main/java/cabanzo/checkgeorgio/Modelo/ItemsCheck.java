package cabanzo.checkgeorgio.Modelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ItemsCheck  implements Serializable {

    String idcheck="";
    String categoria="";
    String descripcion="";

    public ItemsCheck(JSONObject object){
        try {
            this.idcheck=object.getString("id_check_list");
            this.descripcion=object.getString("nombre_check");
            this.categoria=object.getString("id_item");
        } catch (JSONException e){e.printStackTrace();}

    }

    public String getIdcheck() {
        return idcheck;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getDescripcion() {
        return descripcion;
    }



}
