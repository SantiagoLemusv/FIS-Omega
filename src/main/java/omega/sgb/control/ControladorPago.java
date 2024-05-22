package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.Pago;
import omega.sgb.dominio.Prestamo;
import omega.sgb.dominio.Tarjeta;
import omega.sgb.integracion.ProcesarFecha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ControladorPago {
    private Connection connection;
    private final ProcesarFecha procesarFecha;
    Tarjeta tarjetaSeleccionada = new Tarjeta();
    Prestamo prestamoSeleccionado = new Prestamo();

    public ControladorPago(Connection conexionGeneral, ProcesarFecha procesarFecha) throws SQLException {
        this.connection = conexionGeneral;
        this.procesarFecha = procesarFecha;
    }

    public String nombreLibroMulta(String datoMulta) throws SQLException {
        if(datoMulta == null){
            return null;
        }
        int indiceComa = datoMulta.indexOf(",");

        // Si no se encontró una coma, devolver null
        if (indiceComa == -1) {
            return null;
        }
        String nombreLibro = datoMulta.substring(0, indiceComa).trim();

        // Obtener la frase antes de la coma
        return nombreLibro;
    }

    public String numeroTarjetaMulta(String datoMetodoPago) throws SQLException {
        if(datoMetodoPago == null) {
            return null;
        }
        int indiceDosPuntos = datoMetodoPago.indexOf(":");

        // Si no se encontraron los dos puntos, devolver null
        if (indiceDosPuntos == -1) {
            return null;
        }

        // Buscar el índice del primer dígito después de los dos puntos
        int indiceNumero = -1;
        for (int i = indiceDosPuntos + 1; i < datoMetodoPago.length(); i++) {
            if (Character.isDigit(datoMetodoPago.charAt(i))) {
                indiceNumero = i;
                break;
            }
        }

        // Si no se encontró ningún dígito, devolver null
        if (indiceNumero == -1) {
            return null;
        }

        // Obtener los números después de los dos puntos
        String numeroTarjeta = datoMetodoPago.substring(indiceNumero).replaceAll("\\D", "");

        return numeroTarjeta;
    }

    public boolean validarDatos(String numeroTarjeta, String nombreLibro){
        boolean tarjetaEncontrada = false;
        boolean prestamoEncontrado = false;

        if(SingletonControladores.getUsuarioActual().getTarjetas().size()==0){
            return false;
        }
        if(SingletonControladores.getUsuarioActual().getPrestamos().size()==0){
            return false;
        }
        if(SingletonControladores.getUsuarioActual().getMultas().size()==0){
            return false;
        }
        if(numeroTarjeta == null){
            return false;
        }
        if(numeroTarjeta.length() != 16){
            return false;
        }
        if(nombreLibro == null){
            return false;
        }
        Long tarjetaNumero = Long.valueOf(numeroTarjeta);
        for (Tarjeta tarjetaAux : SingletonControladores.getUsuarioActual().getTarjetas()) {
            if(tarjetaAux.getNumero().equals(tarjetaNumero)) {
                tarjetaEncontrada = true;
            }
        }
        if(tarjetaEncontrada == false){
            return false;
        }
        for (Prestamo prestamoAux : SingletonControladores.getUsuarioActual().getPrestamos()) {
            if(prestamoAux.getLibro().getLibroVirtual().getTitulo().equals(nombreLibro) && prestamoAux.getMulta() != null && prestamoAux.getMulta().getPago() == null){
                prestamoEncontrado = true;
            }
        }
        if(prestamoEncontrado == false){
            return false;
        }

        return true;
    }

    public void setTarjetaSeleccionada(String numeroTarjeta) throws SQLException {
        Tarjeta tarjeta = new Tarjeta();
        Long tarjetaNumero = Long.valueOf(numeroTarjeta);
        for (Tarjeta tarjetaAux : SingletonControladores.getUsuarioActual().getTarjetas()) {
            if(tarjetaAux.getNumero().equals(tarjetaNumero)) {
                tarjeta = tarjetaAux;
            }
        }
        this.tarjetaSeleccionada = tarjeta;
    }

    public void setPrestamoSeleccionado(String nombreLibro) throws SQLException {
        Prestamo prestamo = new Prestamo();
        for (Prestamo prestamoAux : SingletonControladores.getUsuarioActual().getPrestamos()) {
            if(prestamoAux.getLibro().getLibroVirtual().getTitulo().equals(nombreLibro) && prestamoAux.getMulta() != null)
                prestamo = prestamoAux;
        }
        this.prestamoSeleccionado = prestamo;
    }

    public Integer calcularPrecioMulta(){
        Integer precioMulta = 0;
        precioMulta = prestamoSeleccionado.getMulta().getMontoPagar();
        return precioMulta;
    }

    public String metodoPago(){
        String metodoPago = new String();
        if(tarjetaSeleccionada.getTipoTarjetaId().equals(1)){
            metodoPago = "Tarjeta debito: " + tarjetaSeleccionada.getNumero();
            return metodoPago;
        }
        if(tarjetaSeleccionada.getTipoTarjetaId().equals(2)){
            metodoPago = "Tarjeta credito: " + tarjetaSeleccionada.getNumero();
            return metodoPago;
        }
        return null;
    }

    public void pagarMulta() throws SQLException {
        Integer precioMulta = prestamoSeleccionado.getMulta().getMontoPagar();
        Date fechaEmision = procesarFecha.getFechaActual();
        Pago pago = new Pago();

        System.out.println("Insertar el pago de la multa en la tabla pago");
        //Insertar el pago de la multa en la tabla pago
        String sqlInsertarPago = "INSERT INTO PAGO (MONTOTOTAL, FECHAEMISION, TARJETAID) VALUES (?, ?, ?)";
        connection.setAutoCommit(false);
        try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsertarPago)) {
            insertStatement.setInt(1, precioMulta);
            insertStatement.setDate(2, procesarFecha.fechaJavaToFechaSql(fechaEmision));
            insertStatement.setInt(3, tarjetaSeleccionada.getId());
            int filasAfectadas = insertStatement.executeUpdate();
            if (filasAfectadas < 1) {
                return;
            }
            pago.setMontoTotal(precioMulta);
            pago.setFecha(fechaEmision);
            pago.setTarjeta(tarjetaSeleccionada);
            connection.commit();
        }catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return;
        }

        System.out.println("Obtener el id del pago previamente creado para actualizar el objeto de dicho pago");
        //Obtener el id del pago previamente creado para actualizar el objeto de dicho pago
        String sqlObtenerIdPago = "SELECT P.ID "+
                "FROM PAGO P "+
                "WHERE P.MONTOTOTAL = ? AND P.FECHAEMISION = ? AND P.TARJETAID = ?";
        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlObtenerIdPago)) {
            preparedStatement.setInt(1, precioMulta);
            preparedStatement.setDate(2, procesarFecha.fechaJavaToFechaSql(fechaEmision));
            preparedStatement.setInt(3, tarjetaSeleccionada.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    pago.setId(resultSet.getInt("ID"));
                }else{
                    return;
                }
            }catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                return;
            }
            connection.commit();
        }catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return;
        }

        System.out.println("Actulizar la multa, asociando el pago previamente creado");
        //Actulizar la multa, asociando el pago previamente creado
        prestamoSeleccionado.getMulta().setPago(pago);
        //pago.getMultas().add(prestamoSeleccionado.getMulta());

        System.out.println("Actualizar la multa de la tabla multa, asociando el pago previamente creado");
        //Actualizar la multa de la tabla multa, asociando el pago previamente creado
        String sqlActualizarMulta = "UPDATE MULTA SET PAGOID = ? WHERE ID = ?";
        connection.setAutoCommit(false);
        try (PreparedStatement insertStatement = connection.prepareStatement(sqlActualizarMulta)) {
            insertStatement.setInt(1, prestamoSeleccionado.getMulta().getPago().getId());
            insertStatement.setInt(2, prestamoSeleccionado.getMulta().getId());
            int filasAfectadas = insertStatement.executeUpdate();
            if (filasAfectadas < 1) {
                return;
            }
            connection.commit();
        }catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return;
        }
        return;
    }
}
