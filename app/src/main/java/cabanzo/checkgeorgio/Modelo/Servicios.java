package cabanzo.checkgeorgio.Modelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Servicios {
    String id_ser_venta="";
    String id_ser_unidad="";
    String id_ser_checklist="";
    String id_check_mecanico="";
    String id_ser_anomalia="";
    String id_ser_refacciones="";
    String id_ser_cliente="";
    String idcliente="";
    String nombre="";
    String telefono="";
    String fecha_ingreso="";
    String hora_ingreso="";
    String fecha_salida="";
    String hora_salida="";
    String motivoingreso="";
    String observaciones="";
    String estatus="";
    String kilometraje="";
    String id_serv_unidad="";
    String marca="";
    String modelo="";
    String anio="";
    String placas="";
    String km="";
    String vin="";
    String motor="";
    String foto="";

    public Servicios(JSONObject object){
        try{
            this.id_ser_venta=object.getString("id_ser_venta");
            this.id_ser_unidad= object.getString("id_ser_unidad");
            this.id_ser_checklist=object.getString("id_ser_checklist");
            this.id_check_mecanico=object.getString("id_check_mecanico");
            this.id_ser_anomalia=object.getString("id_ser_anomalia");
            this.id_ser_refacciones=object.getString("id_ser_refacciones");
            this.id_ser_cliente=object.getString("id_ser_cliente");
            this.idcliente=object.getString("id_ser_cliente");
            this.nombre=object.getString("nombre");
            this.telefono=object.getString("telefono");
            this.fecha_ingreso=object.getString("fecha_ingreso");
            this.hora_ingreso=object.getString("hora_ingreso");
            this.fecha_salida=object.getString("fecha_salida");
            this.hora_salida=object.getString("hora_salida");
            this.motivoingreso=object.getString("motivoingreso");
            this.observaciones=object.getString("observaciones");
            this.estatus=object.getString("estatus");
            this.kilometraje=object.getString("kilometraje");
            this.id_serv_unidad=object.getString("id_serv_unidad");
            this.marca=object.getString("marca");
            this.modelo=object.getString("modelo");
            this.anio=object.getString("anio");
            this.placas=object.getString("placas");
            this.km=object.getString("km");
            this.vin=object.getString("vin");
            this.motor=object.getString("motor");
            this.foto=object.getString("foto");
        }
        catch (JSONException e){e.printStackTrace();}
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public String getHora_ingreso() {
        return hora_ingreso;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getAnio() {
        return anio;
    }

    public String getId_check_mecanico() {
        return id_check_mecanico;
    }

    public String getId_ser_anomalia() {
        return id_ser_anomalia;
    }

    public String getId_ser_checklist() {
        return id_ser_checklist;
    }

    public String getId_ser_cliente() {
        return id_ser_cliente;
    }

    public String getId_ser_refacciones() {
        return id_ser_refacciones;
    }

    public String getFoto() {
        return foto;
    }

    public String getId_ser_unidad() {
        return id_ser_unidad;
    }

    public String getId_ser_venta() {
        return id_ser_venta;
    }

    public String getId_serv_unidad() {
        return id_serv_unidad;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public String getKm() {
        return km;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMotivoingreso() {
        return motivoingreso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMotor() {
        return motor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getPlacas() {
        return placas;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getVin() {
        return vin;
    }
}
