package colorModel;

import java.awt.Label;
import java.awt.image.BufferedImage;

public class color {
	private static final String[] lable={"辰砂 ","赤铁矿","磁铁矿","电气石","方铅矿","方解石","褐铁矿","黄铁矿","黄铜矿","辉钼矿","辉锑矿","孔雀石","蓝铜矿","绿柱石","普通辉石","闪锌矿","石膏","石英","锡石"};
	
	public static void classifyByColor(String imagePath, String modelPath) {
		BufferedImage image= loadImages.loadImage(imagePath);
		int width= image.getWidth();
		int height= image.getHeight();
		int[][] pixels= new int[width][height];
		
		int[] RGBA= new int[3];
		int r=0;
		int g=0;
		int b=0;
		int[][] distance= new int[19][4];
		int[][] mindistance=new int[19][2];
		float[][] score= new float[19][2];
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				int pixel= image.getRGB(i,j);
				r+= (pixel & 0xff0000)>>16;
				g+=(pixel & 0xff00)>>8;    //获取green值
		     	b+=(pixel & 0xff);          //获取blue值  	
			}
		}
		RGBA[0]= r/(width*height);
		RGBA[1]= g/(width*height);
		RGBA[2]= b/(width*height);
		String[][] color= readTxt.readT(modelPath);
		
		//calculate all distance
		for(int kind=0; kind<19; kind++) {
			int weidth= color[kind].length;
			for(int j=0; j<width; j++) {
				if(color[kind][j]==null) {
					break;
				}
				int[] RGB= readTxt.getNumber(color[kind][j]);
				distance[kind][j]= calDis(RGBA, RGB);
			}
		}
		
		// calculate the min distance
		int sumDistance=0;
		for(int i=0; i<19; i++) {
			mindistance[i][0]=i;
			mindistance[i][1]= minDis(distance[i]);
		}
		mindistance= chooseDis(mindistance);
		for(int i=0; i<mindistance.length;i++) {
			sumDistance+=mindistance[i][1];
			System.out.println(mindistance[i][1]);
		}
		
		// calculate the scores
		float sumScore=0;
		for (int i=0; i<mindistance.length; i++) {
			score[i][0]= (float)mindistance[i][0];
			score[i][1]=1- (float)mindistance[i][1]/(float)sumDistance;
			sumScore+=score[i][1];
		}
		
		for (int i=0; i<mindistance.length; i++) {
			score[i][1]= score[i][1]/sumScore;
			System.out.println("Possibilaty of"+lable[(int) score[i][0]]+"is"+score[i][1]);
		}
		
	}
	
	public static int calDis(int[] RGBA, int[] RGB) {
		int distance=0;
		for (int i=0; i<3; i++) {
			distance+= Math.pow(RGBA[i]-RGB[i], 2);
		}
		return distance;
	}
	
	public static int minDis(int[] distance) {
		int t=0;
		for(int i=0; i<4; i++) {
			if(t < distance[i]) {
				t= distance[i];
			}
		}
		return t;
	}
	
	public static int[][] chooseDis(int[][] distance) {
		int[][] choose= new int[3][2];
		int a=0;
		int width= distance.length;
		for (int i=0; i<width; i++) {
			choose[a][1]= distance[i][1];
			for(int j=a+1; j<width; j++) {
				if(choose[a][1]>distance[j][1]) {
					choose[a][0]=distance[j][0];
					choose[a][1]=distance[j][1];
					int t=distance[j][0];
					int m=distance[j][1];
					distance[j][0]=distance[a][0];
					distance[j][1]=distance[a][1];
					distance[a][0]=t;
					distance[a][1]=m;
				}
			}
			System.out.println(choose[a][0]);
			a+=1;
			if(a==3) {
				break;
			}
		}
		return choose;
	}
}
