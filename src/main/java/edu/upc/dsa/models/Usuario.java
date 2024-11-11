package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Usuario {
    String id;
    String nombre;
    String apellidos;
    String correo;
    String nacimiento;

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


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }


    public Usuario(){
        this.setId(RandomUtils.getId());
    }

    public Usuario(String id,String nombre, String apellidos,String correo, String nacimiento){
        this();
        if(id!=null) this.setId(id);
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setCorreo(correo);
        this.setNacimiento(nacimiento);
    }

    public Usuario(String nombre, String apellidos, String correo, String nacimiento){
        this(null, nombre, apellidos, correo, nacimiento);
    }

    @Override
    public String toString(){
        return "Usuario[id= "+id+", Nombre: "+nombre+", Apellidos: "+apellidos+", Correo: "+correo+", nacimiento: "+nacimiento+"]";
    }

}
