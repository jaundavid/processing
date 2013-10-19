package sketchs;

import processing.core.PApplet;
import processing.core.PImage;

public class Metrics extends PApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PImage img,img2, img3,img4,img5;
	
	public void setup() {
	    size(800,600);
	    img = loadImage("lena.jpg");
	    img2 = loadImage("lena5.jpg");
	    pnsr(img,img);
	    pnsr(img,img2);
	    img3 = loadImage("lena10.jpg");
	    pnsr(img,img3);
	    img4 = loadImage("lena75.jpg");
	    pnsr(img,img4);
	    img5 = loadImage("lena80.jpg");
	    pnsr(img,img5);
	}
	
	public void draw() {
		image(img,0,0);
		image(img2,256,0);
		image(img3,512,0);
		image(img4,0,256);
		image(img5,256,256);
		
	}
	public float pnsr(PImage src, PImage trg){
		float mse = mse(src, trg);
		float psnr = 10*log(pow(255,2)/mse);
		return psnr;
	}
	public float rmse(PImage src, PImage trg){
		float mse = mse(src, trg);
		float rmse = sqrt(mse);
		return rmse;		
	}
	public void ssim(){
		
	}
	public void jaccard_coefficient(){
		
	}
	public float mse(PImage src, PImage trg){
		float r = 0;
		float g = 0;
		float b = 0;
		for (int i = 0; i < 13; i++) {			
			for (int j = 0; j < 13; j++) {
				int pos = i+(j*src.width);
				r += pow((int) red(src.pixels[pos]) - (int) red(trg.pixels[pos]),2);
				g += pow((int) green(src.pixels[pos]) - (int) green(trg.pixels[pos]),2);
				b += pow((int) blue(src.pixels[pos]) - (int) blue(trg.pixels[pos]),2);				
			}
		}
		float mse = (r+g+b)/(src.width * src.height * 3);
		return mse;
	}

}
