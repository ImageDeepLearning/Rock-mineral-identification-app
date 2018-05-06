package colorModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class loadImages {

	public static BufferedImage loadImage(String path) {
		File file=new File(path);     //Õº∆¨¬∑æ∂
		 BufferedImage picture=null;   //∂®“Â≥ı ºÕº∆¨
		 try {
			 System.out.print("loading:"+path+",      ");
			 picture=ImageIO.read(file);     //∂¡»°Õº∆¨
			 System.out.println("successful to load the file.");
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return picture;
	}
}
