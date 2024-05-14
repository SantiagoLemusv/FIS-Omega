package omega.sgb.control;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.dominio.Tarjeta;
import omega.sgb.integracion.ConversorImagen;
import omega.sgb.integracion.ProcesarFecha;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControladorAgregarMetodoPago {


    private ProcesarFecha procesarFecha;
    private Connection connection;
    List<Tarjeta> listaTiposTarjeta = new ArrayList<>();

    Tarjeta tarjeta;

    public ControladorAgregarMetodoPago(Connection conexionGeneral, ProcesarFecha procesarFecha) {
        this.connection = conexionGeneral;
        this.procesarFecha = procesarFecha;
    }

    public boolean agregarMetodoPago(String numeroS, String fechavencimiento, String entidadbancaria, String tipotarjetaS, String titular, String CVVS) throws SQLException, ParseException {
        System.out.println(numeroS);
        System.out.println(fechavencimiento);
        System.out.println(entidadbancaria);
        System.out.println(tipotarjetaS);
        System.out.println(titular);
        System.out.println(CVVS);

        Date fechaJavaTarejta = procesarFecha.fechaStringtoFechaJava(fechavencimiento);

    //    Integer numero = Integer.parseInt(numeroS);
        Long numero = Long.parseLong(numeroS);
        System.out.println(numero);
        Integer CVV = Integer.parseInt(CVVS);
        Integer tipotarjeta = 0;
        Integer id = SingletonControladores.getUsuarioActual().getId();
        if(tipotarjetaS.equals("Debito")) {
            tipotarjeta = 1;
        }
        else if(tipotarjetaS.equals("Credito")) {
            tipotarjeta = 2;
        }
        String sqlValidarExistencia = "SELECT COUNT(*) FROM TARJETA WHERE NUMERO = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlValidarExistencia)) {
            preparedStatement.setLong(1, numero);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                if(count != 0){
                    return false;
                }

                String sqlAgregarTarjeta = "INSERT INTO TARJETA (NUMERO, TITULAR, FECHAVENCIMIENTO, ENTIDADBANCARIA, TIPOTARJETAID, PERSONAID) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(sqlAgregarTarjeta)) {
                    insertStatement.setLong(1, numero);
                    insertStatement.setString(2,titular);
                    insertStatement.setDate(3, procesarFecha.fechaJavaToFechaSql(fechaJavaTarejta));
                    insertStatement.setString(4, entidadbancaria);
                    insertStatement.setInt(5,tipotarjeta);
                    insertStatement.setInt(6,id);

                    int filasAfectadas = insertStatement.executeUpdate();
                    if (filasAfectadas < 1) {
                        return false;
                    }
                    return true;
                }
            }catch (Exception f) {
                System.out.println(f.getMessage()); //
        }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());}//
        return false;
    }
}
