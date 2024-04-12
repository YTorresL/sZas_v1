package com.init.szas_v1;

public class Matricula {
    private String id_matricula;
    private String id_estudiante;
    private String id_curso;
    private String fecha_matricula;
    private String estado;


    public String getId_matricula() {
        return id_matricula;
    }

    public void setIdentificacion(String id_matricula) {
        this.id_matricula = id_matricula;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setEstudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setCurso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getFecha_matricula() {
        return fecha_matricula;
    }

    public void setFecha(String fecha_matricula) {
        this.fecha_matricula = fecha_matricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
