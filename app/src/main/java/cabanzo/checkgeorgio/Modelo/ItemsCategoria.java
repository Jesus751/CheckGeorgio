package cabanzo.checkgeorgio.Modelo;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemsCategoria {
    String idcheck="";
    String iditem="";
    String descripcion="";
    String comentarios="";
    String estado="";
    String id_item_mec="";
    String categoria="";
    String id_ser_venta="";
    String urlvideo="";
    String urlfoto="";


    public ItemsCategoria(JSONObject object){
        try{
            this.idcheck=object.getString("idcheck");
            this.iditem=object.getString("iditem");
            this.descripcion=object.getString("descripcion");
            this.comentarios=object.getString("comentarios");
            this.estado=object.getString("estado");
            this.id_item_mec=object.getString("id_item_mec");
            this.categoria=object.getString("categoria");
            this.id_ser_venta=object.getString("id_ser_venta");
            this.urlvideo=object.getString("urlvideo");
            this.urlfoto=object.getString("urlfoto");

        }
        catch (JSONException e){e.printStackTrace();}
    }

    public String getUrlvideo() {
        return urlvideo;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIditem() {
        return iditem;
    }

    public String getEstado() {
        return estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getId_ser_venta() {
        return id_ser_venta;
    }

    public String getId_item_mec() {
        return id_item_mec;
    }

    public String getIdcheck() {
        return idcheck;
    }
}
