package cabanzo.checkgeorgio.Modelo;

public class ItemsTemporal {
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
    String nombrevideo="";
    String nombrefoto="";


    public ItemsTemporal(String Xidcheck,
            String Xiditem,
            String Xdescripcion,
            String Xcomentarios,
            String Xestado,
            String Xid_item_mec,
            String Xcategoria,
            String Xid_ser_venta,
            String Xurlvideo,
            String Xurlfoto,String Xnombrevideo,
                                 String Xnombrefoto){
        this.idcheck=Xidcheck;
        this.iditem=Xiditem;
        this.descripcion=Xdescripcion;
        this.comentarios=Xcomentarios;
        this.estado=Xestado;
        this.id_item_mec=Xid_item_mec;
        this.categoria=Xcategoria;
        this.id_ser_venta=Xid_ser_venta;
        this.urlvideo=Xurlvideo;
        this.urlfoto=Xurlfoto;
        this.nombrefoto = Xnombrefoto;
        this.nombrevideo = Xnombrevideo;


    }

    public String getIdcheck() {
        return idcheck;
    }

    public String getId_item_mec() {
        return id_item_mec;
    }

    public String getId_ser_venta() {
        return id_ser_venta;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEstado() {
        return estado;
    }

    public String getIditem() {
        return iditem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public String getUrlvideo() {
        return urlvideo;
    }

    public String getNombrefoto() {
        return nombrefoto;
    }

    public String getNombrevideo() {
        return nombrevideo;
    }

    public void setNombrefoto(String nombrefoto) {
        this.nombrefoto = nombrefoto;
    }

    public void setNombrevideo(String nombrevideo) {
        this.nombrevideo = nombrevideo;
    }

    public void setUrlvideo(String urlvideo) {
        this.urlvideo = urlvideo;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public void setIditem(String iditem) {
        this.iditem = iditem;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId_item_mec(String id_item_mec) {
        this.id_item_mec = id_item_mec;
    }

    public void setId_ser_venta(String id_ser_venta) {
        this.id_ser_venta = id_ser_venta;
    }

    public void setIdcheck(String idcheck) {
        this.idcheck = idcheck;
    }
}
