package omega.sgb.control;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.integracion.ConversorImagen;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControladorBusquedaLibro {
    private Connection connection;
    private ConversorImagen conversorImagen;
    private String tituloLibroBusquedaGrande;
    List<LibroVirtual> listaLibrosVirtuales = new ArrayList<>();
    LibroVirtual libroVirtualSeleccionado = new LibroVirtual();
    LibroFisico libroFisicoSeleccionado = new LibroFisico();

    public ControladorBusquedaLibro(Connection conexionGeneral, ConversorImagen conversorImagen) {
        this.connection = conexionGeneral;
        this.conversorImagen = conversorImagen;
    }

    public String getTituloLibroBusquedaGrande() {
        return tituloLibroBusquedaGrande;
    }

    public void setTituloLibroBusquedaGrande(String tituloLibroBusquedaGrande) {
        this.tituloLibroBusquedaGrande = tituloLibroBusquedaGrande;
    }

    public List<LibroVirtual> getListaLibrosVirtuales() {
        return listaLibrosVirtuales;
    }

    public LibroVirtual getLibroVirtualSeleccionado() {
        return libroVirtualSeleccionado;
    }

    public void setLibroVirtualSeleccionado(LibroVirtual libroSeleccionado) {
        this.libroVirtualSeleccionado = libroSeleccionado;
    }

    public LibroFisico getLibroFisicoSeleccionado() {
        return libroFisicoSeleccionado;
    }

    public void setLibroFisicoSeleccionado(String clasificacion) {
        System.out.println("entro a fisicoooooooooooooooooooo "+clasificacion);
        LibroFisico libroNuevo = new LibroFisico();
        for(LibroFisico libroFisico : libroVirtualSeleccionado.getLibrosFisicosTotales()){
            if(libroFisico.getNumeroClasificacion().equals(clasificacion)){
                System.out.println("entro a ifffffffffffffffff");
                libroNuevo = libroFisico;
            }
        }
        this.libroFisicoSeleccionado = libroNuevo;
    }

    public void buscarLibrosFisicos(String tituloLibro) {
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
                libroAux.setCantidadCopias(resultSet.getInt("CANTIDAD"));
                libroAux.setAutor(resultSet.getString("AUTOR"));
                libroAux.setMultaValorDia(resultSet.getInt("MULTAVALORDIA"));
                libroAux.setDuracionPrestamo(resultSet.getInt("DURACIONPRESTAMO"));

                // Get the image from the blob (assuming conversion logic remains the same)
                Blob imagenBlob = resultSet.getBlob("IMAGENLIBRO");
                libroAux.setImagenLibro(conversorImagen.blobToImageView(imagenBlob));
                libroAux.setLibrosFisicosDisponibles(traerLibrosFisicos(libroAux, 1));
                libroAux.setLibrosFisicosAgotados(traerLibrosFisicos(libroAux, 2));
                libroAux.setLibrosFisicosTotales();
                listaLibrosVirtuales.add(libroAux);
            }

            // Close resources
            resultSet.close();
            statement.close();

            System.out.println("Búsqueda libros virtuales finalizada.");
            if (listaLibrosVirtuales.isEmpty()) {
                System.out.println("Ningún libro encontrado con el título especificado.");
            } else {
                System.out.println(listaLibrosVirtuales.size() + " libros encontrados.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<LibroFisico> traerLibrosFisicos(LibroVirtual libroVirtual, Integer disponibilidad) {
        List<LibroFisico> listaLibrosFisicos = new ArrayList<>();
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM LIBROFISICO WHERE LIBROVIRTUALID = ? AND ESTADOLIBROFISICOID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, libroVirtual.getId());
            statement.setInt(2, disponibilidad);

            ResultSet resultSet = statement.executeQuery();

            listaLibrosFisicos.clear(); // Clear the list before adding new results

            while (resultSet.next()) {
                // Create a LibroVirtual object and populate data
                LibroFisico libroAux = new LibroFisico();
                libroAux.setId(resultSet.getInt("ID"));
                libroAux.setUbicacion(resultSet.getString("UBICACION"));
                libroAux.setNumeroClasificacion(resultSet.getString("NUMEROCLASIFICACION"));
                libroAux.setLibroVirtual(libroVirtual);
                libroAux.setEstadoLibroFisicoId(resultSet.getInt("ESTADOLIBROFISICOID"));

                listaLibrosFisicos.add(libroAux);
            }

            // Close resources
            resultSet.close();
            statement.close();

            System.out.println("Búsqueda libros fisicos finalizada.");
            if (listaLibrosFisicos.isEmpty()) {
                System.out.println("Ningún libro fisico encontrado con el título especificado.");
            } else {
                System.out.println(listaLibrosVirtuales.size() + " libros fisicos encontrados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibrosFisicos;
    }

    public List<String> getPisosLibrosFisicos(List<LibroFisico> librosFisicos){
        List<String> pisosLibrosFisicos = new ArrayList<>();
        for(LibroFisico libroActual : librosFisicos){
            pisosLibrosFisicos.add(libroActual.getUbicacion());
        }
        return pisosLibrosFisicos;
    }

    public List<String> getNumClasificacionLibrosFisicos(List<LibroFisico> librosFisicos){
        List<String> numClasificacionLibrosFisicos = new ArrayList<>();
        for(LibroFisico libroActual : librosFisicos){
            numClasificacionLibrosFisicos.add(libroActual.getNumeroClasificacion());
        }
        return numClasificacionLibrosFisicos;
    }

    public List<String> combinarPisoConClasificacion (List<LibroFisico> librosFisicos){
        System.out.println("\n"+"entra combina combo box");
        List<String> listaPisos = getPisosLibrosFisicos(librosFisicos);
        List<String> listaClasificacion = getNumClasificacionLibrosFisicos(librosFisicos);
        List<String> listaCombinada = new ArrayList<>();
        for (int i = 0; i < listaPisos.size() && i < listaClasificacion.size(); i++) {
            String combinado = listaPisos.get(i) + "," + listaClasificacion.get(i);
            listaCombinada.add(combinado);
        }
        for(int i = 0; i<listaCombinada.size(); i++){
            System.out.println("listaaaaaaaaaaaaaaaaaaaaaaaaaaa comoboooooooooooooo");
            System.out.println(listaCombinada.get(i));
        }
        return listaCombinada;
    }
}