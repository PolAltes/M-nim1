package edu.upc.dsa.models;

public class UserPoint {
    Usuario user;
    PuntoInteres point;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }


    public PuntoInteres getPoint() {
        return point;
    }

    public void setPoint(PuntoInteres point) {
        this.point = point;
    }

    public UserPoint(Usuario user, PuntoInteres point){
        this.setUser(user);
        this.setPoint(point);
    }

    @Override
    public String toString(){
        return ("Relación[userID: "+user.getId()+", point:("+point.getHorizontal()+","+point.getVertical()+")]");
    }
}
