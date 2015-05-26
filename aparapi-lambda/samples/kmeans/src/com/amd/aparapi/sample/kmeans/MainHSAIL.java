package com.amd.aparapi.sample.kmeans;

import com.amd.aparapi.Device;
import com.amd.aparapi.Kernel;
import com.amd.aparapi.Range;
import com.amd.aparapi.Aparapi;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.IntStream;
import java.lang.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;

public class MainHSAIL{

 public static class KmeansKernel{
		
		private final int width, height, K/*, mode_input*/;		
		private final int[] data;
		private float[] best_center;
		private float[] new_center;
		private int[] best_map;
		private int[] new_map;
		private float best_squareErr;
		private float squareerr; 
		//private Device device;
		private float best_dist;
		private boolean clustermapchange;
		
public KmeansKernel(int[] _inputArray, int _width, int _height, int _K/*, int _mode_input, Device _device*/){
	width = _width;
	height = _height;
	K = _K;
	//mode_input = _mode_input;
	//device =_device;
	data = new int[3*width*height];
	best_center = new float[3*K];
	new_map = new int[width*height];
	best_map = new int[width*height];
	new_center = new float[3*K];
	/*bi.getRGB is an array that saves image data 因此要一一讀出rgb資訊*/
		for(int i=0; i<width*height; i++)
		{
		data[3*i  ] = (_inputArray[i]>>16)&0xFF; //save red
		data[3*i+1] = (_inputArray[i]>>8)&0xFF ; //save green
		data[3*i+2] = (_inputArray[i])&0xFF; // save blue
		}
}
		
public int[] Kmeanscomputation(){

	best_squareErr=Float.MAX_VALUE;
	int[] result = new int[width*height*3];
	int best_cluster = 0;

	  for(int r=0; r < 30; r++){
		for(int i=0; i< width *height ; i++)
		new_map[i] = 255;
		 // initial center
		for(int i=0; i < K; i++)
			{
			int index = (int)(Math.random()*width*height);
			new_center[3*i  ] = (float)data[index*3] ;
			new_center[3*i+1] = (float)data[index*3+1] ;
			new_center[3*i+2] = (float)data[index*3+2] ;
			}
		clustermapchange = true;
		while(clustermapchange == true)
			{
			clustermapchange = false;
			squareerr=0;
			//System.out.println("Enter while!!");
		// find the best cluster belonged
			/*for(int m=0; m<height; m++)
				{
				for(int n=0; n<width; n++)
					{
					
					best_cluster=0;
					best_dist = Float.MAX_VALUE;
*/
					
					Device.jtp().forEach(width*height,gid -> {findbestclusterbelonged(gid);});	
					//Device.seq().forEach(width*height,gid -> {findbestclusterbelonged(gid);});
					//Device.hsa().forEach(width*height,gid -> {findbestclusterbelonged(gid);});
					/*switch(mode_input){
					
					case 1:
					Device.jtp().forEach(width*height,gid -> {findbestclusterbelonged(gid);});	//HSA
					break;
					case 2:
					Device.seq().forEach(width*height,gid -> {findbestclusterbelonged(gid);});
					break;
					case 3:
					Device.hsa().forEach(width*height,gid -> {findbestclusterbelonged(gid);});
					break;
					}*/
					//Aparapi.range(width*height).parallel().forEach(gid -> {findbestclusterbelonged(gid);});  //parallel
					//device.forEach(width*height,gid -> {findbestclusterbelonged(gid);});	
								/*)for(i=0; i<K; i++){
									int index = (m*width+n) * 3 ;
									float dist = 
									(float)Math.sqrt(data[index  ] - new_center[i*3  ]) +
									(float)Math.sqrt(data[index+1] - new_center[i*3+1]) +
									(float)Math.sqrt(data[index+2] - new_center[i*3+2]) ;
										if(dist < best_dist)
											{
											best_dist = dist;
											best_cluster = i;
											}}*/
					/*if(new_map[m*width+n] != best_cluster)
						{
						new_map[m*width+n] = best_cluster;
						clustermapchange = true;
						}*/
					//}
				//}
//System.out.println("gen HSail !!");
      squareerr = squareerr + best_dist;
	        //update new center
		for(int i=0; i<K; i++)
			{
			int total = 0 ;
			float tmpR=0, tmpG=0,tmpB=0;
			//System.out.println("Enter update new center!!");
				for(int m=0; m<height; m++)
				{
				for(int n=0; n<width; n++)
					{
					if(new_map[m*width+n] == i)
						{
						total++;
						tmpR += data[(m*width+n)*3];
						tmpG += data[(m*width+n)*3+1];
						tmpB += data[(m*width+n)*3+2];
						}
					if(total >0)
						{
						new_center[i*3  ] = (tmpR /total);
						new_center[i*3+1] = (tmpG /total);
						new_center[i*3+2] = (tmpB /total);
						}
					else
						{
						int index = (int)(Math.random()*width*height);
						new_center[i*3  ] = (float)data[index*3] ;
						new_center[i*3+1] = (float)data[index*3+1] ;
						new_center[i*3+2] = (float)data[index*3+2] ;
						}         
					}
				}
				//System.out.println("Exit check!!");
			}
			
			if(squareerr < best_squareErr)
				{
				for(int i=0; i< 3*K ; i++)
					best_center[i] = new_center[i];
				for(int i=0; i< width *height ; i++)
					best_map[i] = new_map[i];
					
				}
			}/*while(clustermapchange == 1)*/
		}/*for(r=0; r < 30; r++)*/
		
		/*for(int i=0; i<K; i++){
			for(int m=0; m<height; m++){
				for(int n=0; n<width; n++){
					if(best_map[m*width+n] == i){
					result[(m*width+n)*3  ] =0xFF000000|((int)best_center[i*3]<<16);//(unsigned char)best_center[i*3  ];
					result[(m*width+n)*3+1] =0xFF000000|((int)best_center[i*3+1]<<8);//;(unsigned char)best_center[i*3+1];
					result[(m*width+n)*3+2] =0xFF000000|((int)best_center[i*3+2]);//;(unsigned char)best_center[i*3+2];
					}
				}
			}
		}*/
		
		Aparapi.range(width*height*3).parallel().forEach(gid ->{
		for(int i=0; i<K; i++){
					if(best_map[gid] == i){
						result[gid] =0xFF000000|((int)best_center[i*3]<<16)|((int)best_center[i*3+1]<<8)|((int)best_center[i*3+2]);
					}
				}
		});
		
	
	System.out.println(best_squareErr+","+squareerr);
	return result;
}	


