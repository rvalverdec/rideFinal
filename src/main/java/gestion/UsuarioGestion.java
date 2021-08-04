package gestion;
/*
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Usuario;

public class UsuarioGestion {
    
    public static Usuario valida(String idUsuario, String clave) {
        String sentencia="select nombreUsuario,idRol from usuario where idUsuario=? and pwusuario=md5(?)";
        Usuario usuario=null;        
        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(sentencia);
            consulta.setString(1, idUsuario);
            consulta.setString(2, clave);
            ResultSet datos = consulta.executeQuery();
            if (datos.next()) {  //Si se autenticó...
                usuario = new Usuario(
                        idUsuario,
                        datos.getString(1),  //
                        datos.getString(2)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }    
}*/
