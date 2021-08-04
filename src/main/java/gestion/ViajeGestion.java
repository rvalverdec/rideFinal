
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

        public static boolean insertar(Viaje viaje) {
            //Sentencia para insertar un Viaje
            String sentencia = "insert into Viajes "
                    + "(idViaje,ubicacion,nombreLugar,descripcion,requerimiento,oferta,telefono,correoElectronico)"
                    + "values (?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                consulta.setString(1, viaje.getIdViaje());
                consulta.setString(2, viaje.getUbicacion());
                consulta.setString(3, viaje.getNombreLugar());
                consulta.setString(4, viaje.getDescripcion());
                consulta.setString(5, viaje.getRequerimiento());
                consulta.setString(6, viaje.getOferta());
                consulta.setString(7, viaje.getTelefono());
                consulta.setString(8, viaje.getCorreoElectronico());
                return consulta.executeUpdate() > 0;  //retorna true si logra inserta o falso si no...
            } catch (SQLException ex) {
                Logger.getLogger(ViajeGestion.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }

        public static boolean modificar(Viaje viaje) {
            //Sentencia para modificar un Viaje
            String sentencia = "update Viajes set ubicacion=?,nombreLugar=?,descripcion=?,requerimiento=?,oferta=?,telefono=?,correoElectronico=? where idViaje=?";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                consulta.setString(1, viaje.getUbicacion());
                consulta.setString(2, viaje.getNombreLugar());
                consulta.setString(3, viaje.getDescripcion());
                consulta.setString(4, viaje.getRequerimiento());
                consulta.setString(5, viaje.getOferta());
                consulta.setString(6, viaje.getTelefono());
                consulta.setString(7, viaje.getCorreoElectronico());
                consulta.setString(8, viaje.getIdViaje());
                return consulta.executeUpdate() > 0;  //retorna true si logra modificar o falso si no...
            } catch (SQLException ex) {
                Logger.getLogger(ViajeGestion.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }

        public static boolean eliminar(Viaje viaje) {
            //Sentencia para eliminar un Viaje
            String sentencia = "delete from Viajes where idViaje=?";
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
            String sentencia = "Select * from Viajes where idViaje=?";
            //String sentencia = "Select * from Viaje where idViaje="+ idViaje;
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                consulta.setString(1, idViaje);
                ResultSet datos = consulta.executeQuery();
                if (datos.next()) {  //mientras se pueda avanzar...
                    viaje = new Viaje(
                            datos.getString(2),
                            datos.getString(3),
                            datos.getString(4),
                            datos.getString(5),
                            datos.getString(6),
                            datos.getString(7),
                            datos.getString(8));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViajeGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return viaje;
        }

        public static ArrayList<Viaje> getViajes() {
            ArrayList<Viaje> lista = new ArrayList<>();
            String sentencia = "Select * from Viajes";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {  //mientras se pueda avanzar...
                    lista.add(new Viaje(
                            datos.getString(2),
                            datos.getString(3),
                            datos.getString(4),
                            datos.getString(5),
                            datos.getString(6),
                            datos.getString(7),
                            datos.getString(8)));
                }

            } catch (SQLException ex) {
                Logger.getLogger(ViajeGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return lista;
        }

        public static List<Viaje> getViaje() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
