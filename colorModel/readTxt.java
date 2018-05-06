package colorModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class readTxt {

	//return a String matrix including the color value
	public static String[][] readT(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		String[] lines= new String[2018];
		String[][] color= new String[19][6];
		try {
			System.out.println("reading...");
			reader=new BufferedReader(new FileReader(file));
			int line= 0;
			int j=0;
			int a=-1;
			while((lines[line] = reader.readLine())!=null) {
				if(lines[line].indexOf(",")==-1) {
					a+=1;
					j=0;
				}else {
					color[a][j]= lines[line];
					j++;
				}
				line++;
			}
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(reader!=null) {
				try {
					reader.close();
				}catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		}
		return color;
	}
	
	// converting string matrix to a int matrix
	public static int[] getNumber(String s) {
		int[] RGB= new int[3];
		for(int i=0; i<2; i++) {
			String t1= s.substring(0, s.indexOf(","));
			RGB[i]=  Integer.parseInt(t1);
			s= s.substring(s.indexOf(",")+1, s.length());
		}
		RGB[2]= Integer.parseInt(s);
		return RGB;
	}
}
