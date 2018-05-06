

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class writeToTxt {
public static void writeToTxts(int[][] pixels, String path) {
	File file= new File(path);
	if(!file.exists()) {
		file.mkdirs();
	}
	FileOutputStream fop=null;
	try {
		fop= new FileOutputStream(file);
		for(int i=0; i<pixels.length; i++) {
			for(int j=0; j<pixels[0].length; j++) {
				try {
					fop.write(pixels[i][j]);
					fop.flush();
					fop.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static String[] getFileName(String path) {
	File inputFile=new File(path);
	if(!inputFile.exists()) {
		System.out.println("File is not existed, please check your path.");
	}
	System.out.println("Reading the file...");
	File listFile[]= inputFile.listFiles();
	String[] fileName= new String[listFile.length];
	for(int i=0; i<listFile.length; i++) {
		fileName[i]= listFile[i].getName();    //getPath() 为获取绝对路径
	}
	return fileName;
}
}
