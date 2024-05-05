package omega.sgb.control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.integracion.ConversorImagen;

import java.io.IOException;
import java.sql.Blob;
import javax.imageio.ImageIO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControladorBusquedaLibro {
    private Connection connection;
    private ConversorImagen conversorImagen;
    List<LibroVirtual> listaLibrosVirtuales = new ArrayList<>();

    public ControladorBusquedaLibro(Connection conexionGeneral, ConversorImagen conversorImagen) {
        this.connection = conexionGeneral;
        this.conversorImagen = conversorImagen;
    }

    public List<LibroVirtual> getListaLibrosVirtuales() {
        return listaLibrosVirtuales;
    }

    public void buscarLibros(String tituloLibro) {
        try {
            // Preparar la consulta SQL para buscar libros por título o autor
            String sql = "SELECT * FROM LibroVirtual";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Crear un objeto LibroVirtual con los datos de la fila actual
                LibroVirtual libroAux = new LibroVirtual();
                libroAux.setId(resultSet.getInt("ID"));
                libroAux.setIsbn(resultSet.getString("ISBN"));
                libroAux.setTitulo(resultSet.getString("TITULO"));
                libroAux.setCantidad(resultSet.getInt("CANTIDAD"));
                libroAux.setAutor(resultSet.getString("AUTOR"));
                libroAux.setMultaValorDia(resultSet.getInt("MULTAVALORDIA"));
                libroAux.setDuracionPrestamo(resultSet.getInt("DURACIONPRESTAMO"));
                // Obtener la imagen desde el blob y asignarla a imagenLibro
                Blob imagenBlob = resultSet.getBlob("IMAGENLIBRO");
                Image imagenAux = conversorImagen.blobToImage(imagenBlob);
                ImageView imagenView = new ImageView(imagenAux);
                libroAux.setImagenLibro(imagenView);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            // Manejar la excepción apropiadamente en tu aplicación
        }
    }
}