
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Pais;


public class PaisGestion {
    
   
    //Modulo para gestionar CRUD de Pais
        public static boolean insertar(Pais pais) {
            //Sentencia para insertar un pais
            String sentencia = "insert into pais"
                    + "(idPais,nombre,continente,descripcion)"
                    + "values (?,?,?,?)";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                consulta.setString(1, pais.getIdPais());
                consulta.setString(2, pais.getNombre());
                consulta.setString(3, pais.getContinente());
                consulta.setString(4, pais.getDescripcion());
                
                return consulta.executeUpdate() > 0;  
            } catch (SQLException ex) {
                Logger.getLogger(PaisGestion.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }

     public static boolean modificar(Pais pais) {
            //Sentencia para modificar un pais
            String sentencia = "update pais set nombre=?,continente=?,descripcion=? where idPais=?";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
           
                consulta.setString(1, pais.getNombre());
                consulta.setString(2, pais.getContinente());
                consulta.setString(3, pais.getDescripcion());
                consulta.setString(4, pais.getIdPais());
                
                return consulta.executeUpdate() > 0;  
            } catch (SQLException ex) {
                Logger.getLogger(PaisGestion.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }

        public static boolean eliminar(Pais pais) {
            String sentencia = "delete from pais where idPais=?";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                consulta.setString(1, pais.getIdPais());
                return consulta.executeUpdate() > 0;  //retorna true si logra eliminar o falso si no...
            } catch (SQLException ex) {
                Logger.getLogger(PaisGestion.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }

        public static Pais getPais(String idPais) {
            Pais pais = null;
            String sentencia = "Select * from pais where idPais=?";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                consulta.setString(1, idPais);
                ResultSet datos = consulta.executeQuery();
                if (datos.next()) {  
                    pais = new Pais(
                            datos.getString(2), //idPais
                            datos.getString(3), //Nombre
                            datos.getString(4), //Continente
                            datos.getString(5)); // Descripcion
                            
                }
            } catch (SQLException ex) {
                Logger.getLogger(PaisGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return pais;
        }

        public static ArrayList<Pais> getPaises() {
            ArrayList<Pais> lista = new ArrayList<>();
            String sentencia = "Select * from pais";
            try {
                PreparedStatement consulta = Conexion.getConexion()
                        .prepareStatement(sentencia);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {  //mientras se pueda avanzar...
                    lista.add(new Pais(
                            datos.getString(2), //idPais
                            datos.getString(3), //nombre
                            datos.getString(4), //Continente
                            datos.getString(5) //Descripcion
                            )); 
                }

            } catch (SQLException ex) {
                Logger.getLogger(PaisGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return lista;
        }

     
}
