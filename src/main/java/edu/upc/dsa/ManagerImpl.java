package edu.upc.dsa;

import edu.upc.dsa.exceptions.RelationNotFoundException;
import edu.upc.dsa.exceptions.UsuarioNotFoundException;
import edu.upc.dsa.exceptions.PuntoInteresNotFoundException;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.UserPoint;

import org.apache.log4j.Logger;

import javax.management.relation.Relation;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ManagerImpl implements Manager {
    private static Manager instance;
    List<Usuario> userList;
    List<PuntoInteres> pointList;
    List<UserPoint> relationList;
    String[] ElementType= {"DOOR", "WALL", "BRIDGE","POTION","SWORD","COIN","GRASS","TREE"};
    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    private ManagerImpl(){
        this.userList = new ArrayList<>();
        this.pointList = new ArrayList<>();
        this.relationList = new ArrayList<>();
    }

    public static Manager getInstance(){
        if(instance == null) instance = new ManagerImpl();
        return instance;
    }

    public int usersSize(){
        logger.info("Users size: "+userList.size());
        return this.userList.size();
    }
    public int pointsSize(){
        logger.info("Points size: "+pointList.size());
        return this.pointList.size();
    }
    public int relationsSize(){
        logger.info("Relations size "+relationList.size());
        return this.relationList.size();
    }

    public void clearUsers(){
        this.userList.clear();
    }
    public void clearPoints(){
        this.pointList.clear();
    }
    public void clearRelations(){
        this.relationList.clear();
    }
    public void clear(){
        this.clearPoints();
        this.clearUsers();
        this.clearRelations();
    }

    public Usuario addUsuario(Usuario user){
        logger.info("Añadiendo usuario"+user);
        this.userList.add(user);
        logger.info("Usuario añadido");
        return user;
    }

    public Usuario addUsuario(String id, String nombre, String apellidos, String correo, String nacimiento){
        return this.addUsuario(new Usuario(id, nombre, apellidos, correo, nacimiento));
    }

    public Usuario addUsuario(String nombre, String apellidos, String correo, String nacimiento){
        return this.addUsuario(new Usuario(null, nombre, apellidos, correo, nacimiento));
    }

    public PuntoInteres addPuntoInteres(String element, int horizontal, int vertical){
        for (String s:ElementType){
            if (s.equals(element)){
                PuntoInteres p = new PuntoInteres(element, horizontal,vertical);
                logger.info("Añadiendo punto de interés ("+horizontal+","+vertical+") con elemento: "+element);
                pointList.add(p);
                return p;
            }
        }
        logger.warn("Elemento no existente, añada un elemento válido");
        return null;
    }

    public UserPoint addRelation(UserPoint up){
        logger.info("Añadiendo relación:"+up);
        this.relationList.add(up);
        logger.info("Relación añadida");
        return up;
    }

    public UserPoint addRelation(String idUser, int horizontal, int vertical){
        return this.addRelation(new UserPoint(getUsuario(idUser),getPunto(horizontal,vertical)));
    }

    public Usuario getUsuario(String id){
        logger.info("Get Usuario con id{"+id+"}");

        for (Usuario u:userList){
            if (u.getId().equals(id)){
                logger.info("Get Usuario con id{"+id+"}: "+u);
                return u;
            }
        }

        logger.warn("Usuario no encontrado");
        return null;
    }

    public Usuario getUsuario1(String id) throws UsuarioNotFoundException{
        Usuario u =getUsuario(id);
        if(u==null) throw new UsuarioNotFoundException();
        return u;
    }

    public Usuario getUsuario2(String apellidos){
        logger.info("Get Usuario con apellidos: "+apellidos);

        for (Usuario u:userList){
            if (u.getApellidos().equals(apellidos)){
                logger.info("Get Usuario con apellidos:"+apellidos+": "+u);
                return u;
            }
        }

        logger.warn("Usuario no encontrado");
        return null;
    }

    public Usuario getUsuario21(String apellidos) throws UsuarioNotFoundException {
        Usuario u = getUsuario2(apellidos);
        if(u==null) throw new UsuarioNotFoundException();
        return u;
    }

    public PuntoInteres getPunto(int horizontal, int vertical){
        logger.info("Get Punto de interés con coordenadas("+horizontal+","+vertical+")");
        for(PuntoInteres p:pointList){
            if (p.getHorizontal()==horizontal && p.getVertical()==vertical){
                logger.info("Punto de interés ("+horizontal+","+vertical+") contiene: "+p.getElement());
                return p;
            }
        }

        logger.warn("Punto de interés no encontrado");
        return null;
    }

    public PuntoInteres getPunto1(int horizontal, int vertical) throws PuntoInteresNotFoundException {
        PuntoInteres p = getPunto(horizontal,vertical);
        if(p==null) throw new PuntoInteresNotFoundException();
        return p;
    }

    public List<Usuario> findAllUsers(){
        return this.userList;
    }

    public List<PuntoInteres> findAllPoints(){
        return this.pointList;
    }

    //ENCONTRAR TODOS LOS PUNTOS QUE CONTIENEN UN ELEMENTO

    public List<PuntoInteres> findAllPointsElement(String element){
        List<PuntoInteres> lista = new ArrayList<>();
        for(PuntoInteres p : pointList){
            if(p.getElement().equals(element)){
                logger.info("Añadiendo punto: "+p);
                lista.add(p);
            }
        }
        if(!lista.isEmpty()) return lista;
        else{
            logger.warn("No hay nungún punto en el mapa con el elemento: "+element);
            return null;
        }
    }

    //ENCONTRAR RELACIONES

    //TODOS LOS USERS QUE HAYAN PASADO POR UN PUNTO

    public List<PuntoInteres> findAllPointsUser(String idUser){
        List<PuntoInteres> lista = new ArrayList<>();
        for(UserPoint up:relationList){
            if(Objects.equals(up.getUser().getId(), idUser)) {
                logger.info("Añadiendo punto: "+up.getPoint());
                lista.add(up.getPoint());
            }
        }
        if(!lista.isEmpty()) return lista;
        else{
            logger.warn("El usuario:"+idUser+" no ha pasado por ningún punto de interés");
            return null;
        }
    }

    public List<PuntoInteres> findAllPointsUser1(String idUser) throws RelationNotFoundException{
        List<PuntoInteres> points = findAllPointsUser(idUser);
        if(points==null) throw new RelationNotFoundException();
        return points;
    }

    //TODOS LOS PUNTOS POR LOS QUE HA PASADO UN USER

    public List<Usuario> findAllUsersPoint(int horizontal, int vertical){
        List<Usuario> lista = new ArrayList<>();
        for(UserPoint up:relationList){
            if(up.getPoint().getHorizontal()==horizontal && up.getPoint().getVertical()==vertical){
                logger.info("Añadiendo usuario: "+up.getUser());
                lista.add(up.getUser());
            }
        }
        if(!lista.isEmpty()) return lista;
        else{
            logger.warn("No ha pasado ningún usuario por el punto ("+horizontal+","+vertical+")");
            return null;
        }
    }

    public List<Usuario> findAllUsersPoint1(int horizontal, int vertical) throws RelationNotFoundException{
        List<Usuario> users = findAllUsersPoint(horizontal,vertical);
        if(users==null) throw new RelationNotFoundException();
        return users;
    }

    //ORDENAR USUARIOS EN ORDEN ALFABÉTICO

    public List<Usuario> organizeUsers(){
        List<String> listaApellidos = new ArrayList<>();
        for(Usuario u:userList){
            listaApellidos.add(u.getApellidos());
        }
        Collections.sort(listaApellidos);
        List<Usuario> lista = new ArrayList<>();
        for(String s:listaApellidos){
            lista.add(getUsuario2(s));
        }
        return lista;
    }

}
