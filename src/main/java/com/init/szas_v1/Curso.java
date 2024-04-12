package com.init.szas_v1;

public class Curso {
    private String Id_Curso;
    private String Nombre;
    private String Instructor;
    private String Costo;

    // Agrega los demás campos según las columnas de tu tabla

    // Agrega getters y setters para todos los campos
    public String getIdentificacion() {
        return Id_Curso;
    }

    public void setIdentificacion(String Id_Curso) {
        this.Id_Curso = Id_Curso;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String Instructor) {
        this.Instructor = Instructor;
    }

    public String getCosto() {
        return Costo;
    }

    public void setCosto(String Costo) {
        this.Costo = Costo;
    }
    // Agrega getters y setters para los demás campos según sea necesario
}
