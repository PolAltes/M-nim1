package edu.upc.dsa;


import edu.upc.dsa.exceptions.UsuarioNotFoundException;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.UserPoint;
import edu.upc.dsa.models.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ManagerTest {
    Manager m;

    @Before
    public void setUp() {
        this.m = ManagerImpl.getInstance();
        this.m.addUsuario("U1","Miguel","Alvarez","MiguelAlvarez@gmail.com","11/05/2003");
        this.m.addUsuario("U2","Aitor","Gimenez","AitorGimenez@gmail.com","11/05/2003");
        this.m.addUsuario("U3","Eric","Lopez","EricLopez@gmail.com","11/05/2003");
        this.m.addUsuario("U4","Carlos","Ruiz","CarlosRuiz@gmail.com","11/05/2003");
        this.m.addUsuario("U5","Angel","Guimera","AngelGuimera@gmail.com","11/05/2003");
        this.m.addPuntoInteres("WALL",1,5);
        this.m.addPuntoInteres("POTION",4,5);
        this.m.addPuntoInteres("COIN",3,1);
        this.m.addPuntoInteres("GRASS",6,3);
        this.m.addPuntoInteres("SWORD",5,6);
        this.m.addPuntoInteres("DOOR",4,2);
        this.m.addRelation("U1",3,1);
        this.m.addRelation("U2",3,1);
        this.m.addRelation("U2",6,3);
        this.m.addRelation("U4",3,1);
        this.m.addRelation("U5",4,2);

    }

    @After
    public void tearDown() {
        this.m.clear();
    }

    @Test
    public void addUser() {
        Assert.assertEquals(5, m.usersSize());

        this.m.addUsuario("U6","Marc","Martin","MarcMartin@gmail.com","25/12/0000");

        Assert.assertEquals(6, m.usersSize());

    }

    @Test
    public void getUsuarioTest() throws Exception {
        Assert.assertEquals(5,m.usersSize());

        Usuario u = this.m.getUsuario("U4");
        Assert.assertEquals("Carlos Ruiz",u.getNombre() + " "+ u.getApellidos());

        u = this.m.getUsuario1("U4");
        Assert.assertEquals("Carlos Ruiz",u.getNombre() + " "+ u.getApellidos());

        Assert.assertThrows(UsuarioNotFoundException.class,()-> this.m.getUsuario1("XXXXXX"));

    }

    @Test
    public void getUsuariosTest() {
        Assert.assertEquals(5,m.usersSize());
        List<Usuario> users = m.findAllUsers();

        Usuario u = users.get(0);
        Assert.assertEquals("MiguelAlvarez@gmail.com",u.getCorreo());

        u = users.get(2);
        Assert.assertEquals("EricLopez@gmail.com",u.getCorreo());

        Assert.assertEquals(5,m.usersSize());
    }

    @Test
    public void getPoint() {
        Assert.assertEquals(6,m.pointsSize());

        PuntoInteres p = this.m.getPunto(4,5);
        Assert.assertEquals("POTION",p.getElement());
    }


    @Test
    public void getUserPoints() {
        Assert.assertEquals(5, m.relationsSize());
        List<PuntoInteres> lista = this.m.findAllPointsUser("U2");
        Assert.assertEquals(2,lista.size());

        Assert.assertEquals(5, m.relationsSize());
        lista = this.m.findAllPointsUser("U4");
        Assert.assertEquals(1,lista.size());

    }
}
