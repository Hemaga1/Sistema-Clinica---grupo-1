package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Config;

public class dataBaseDAO {
    private static String DB_URL;
    private static String USER;
    private static String PASS;
    private Connection connection;
    private Statement sentencia;

    public dataBaseDAO() {
        DB_URL = Config.get("db.url");
        USER = Config.get("db.user");
        PASS = Config.get("db.password");
    }

    public void abrirConexion() throws SQLException {

        try {
            Class.forName("sun.jdbc.obdc.JdbcObdcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(DB_URL, USER,PASS);
        sentencia = connection.createStatement();
    }

    public void cerrarConexion() throws SQLException {
        try {
            sentencia.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void crearTablaAsociados() throws SQLException {
        try {
            sentencia.execute("DROP TABLE asociados");
            sentencia.execute("CREATE TABLE asociados" +
                    " (nombre VARCHAR(50) NOT NULL, " +
                    " apellido VARCHAR(50) NOT NULL, " +
                    " dni VARCHAR(10) NOT NULL PRIMARY KEY, " +
                    " telefono VARCHAR(15), " +
                    " email VARCHAR(50))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarAsociado(int DNIAsociado) {
        String sql = "DELETE FROM asociados WHERE dni = '" + DNIAsociado + "'";
        try {
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

    public void agregarAsociado(AsociadoDTO asociado) {
        String sql = "INSERT INTO asociados (nombre, apellido, dni, telefono, email) VALUES ('"
                + asociado.getNombre() + "', '"
                + asociado.getApellido() + "', '"
                + asociado.getDni() + "', '"
                + asociado.getTelefono() + "', '"
                + asociado.getEmail() + "')";
        try {
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarAsociado(AsociadoDTO asociado) {
        String sql = "UPDATE asociados SET "
                + "nombre = '" + asociado.getNombre() + "', "
                + "apellido = '" + asociado.getApellido() + "', "
                + "telefono = '" + asociado.getTelefono() + "', "
                + "email = '" + asociado.getEmail() + "' "
                + "WHERE dni = '" + asociado.getDni() + "'";
        try {
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AsociadoDTO obtenerAsociadoPorDNI(String dni) {
        String sql = "SELECT * FROM asociados WHERE dni = '" + dni + "'";
        ResultSet rs = null;
        try {
            rs = sentencia.executeQuery(sql);
            if (rs.next()) {
                return new AsociadoDTO(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Si no se encuentra el asociado
    }

    public List<AsociadoDTO> traerAsociados() {
        ArrayList<AsociadoDTO> asociados = null;
        String sql = "SELECT * FROM asociados";
        ResultSet rs = null;
        try {
            asociados = new ArrayList<AsociadoDTO>();
            rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                AsociadoDTO asociado = null;

                asociado = new AsociadoDTO(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );

                asociados.add(asociado);
            }
        } catch (SQLException e) {

        }
        return asociados;
    }
}
