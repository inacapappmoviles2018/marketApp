package rboock.marketapp.modelo;

public class Producto {
    private String id,nombre,precio,direccion,descripcion,tiempoDuracion,categoria,uso;

    public Producto(String id,String nombre, String precio, String direccion, String descripcion, String tiempoDuracion, String categoria, String uso) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.tiempoDuracion = tiempoDuracion;
        this.categoria = categoria;
        this.uso = uso;
    }

    public Producto(String id, String nombre, String precio, String descripcion, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public Producto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(String tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }
}
