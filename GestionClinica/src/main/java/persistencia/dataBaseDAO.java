package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Config;

public class dataBaseDAO {
    private static String DB_URL;
    private static String DB_NAME;
    private static String USER;
    private static String PASS;
    private Connection connection;
    private Statement sentencia;
    private static dataBaseDAO instancia;

    private dataBaseDAO() {
        DB_URL = Config.get("db.url");
        DB_NAME = Config.get("db.name");
        USER = Config.get("db.user");
        PASS = Config.get("db.password");
    }

    public static dataBaseDAO getInstancia() {
        if  (instancia == null) {
            instancia = new dataBaseDAO();
        }
        return instancia;
    }

    public void abrirConexion() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(DB_URL, USER,PASS);
        sentencia = connection.createStatement();
        sentencia.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
        sentencia.execute("USE " + DB_NAME);
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
            sentencia.execute("DROP TABLE IF EXISTS asociados");
            sentencia.execute("CREATE TABLE IF NOT EXISTS asociados" +
                    " (dni VARCHAR(10) NOT NULL PRIMARY KEY, " +
                    " nombre VARCHAR(50) NOT NULL, " +
                    " apellido VARCHAR(50) NOT NULL, " +
                    " calle VARCHAR(100), " +
                    " numero INT, " +
                    " ciudad VARCHAR(50), " +
                    " telefono VARCHAR(15))");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void eliminarAsociado(String DNIAsociado) {
        String sql = "DELETE FROM asociados WHERE dni = '" + DNIAsociado + "'";
        try {
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarAsociado(AsociadoDTO asociado) {
        String sql = "INSERT INTO asociados (dni, nombre, apellido, calle, numero, ciudad, telefono) VALUES ('"
                + asociado.getDni() + "', '"
                + asociado.getNombre() + "', '"
                + asociado.getApellido() + "', '"
                + asociado.getCalle() + "', "
                + asociado.getNumero() + ", '"
                + asociado.getCiudad() + "', '"
                + asociado.getTelefono() + "')";
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
                + "calle = '" + asociado.getCalle() + "', "
                + "numero = " + asociado.getNumero() + ", "
                + "ciudad = '" + asociado.getCiudad() + "', "
                + "telefono = '" + asociado.getTelefono() + "' "
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
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("ciudad"),
                        rs.getString("telefono")
                        
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
                AsociadoDTO asociado = new AsociadoDTO(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("ciudad"),
                        rs.getString("telefono")
                );

                asociados.add(asociado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return asociados;
    }
}
