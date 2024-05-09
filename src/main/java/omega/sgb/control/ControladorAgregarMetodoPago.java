package omega.sgb.control;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.dominio.Tarjeta;
import omega.sgb.integracion.ConversorImagen;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControladorAgregarMetodoPago {



    private Connection connection;
    List<Tarjeta> listaTiposTarjeta = new ArrayList<>();

    Tarjeta tarjeta;

    public ControladorAgregarMetodoPago(Connection conexionGeneral, ConversorImagen conversorImagen) {
        this.connection = conexionGeneral;
    }

    public boolean agregarMetodoPago(String numero, String fechavencimiento, String entidadbancaria, String tipotarjeta, String titular) throws SQLException {
        Integer num = Integer.parseInt(numero);
        return false;
    }
}