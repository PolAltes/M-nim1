package edu.upc.dsa.models;

public class PuntoInteres {
    String element;
    int horizontal;
    int vertical;

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }


    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }


    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }


    public PuntoInteres(String element, int horiz, int vert){
        this.setElement(element);
        this.setHorizontal(horiz);
        this.setVertical(vert);
    }

    @Override
    public String toString(){
        return "Punto[element: "+element+", coordenadas: ("+horizontal+","+vertical+")]";
    }

}
