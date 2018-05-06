import java.awt.Color;

public class readPic {

	
	// Change the feature points' RGB value
	public static int getChangeColorPixels(int[] pixels) {
		double[] changing= new double[pixels.length];
		double[] dtl=new double[pixels.length];
		double r, g, b;
		double co, dt, co1, dt1;
		int ind=0;
		for(int i=0; i<pixels.length; i++) {
			r= (pixels[i] & 0xff0000)>>16;
			g=(pixels[i] & 0xff00)>>8;    //获取green值
	     	b=(pixels[i] & 0xff);          //获取blue值
	     	co = r*0.299+g*0.587+b*0.114;
	     	co1=((r/g)*(r/g)+(r/b)*(r/b)+(g/b)*(g/b)+(g/r)*(g/r)+(b/r)*(b/r)+(b/g)*(b/g))/36.00;
	     	dtl[i]=co1;
	     	changing[i]= co;
	     	if (i>0) {
	     		dt= Math.abs(changing[i]- changing[i-1]);
	     		dt1= Math.abs(dtl[i]-dtl[i-1]);
	     	}else {
	     		dt=0;
	     		dt1=0.00;
	     	}
	     	if(dt>15.00 & ind==0 & i>1 & i<4) {
	     		int rr=(int) ((int)r*0.85);
	     		int gg=(int) ((int)g*0.85);
	     		int bb=(int) ((int)b*0.85);
	     		ind+=1;
	     		pixels[i]= new Color(rr, gg, bb).getRGB();
	     	}else {
	     		if(dt1>2.50 & ind==0 & i>1 & i<4) {
		     		int rr=(int) ((int)r*0.85);
		     		int gg=(int) ((int)g*0.85);
		     		int bb=(int) ((int)b*0.85);
		     		ind+=1;
		     		pixels[i]= new Color(rr, gg, bb).getRGB();
		     	}
		     	else {
		     		pixels[i]=pixels[i];
		     	}
	     	}
	     	
		}
		return pixels[3];
	}
}
