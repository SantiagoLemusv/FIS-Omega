package omega.sgb.integracion;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javafx.scene.image.Image;
public class ConversorImagen {
    public ImageView blobToImageView(Blob imagenBlob)
            throws IOException, SQLException {
        if (imagenBlob == null) {
            return null;
        }

        byte[] bytes = imagenBlob.getBytes(1, (int) imagenBlob.length());
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));

        // Resize the BufferedImage
        BufferedImage resizedImage = resizeImage(bufferedImage, 200, 225);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "png", baos); // Assuming PNG format
        byte[] imageBytes = baos.toByteArray();

        Image fxImage = new Image(new ByteArrayInputStream(imageBytes));

        ImageView imageView = new ImageView(fxImage);


        return imageView;

    }

    // Helper method for image resizing
    private BufferedImage resizeImage(BufferedImage image, int targetWidth, int targetHeight) {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();

        double widthRatio = (double) targetWidth / originalWidth;
        double heightRatio = (double) targetHeight / originalHeight;

        double ratio = Math.min(widthRatio, heightRatio);

        int scaledWidth = (int) (originalWidth * ratio);
        int scaledHeight = (int) (originalHeight * ratio);

        BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, image.getType());
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        return resizedImage;
    }
}
