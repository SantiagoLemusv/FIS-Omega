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
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM LIBROVIRTUAL WHERE LOWER(TITULO) LIKE LOWER(?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set the search term parameter using a case-insensitive search
            statement.setString(1, tituloLibro + "%");

            ResultSet resultSet = statement.executeQuery();

            listaLibrosVirtuales.clear(); // Clear the list before adding new results

            while (resultSet.next()) {
                // Create a LibroVirtual object and populate data
                LibroVirtual libroAux = new LibroVirtual();
                libroAux.setId(resultSet.getInt("ID"));
                libroAux.setIsbn(resultSet.getString("ISBN"));
                libroAux.setTitulo(resultSet.getString("TITULO"));
                libroAux.setCantidad(resultSet.getInt("CANTIDAD"));
                libroAux.setAutor(resultSet.getString("AUTOR"));
                libroAux.setMultaValorDia(resultSet.getInt("MULTAVALORDIA"));
                libroAux.setDuracionPrestamo(resultSet.getInt("DURACIONPRESTAMO"));

                // Get the image from the blob (assuming conversion logic remains the same)
                Blob imagenBlob = resultSet.getBlob("IMAGENLIBRO");
                libroAux.setImagenLibro(conversorImagen.blobToImageView(imagenBlob));

                listaLibrosVirtuales.add(libroAux);
            }

            // Close resources
            resultSet.close();
            statement.close();

            System.out.println("Búsqueda finalizada.");
            if (listaLibrosVirtuales.isEmpty()) {
                System.out.println("Ningún libro encontrado con el título especificado.");
            } else {
                System.out.println(listaLibrosVirtuales.size() + " libros encontrados.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}