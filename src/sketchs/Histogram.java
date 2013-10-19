package sketchs;
import processing.core.*;

public class Histogram extends PApplet{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PImage img;
	PImage mask;
	PImage hist;
	final int WIDTH = 512;
	final int HEIGHT = 512;
	
	public enum color {Red, Green, Blue, Brightness};
	
	public void setup() {
		    size(WIDTH,HEIGHT);
		    background(0);
		    img = loadImage("Lenna.png");
		    stroke(100);
		    mask = createImage(100, 100, RGB);
		    stroke(100);
	}

	public void draw() {
		    image(img,0,0);
		    image(mask,mouseX,mouseY);
		    histogram(img, color.Red, mouseX, mouseY);
		    
	}
	
	public void histogram(PImage img, color c, int X, int Y) {
		int[] hist = new int[256];
		stroke(100);
		line(X,0,X,HEIGHT);
		line(X+100,0,100+X,HEIGHT);
		line(0,Y,WIDTH,Y);
		line(0,Y+100,WIDTH,Y+100);
		int value = 0;
		if(X < 50){
			X = 50;
		}
		if(Y < 50){
			Y = 50;
		}
		if(X > WIDTH -50){
			X = WIDTH -50;
		}
		if(Y > HEIGHT - 50){
			Y = HEIGHT - 50;
		}
		for (int i = X-50; i < X+50; i++) {
		  	for (int j = Y-50; j < Y+50; j++) {
		   		int pos = i+(j*img.width);
		   		switch (c) {
				case Blue:
					value = (int) blue(img.pixels[pos]);
					break;
				case Red:
					value = (int) red(img.pixels[pos]);
					break;
				case Green:
					value = (int) green(img.pixels[pos]);
					break;
				case Brightness:
					value = (int) brightness(img.pixels[pos]);
				default:
					break;
				}
		   		hist[value] += 1;
			}
		}
		int histMax = max(hist);
		for (int i = 0; i + X <  X + mask.width; i+=2) {
			  int which = (int) (map(i, 0, mask.width, 0, 255));
		   	  int y = (int) (map(hist[which], 0, histMax, mask.height, 0));
		   	  switch (c){
		   	  	case Blue:
		   	  		stroke(0,0,255);
		   	  		line(X+i, Y+mask.height, X+i, Y+y);		   	  		
		   	  		break;
		   	  	case Green:
		   	  		stroke(0,255,0);
		   	  		line(X+i, Y+mask.height, X+i, Y+y);	
		   	  		break;
		   	  	case Red:
		   	  		stroke(255,0,0);
		   	  		line(X+i, Y+mask.height, X+i, Y+y);	
		   	  		break;
		   	  	case Brightness:
		   	  		line(X+i, Y+mask.height, X+i, Y+y);	
		   	  		break;
		   	  	default:
		   	  		break;
		   	  }				
		}
	}
}