  public void findbestclusterbelonged(int gid){
         int best_cluster=0;
		 float best_dist = Float.MAX_VALUE;
         for(int i=0; i<K; i++){
            int index = gid * 3 ;
            float dist =square(data[index  ] - new_center[i*3  ]) +
                        square(data[index+1] - new_center[i*3+1]) +
                        square(data[index+2] - new_center[i*3+2]) ;
            if(dist < best_dist)
			{
			best_dist = dist;
			best_cluster = i;
			}
		}
		
		if(new_map[gid] != best_cluster)
			{
			new_map[gid] = best_cluster;
			clustermapchange = true;
			}
}	
/*public void findbestclusterbelonged(int gid){

		
		int best_cluster=0;
		best_dist = Float.MAX_VALUE;
		int i =0;
		//int clustermapchange = 0;
		for(i=0; i<K; i++)
		{
		int index = gid * 3;
		float dist = 
		(float)square(data[index  ] - new_center[i*3  ]) +
		(float)square(data[index+1] - new_center[i*3+1]) +
		(float)square(data[index+2] - new_center[i*3+2]) ;
			if(dist < best_dist)
			{
			best_dist = dist;
			best_cluster = i;
			}
		}
		
		if(new_map[gid] != best_cluster)
			{
			new_map[gid] = best_cluster;
			clustermapchange = true;
			}
	}*/
	
 public float square(float v)
 {return v*v;}
 
}


   public static void main(String[] args)throws Exception{
		
		/*System.out.println("Input mode(3:HSA,2:SEQ,1:JTP)");
		int mode_input = 0;
		Scanner scan = new Scanner(System.in);
		mode_input = scan.nextInt();*/
		
		double startTime,endTime,totTime;
       
		
		File f = new File("640640.gif");
		BufferedImage bi = ImageIO.read(f);
		int[] imageArray = bi.getRGB(bi.getMinX(), bi.getMinY(), 
		                   bi.getWidth(),bi.getHeight(),null,
				           0, bi.getWidth());
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		int Width = bi.getWidth();
		int Height = bi.getHeight();
		//System.out.println("minx "+ minx+ "!!" );
		//System.out.println("miny "+ miny+ "!!" );
		System.out.println("Width "+ Width+ "!!" );
		System.out.println("Height "+ Height+ "!!" );
		
		if(imageArray==null){
			System.out.println("imageArray is null!!");
		}
		
		/*if(args.length==0)
		{System.out.println("Input mode(1:HSA,2:SEQ,3:JTP)");}
		else{*/
		final KmeansKernel kmeansKernelHSA
		= new KmeansKernel(imageArray, bi.getWidth(), bi.getHeight(), 4/*, mode_input, Device.hsa(), Interger.valueOf([args(0)])*/);
		
		File output = new File("imagesmallHSA.gif");
		
		BufferedImage resultImage
		= new BufferedImage(bi.getWidth(), bi.getHeight(),
							BufferedImage.TYPE_INT_ARGB);
		startTime = System.currentTimeMillis();
		resultImage.setRGB(bi.getMinX(), bi.getMinY(), 
		                   bi.getWidth(),bi.getHeight(),
						   kmeansKernelHSA.Kmeanscomputation(),
						   0, bi.getWidth());
		endTime = System.currentTimeMillis();
        //取得程式結束的時間
        totTime = endTime - startTime;
		System.out.println("Using Time:" + totTime/1000+"sec");
		ImageIO.write(resultImage,"gif",output);
		//}
	}
}

