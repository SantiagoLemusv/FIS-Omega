package omega.sgb.integracion;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class Imagen {
    public Image blobToImage(Blob imagenBlob) throws IOException, SQLException {
        if (imagenBlob == null) {
            return null;
        }

        byte[] bytes = imagenBlob.getBytes(1, (int) imagenBlob.length());
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));

        return new Image(new ByteArrayInputStream(bytes));
    }
}
