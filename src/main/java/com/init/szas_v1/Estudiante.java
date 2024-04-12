package com.init.szas_v1;

public class Estudiante {
    private String Id_Estudiante;
    private String cedula;
    private String nombre;
    private String apellido;
    private String genero;
    private String edad;
    private String telefono;
    private String direccion;
    private String correo;
    private String lugarNacimiento;

    // Agrega los demás campos según las columnas de tu tabla

    // Agrega getters y setters para todos los campos
    public String getIdentificacion() {
        return Id_Estudiante;
    }

    public void setIdentificacion(String Id_Estudiante) {
        this.Id_Estudiante = Id_Estudiante;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

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
    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
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
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }
    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }
    // Agrega getters y setters para los demás campos según sea necesario
}

