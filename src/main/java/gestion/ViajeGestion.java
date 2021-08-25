
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Viaje;


public class ViajeGestion {

    
    //Insertar un nuevo viaje 
        public static boolean insertar(Viaje viaje) {
            //Sentencia para insertar un Viaje
            String sentencia = "insert into Viaje"
                    + "(idViaje,nombre,descripcion,telefono,correoElectronico,activo,costo)"
                    + "values (?,?,?,?,?,?,?)";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                consulta.setString(1, viaje.getIdViaje());
                consulta.setString(2, viaje.getNombre());
                consulta.setString(3, viaje.getDescripcion());
                consulta.setString(4, viaje.getTelefono());
                consulta.setString(5, viaje.getCorreoElectronico());
                consulta.setBoolean(6, viaje.isActivo());
                consulta.setDouble(7, viaje.getCosto());
                return consulta.executeUpdate() > 0;  //retorna true si logra inserta o falso si no...
            } catch (SQLException ex) {
                Logger.getLogger(ViajeGestion.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }

     public static boolean modificar(Viaje viaje) {
            //Sentencia para modificar un Viaje
            String sentencia = "update viaje set nombre=?,descripcion=?,telefono=?,correoElectronico=?, activo=?, costo=? where idViaje=?";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
           
                consulta.setString(1, viaje.getNombre());
                consulta.setString(2, viaje.getDescripcion());
                consulta.setString(3, viaje.getTelefono());
                consulta.setString(4, viaje.getCorreoElectronico());
                consulta.setBoolean(5, viaje.isActivo());
                consulta.setDouble(6, viaje.getCosto());
                consulta.setString(7, viaje.getIdViaje());
                return consulta.executeUpdate() > 0;  //retorna true si logra modificar o falso si no...
            } catch (SQLException ex) {
                Logger.getLogger(ViajeGestion.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }

        public static boolean eliminar(Viaje viaje) {
            //Sentencia para eliminar un Viaje
            String sentencia = "delete from viaje where idViaje=?";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                consulta.setString(1, viaje.getIdViaje());
                return consulta.executeUpdate() > 0;  //retorna true si logra eliminar o falso si no...
            } catch (SQLException ex) {
                Logger.getLogger(ViajeGestion.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }

        public static Viaje getViaje(String idViaje) {
            Viaje viaje = null;
            String sentencia = "Select * from viaje where idViaje=?";
            //String sentencia = "Select * from Viaje where idViaje="+ idViaje;
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                consulta.setString(1, idViaje);
                ResultSet datos = consulta.executeQuery();
                if (datos.next()) {  //mientras se pueda avanzar...
                    viaje = new Viaje(
                            datos.getString(2), //idViaje
                            datos.getString(3), //Nombre
                            datos.getString(4), //Descripcion
                            datos.getString(5), // Telefono
                            datos.getString(6), //correo electronico
                            datos.getBoolean(7), //Activo (Boolean)
                            datos.getDouble(8)); //Costo
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViajeGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return viaje;
        }

        public static ArrayList<Viaje> getViajes() {
            ArrayList<Viaje> lista = new ArrayList<>();
            String sentencia = "Select * from Viaje";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {  //mientras se pueda avanzar...
                    lista.add(new Viaje(
                            datos.getString(2), //idViaje
                            datos.getString(3), //nombre
                            datos.getString(4), //Descripcion
                            datos.getString(5), //Telefono
                            datos.getString(6), // Correo
                            datos.getBoolean(7), //Activo
                            datos.getDouble(8))); //Costo
                }

            } catch (SQLException ex) {
                Logger.getLogger(ViajeGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return lista;
        }

     
    }
