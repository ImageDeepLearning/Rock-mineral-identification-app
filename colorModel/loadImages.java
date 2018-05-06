package colorModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class loadImages {

	public static BufferedImage loadImage(String path) {
		File file=new File(path);     //ͼƬ·��
		 BufferedImage picture=null;   //�����ʼͼƬ
		 try {
			 System.out.print("loading:"+path+",      ");
			 picture=ImageIO.read(file);     //��ȡͼƬ
			 System.out.println("successful to load the file.");
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return picture;
	}
}
