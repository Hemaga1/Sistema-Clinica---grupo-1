package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.excepciones.ErrorPersistenciaExcepcion;
import util.Config;

/**
 * DAO encargado de gestionar la conexión, creación de tablas y operaciones CRUD
 * sobre la base de datos referente a los AsociadoDTO.
 *
 * Implementa un patrón Singleton.
 */
public class dataBaseDAO {
    private static String DB_URL;
    private static String DB_NAME;
    private static String USER;
    private static String PASS;
    private Connection connection;
    private Statement sentencia;
    private static dataBaseDAO instancia;

    /**
     * Constructor privado del patrón Singleton.
     * Inicializa los parámetros de conexión desde archivo de configuración.
     */
    private dataBaseDAO() {
        DB_URL = Config.get("db.url");
        DB_NAME = Config.get("db.name");
        USER = Config.get("db.user");
        PASS = Config.get("db.password");
    }

    /**
     * Devuelve la instancia única del DAO.
     *
     * @return instancia única de dataBaseDAO
     */
    public static dataBaseDAO getInstancia() {
        if  (instancia == null) {
            instancia = new dataBaseDAO();
        }
        return instancia;
    }

    /**
     * Abre la conexión con la base de datos,
     * carga el driver y selecciona la base configurada.
     *
     * @throws ErrorPersistenciaExcepcion si ocurre un error de conexión o el driver no se encuentra
     */
    public void abrirConexion() throws ErrorPersistenciaExcepcion {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ErrorPersistenciaExcepcion("No se pudo cargar el driver de MySQL");
        }
        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            sentencia = connection.createStatement();
            sentencia.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            sentencia.execute("USE " + DB_NAME);
        }catch (SQLException e){
            throw new ErrorPersistenciaExcepcion("No se pudo conectar a la base de datos");
        }
    }

    /**
     * Cierra la conexión limpiamente.
     *
     * @throws ErrorPersistenciaExcepcion si ocurre un error al cerrar
     */
    public void cerrarConexion() throws ErrorPersistenciaExcepcion {
        try {
            if (sentencia != null) {
                sentencia.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ErrorPersistenciaExcepcion("Error al cerrar la conexión");
        }
    }

    /**
     * Crea la tabla de asociados (eliminando una existente si ya estaba creada).
     */
    public void crearTablaAsociados() throws ErrorPersistenciaExcepcion {
        if (sentencia == null) {
            throw new ErrorPersistenciaExcepcion("La conexión no está abierta");
        }

        try{
            sentencia.execute("DROP TABLE IF EXISTS asociados");
            sentencia.execute("CREATE TABLE IF NOT EXISTS asociados" +
                    " (dni VARCHAR(10) NOT NULL PRIMARY KEY, " +
                    " nombre VARCHAR(50) NOT NULL, " +
                    " apellido VARCHAR(50) NOT NULL, " +
                    " calle VARCHAR(100), " +
                    " numero INT, " +
                    " ciudad VARCHAR(50), " +
                    " telefono VARCHAR(15))");
        }catch (SQLException e){
            throw new ErrorPersistenciaExcepcion("No se pudo crear la tabla de asociados");
        }

    }

    /**
     * Elimina un asociado por DNI.
     * 
     * @param DNIAsociado !=null
     * @throws ErrorPersistenciaExcepcion si ocurre un error al eliminar el asociado
     */
    public void eliminarAsociado(String DNIAsociado) throws ErrorPersistenciaExcepcion {
        asegurarConexionAbierta();

        String sql = "DELETE FROM asociados WHERE dni = '" + DNIAsociado + "'";
        try{
            sentencia.executeUpdate(sql);
        }catch (SQLException e){
            throw new ErrorPersistenciaExcepcion("Error al eliminar el asociado de la base de datos");
        }
    }

    /**
     * Verifica si la conexión está abierta y el statement es válido.
     * Si no lo están, los crea/reabre.
     * 
     * @throws ErrorPersistenciaExcepcion si no se puede establecer la conexión
     */
    private void asegurarConexionAbierta() throws ErrorPersistenciaExcepcion {
        try {
            // Verificar si la conexión está cerrada o es null
            if (connection == null || connection.isClosed()) {
                abrirConexion();
                return;
            }
            
            // Verificar si el statement está cerrado o es null
            if (sentencia == null || sentencia.isClosed()) {
                sentencia = connection.createStatement();
            }
        } catch (SQLException e) {
             throw new ErrorPersistenciaExcepcion("Error al recuperar la conexión");
        }
    }

    /**
     * Inserta un nuevo asociado en la tabla.
     * 
     * @param asociado el DTO del asociado a insertar
     * @throws ErrorPersistenciaExcepcion si ocurre un error al insertar el asociado
     */
    public void agregarAsociado(AsociadoDTO asociado) throws ErrorPersistenciaExcepcion {
        asegurarConexionAbierta();

        String sql = "INSERT INTO asociados (dni, nombre, apellido, calle, numero, ciudad, telefono) VALUES ('"
                + asociado.getDni() + "', '"
                + asociado.getNombre() + "', '"
                + asociado.getApellido() + "', '"
                + asociado.getCalle() + "', "
                + asociado.getNumero() + ", '"
                + asociado.getCiudad() + "', '"
                + asociado.getTelefono() + "')";
        try{
            sentencia.executeUpdate(sql);
        }catch (SQLException e){
            throw new ErrorPersistenciaExcepcion("Error al guardar el asociado en la base de datos");
        }
    }

    /**
     * Devuelve todos los asociados registrados.
     *
     * @return lista de DTOs (lista vacía si no hay asociados)
     * @throws ErrorPersistenciaExcepcion si ocurre un error al obtener los asociados
     */
    public List<AsociadoDTO> traerAsociados() throws ErrorPersistenciaExcepcion {
        asegurarConexionAbierta();
        
        ArrayList<AsociadoDTO> asociados = new ArrayList<AsociadoDTO>();
        String sql = "SELECT * FROM asociados";
        
        try (ResultSet rs = sentencia.executeQuery(sql)) {
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
            throw new ErrorPersistenciaExcepcion("Error al obtener los asociados");
        }           
        return asociados;
    }

    public boolean estaConectado(){
        return connection != null;
    }
}
