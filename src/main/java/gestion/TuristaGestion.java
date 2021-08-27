package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Turista;

public class TuristaGestion {
    
        public static Turista valida(String idTurista, String pwTurista) {
        //Lo que obtengo de la base de datos en el mismo orden viene para datos
        String sentencia = "select * from turista where idTurista=? and pwTurista=?";

        Turista turi = null;
        try {

            PreparedStatement consulta = Conexion.getConexion().prepareStatement(sentencia);

            consulta.setString(1, idTurista);
            consulta.setString(2, pwTurista);

            ResultSet datos = consulta.executeQuery();
            //datos trae la informacion de la misma manera que viene del QUERY de la base de datos (Sentencia)

            if (datos.next()) {  //Si se autenticó...
                turi = new Turista(datos.getString(2), //id Usuario
                        datos.getString(3), //Password
                        datos.getString(4), //Nombre de usuario
                        datos.getString(5), //correo
                        datos.getBoolean(6), //activo
                        datos.getString(7));
                        

               
            }
        } catch (SQLException ex) {
            Logger.getLogger(TuristaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turi;
    }

    public static boolean insertar(Turista turista) {
        String sentencia = "insert into turista "
                + "(idturista,pwTurista, nombreUsuario,correoTurista,activo,idRolTurista)"
                + "values (?,?,?,?,?,?)";
        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(sentencia);
            consulta.setString(1, turista.getIdTurista());
            consulta.setString(2, turista.getPwTurista());
            consulta.setString(3, turista.getNombreUsuario());
            consulta.setString(4, turista.getCorreoTurista());
            consulta.setBoolean(5, turista.isActivo());
            consulta.setString(6, turista.getIdRol());

            return consulta.executeUpdate() > 0;  //retorna true si logra inserta o falso si no...
        } catch (SQLException ex) {
            Logger.getLogger(TuristaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean modificar(Turista turista) {
        //Sentencia para modificar un turista
        String sentencia = "update turista set pwTurista=?, nombreUsuario=?,correoTurista=?,activo=?,idRolTurista=?  where idTurista=?";
        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(sentencia);
            consulta.setString(1, turista.getPwTurista());
            consulta.setString(2, turista.getNombreUsuario());
            consulta.setString(3, turista.getCorreoTurista());
            consulta.setBoolean(4, turista.isActivo());
            consulta.setString(5,   turista.getIdRol());
            consulta.setString(6, turista.getIdTurista());

            return consulta.executeUpdate() > 0;  //retorna true si logra modificar o falso si no...
        } catch (SQLException ex) {
            Logger.getLogger(TuristaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean eliminar(Turista turista) {
        //Sentencia para eliminar un turista
        String sentencia = "delete from turista where idTurista=?";
        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(sentencia);
            consulta.setString(1, turista.getIdTurista());
            return consulta.executeUpdate() > 0;  //retorna true si logra eliminar o falso si no...
        } catch (SQLException ex) {
            Logger.getLogger(TuristaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Turista getTurista(String idTurista) {
        Turista turista = null;
        String sentencia = "Select * from turista where idTurista=?";
        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(sentencia);
            consulta.setString(1, idTurista);
            ResultSet datos = consulta.executeQuery();
            if (datos.next()) {  //mientras se pueda avanzar...
                turista = new Turista( 
                        datos.getString(2), //idTurista
                        datos.getString(3), //pass
                        datos.getString(4), //nombre
                        datos.getString(5), //correo
                        datos.getBoolean(6), //activo
                        datos.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TuristaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turista;
    }

    public static ArrayList<Turista> getTuristas() {
        ArrayList<Turista> lista = new ArrayList<>();
        String sentencia = "Select * from turista";
        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(sentencia);
            ResultSet datos = consulta.executeQuery();
            while (datos.next()) {  
                lista.add(new Turista(
                        datos.getString(2), //idTurista
                        datos.getString(3), //pass
                        datos.getString(4), //nombre
                        datos.getString(5), //correo
                        datos.getBoolean(6), //activo
                        datos.getString(7))); //rol
            }
        } catch (SQLException ex) {
            Logger.getLogger(TuristaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
