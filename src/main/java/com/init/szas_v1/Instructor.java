package com.init.szas_v1;

public class Instructor {
    private String nombre;
    private String apellido;
    private String correo;
    private String Cedula;
    private String Especialidad;
    private String Genero;
    private String telefono;
    private String direccion;
    private String id_instructor;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getIdentificacion() {
        return id_instructor;
    }
    public void setIdentificacion(String id_instructor) {
        this.id_instructor = id_instructor;
    }
    public String getCedula() {
        return Cedula;
    }
    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }
    public String getEspecialidad() {
        return Especialidad;
    }
    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }
    public String getGenero() {
        return Genero;
    }
    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

}
