package com.init.szas_v1;

public class Pago {
    private String Id_Pago;
    private String Estudiante;
    private String Referencia;
    private String Fecha;
    private String Monto;
    private String Descripcion;
    private String Metodo;

    // Agrega los demás campos según las columnas de tu tabla

    // Agrega getters y setters para todos los campos
    public String getIdentificacion() {
        return Id_Pago;
    }

    public void setIdentificacion(String Id_Pago) {
        this.Id_Pago = Id_Pago;
    }

    public String getEstudiante() {
        return Estudiante;
    }

    public void setEstudiante(String Estudiante) {
        this.Estudiante = Estudiante;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getMetodo() {
        return Metodo;
    }

    public void setMetodo(String Metodo) {
        this.Metodo = Metodo;
    }
    // Agrega getters y setters para los demás campos según sea necesario
}
