package pe.edu.system.jcmr.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImgeUtilFx {
	 /**
	  * 
	  * @param imagen
	  * @param photo
	  */
   public static void  setByteToImageView(ImageView imagen, byte[] photo){
   	try {
           ByteArrayInputStream in = new ByteArrayInputStream(photo);
           BufferedImage read = ImageIO.read(in);
           imagen.setImage(SwingFXUtils.toFXImage(read, null));
		} catch (Exception e) {
		   e.printStackTrace();
		}
   }

	public static Image fileToImage(File file) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(file);
		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
		return image;
	}
   public static Image convertByteToImage(byte[] foto) {
		BufferedImage read=null;
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(foto);
			read= ImageIO.read(in);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SwingFXUtils.toFXImage(read, null);
	}
	
   public static ImageView byteToImageSize(byte[] img,Double height,Double width){
   	 
		ImageView imgView = new ImageView();

		imgView.setImage(convertByteToImage(img));
		imgView.setFitWidth(height);
		imgView.setFitHeight(width);
		return imgView;
   }
   public static ImageView urlToImageSize(String imgUrl,Double height,Double width){
  	 
		ImageView imgView = new ImageView();
		Image img = new Image(imgUrl);
		imgView.setImage(img);
		imgView.setFitWidth(height);
		imgView.setFitHeight(width);
		return imgView;
   }
   /* Metodo de invacion al chageColorImage try {
            BufferedImage img = colorImage(ImageIO.read(new File("C:/Users/Julio/Desktop/150-cogs.png")));
            ImageIO.write(img, "png", new File("C:/Users/Julio/Desktop/Test.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
/**
 * Metodo que permite cambiar de color a una img png   
 * @param image
 * @param colorOrignalImagen
 * @param colorOutputImage
 * @return
 */
	public static BufferedImage changeColorImage(BufferedImage image,Color colorOrignalImagen,Color colorOutputImage) {
		int width = image.getWidth();
		int height = image.getHeight();

		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				Color originalColor = new Color(image.getRGB(xx, yy), true);
				if (originalColor.equals(colorOrignalImagen) && originalColor.getAlpha() == 255) {
					image.setRGB(xx, yy, colorOutputImage.getRGB());
				}
			}
		}
	return image;
 }
   
}
