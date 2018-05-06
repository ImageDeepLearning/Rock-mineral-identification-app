import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class savePic {

	// save the feature-extraction images
	public static void savePicture(String filePath) {
		String[] firstFileName= writeToTxt.getFileName(filePath);
		 for(int x=0; x<firstFileName.length; x++) {
			 String secondFileName=filePath+firstFileName[x];
			 String[] fileName= writeToTxt.getFileName(secondFileName);
			 for(int t=1; t<fileName.length; t++) {
				 String path= secondFileName+"\\"+fileName[t];
				 File file=new File(path);     //图片路径
				 BufferedImage picture=null;   //定义初始图片
				 try {
					 System.out.print("reading:"+path+",      ");
					 picture=ImageIO.read(file);     //读取图片
					 System.out.println("successful to load the file.");
				 } catch (IOException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				 }
				 int width= picture.getWidth();      //获取图片宽度
				 int height= picture.getHeight();    //获取图片长度
				 int minX=picture.getMinX();       //获取图片最小有意义点坐标X值
				 int minY=picture.getMinY();       //获取图片最小有意义点左边Y值
				 int[][] pixels=new int[width-minX][height-minY];   //综合值存储矩阵
				 for(int i=minX; i<width; i++) {
					 for(int j=minY; j<height; j++) {
						 int pixel= picture.getRGB(i, j);
						 pixels[i-minX][j-minY]=pixel;                 //综合值
					 }
				 }
			 
				 int[][] theLastPixels= new int[pixels.length][pixels[0].length];
				 int[] tempPixel= new int[5];
				 for(int i=1; i<pixels.length-1; i++) {
					 for (int j=1; j<pixels[0].length-1; j++) {
						 tempPixel[0]= pixels[i][j-1];
						 tempPixel[1]= pixels[i-1][j];
						 tempPixel[2]= pixels[i][j];
						 tempPixel[3]= pixels[i+1][j];
						 tempPixel[4]= pixels[i][j+1];
						 theLastPixels[i][j]= readPic.getChangeColorPixels(tempPixel);
					 }
				 }
				 savePicByPixels("savePath",firstFileName[x], t, theLastPixels);
		 		}
			 System.out.println(firstFileName[x]+"  has been changed.");
	 		}
		 System.out.println("finished");
	 }
	
	
	
	
	public static void savePicByPixels(String path,String name, int t, int[][] pixels) {
		System.out.println("deal with the"+t+"image");
		BufferedImage savePic= new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_RGB);  //设置图片容器
		for (int i=0; i<pixels.length; i++) {
			for (int j=0; j<pixels[0].length; j++) {
				Color color= new Color(pixels[i][j]);
				 savePic.setRGB(i, j, color.getRGB());
			}
		}
		File file=new File(path+name);
		if(!file.exists()) { file.mkdirs(); }
		 String savePath=path+name+"\\"+name+t+".jpg";
		 String ext="jpg";
		 try {
			 System.out.print("is forming the"+t+"image"+",        ");
			ImageIO.write(savePic, ext, new File(savePath));
			System.out.println("the image has been formed, path："+savePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
