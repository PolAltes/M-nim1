package edu.upc.dsa;

import edu.upc.dsa.exceptions.PuntoInteresNotFoundException;
import edu.upc.dsa.exceptions.RelationNotFoundException;
import edu.upc.dsa.exceptions.UsuarioNotFoundException;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.UserPoint;
import edu.upc.dsa.models.Usuario;

import javax.management.relation.Relation;
import java.util.List;

public interface Manager {

    public Usuario addUsuario(Usuario user);
    public Usuario addUsuario(String id, String nombre, String apellidos, String correo, String nacimiento);
    public Usuario addUsuario(String nombre, String apellidos, String correo, String nacimiento);
    public Usuario getUsuario(String id);
    public Usuario getUsuario2(String apellidos);
    public Usuario getUsuario1(String id) throws UsuarioNotFoundException;
    public Usuario getUsuario21(String apellidos) throws UsuarioNotFoundException;
    public PuntoInteres getPunto(int horizontal, int vertical);
    public PuntoInteres getPunto1(int horizontal, int vertical) throws PuntoInteresNotFoundException;

    public PuntoInteres addPuntoInteres(String element, int horizontal, int vertical);
    public List<Usuario> findAllUsers();
    public List<Usuario> organizeUsers();
    public List<PuntoInteres> findAllPoints();
    public List<PuntoInteres> findAllPointsElement(String element);

    public List<PuntoInteres> findAllPointsUser(String idUser);
    public List<Usuario> findAllUsersPoint(int horizontal, int vertical);
    public List<PuntoInteres> findAllPointsUser1(String idUser) throws RelationNotFoundException;
    public List<Usuario> findAllUsersPoint1(int horizontal, int vertical) throws RelationNotFoundException;

    public UserPoint addRelation(UserPoint up);
    public UserPoint addRelation(String idUser, int horizontal, int vertical);

    public void clearUsers();
    public void clearPoints();
    public void clearRelations();
    public void clear();
    public int usersSize();
    public int pointsSize();
    public int relationsSize();
}
