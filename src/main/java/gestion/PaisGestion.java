package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
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

    public static String generaJson() {
        String tiraJson = "";
        String sentencia = "Select * from pais";
        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(sentencia);
            ResultSet datos = consulta.executeQuery();

            //Formato para convertir un Date a texto.
            DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            //Se crea un objeto Json para transformar la info del registro
            //y pasar a a texto luego...
            JsonObjectBuilder constructorJson = Json.createObjectBuilder();
            JsonObject objetoJson;
            JsonWriter salidaJson;
            StringWriter tira;

            while (datos.next()) {  //mientras se pueda avanzar...

                objetoJson = (JsonObject) constructorJson
                        
                        .add("idPais", datos.getString(2))
                        .add("nombre", datos.getString(3))
                        .add("continente", datos.getString(4))
                        .add("descripción", datos.getString(5));

                //Se pasa el objeto Json a un texto...
                tira = new StringWriter(); //Se inicializa la tira 
                salidaJson = Json.createWriter(tira);
                salidaJson.writeObject(objetoJson);

                tiraJson += tira.toString()+"\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaisGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
}
