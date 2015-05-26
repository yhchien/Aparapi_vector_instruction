package com.amd.aparapi.sample.add;

import com.amd.aparapi.Aparapi;
import com.amd.aparapi.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.IntStream;
import java.lang.*;
import java.io.*;



public class HSAAdd{

	public class TestCase{
		public float number;

		void caculate(float a,float b,  float ans)
		{
			ans = a + b;
		}
	}
	
	/*public static class oopaddkernel{
	
	private Device device;
	private float[] A;
	private float[] B;
	private float[] SUM;
	private int SIZE;
	
	public oopaddkernel(int _size, float[] _a, float[] _b, Device _device){
	
	device =_device;
	SIZE = _size;
	A = new float [SIZE];
	B = new float [SIZE];
	SUM = new float[SIZE];
	for (int i = 0; i < SIZE; i++) {
        A[i] = _a[i];
        B[i] = _b[i];
      }
	}
	
	public float[] getResult(){
		Device.hsa().forEach(SIZE,gid->{this.cacluate(gid);});
		return SUM;
	}
	
	public void cacluate(int gid){
	SUM[gid] = A[gid] + B[gid];
	}
}*///classoopadd

   public static void main(String[] _args){
		(new HSAAdd()).go(Device.getByName(/*System.getProperty("mode", "hsa"))*/"hsa"), 2048);
 /*   double HSAstartTime,HSAendTime,HSAtotTime;
	double SEQstartTime,SEQendTime,SEQtotTime;
	double JTPstartTime,JTPendTime,JTPtotTime;

	final int size = 4096;
    final float[] a = new float[size];
    final float[] b = new float[size];
	float[] Ans;
      for (int i = 0; i < size; i++) {
         a[i] = (float)(Math.random()*100);
         b[i] = (float)(Math.random()*100);
      }

      Ans = new float[size];*/
	 
      /** Initialize input array. */
     // Aparapi.range(size).forEach(gid -> values[gid] = gid);

 //     HSAstartTime = System.currentTimeMillis();
    //  Device.hsa().forEach(1, size, gid-> sum[gid] = a[gid] + b[gid]);
	  
	/*  final oopaddkernel oopaddkernelHSA
		= new oopaddkernel(size, a, b, Device.hsa());
	
	  Ans = oopaddkernelHSA.getResult();
	  
	  HSAendTime = System.currentTimeMillis();
      HSAtotTime = HSAendTime - HSAstartTime;
      System.out.println(" HSA Using Time:" + HSAtotTime/1000+"sec");	*/

     // Aparapi.range(size).parallel().forEach(gid -> squares[gid] = values[gid]*values[gid]);
	 /* SEQstartTime = System.currentTimeMillis();
      Device.seq().forEach(1, size, gid-> sum[gid] = a[gid] + b[gid]);
      SEQendTime = System.currentTimeMillis(); 
      SEQtotTime = SEQendTime - SEQstartTime;
      System.out.println("SEQ Using Time:" + SEQtotTime/1000+"sec");*/
	  
	  // Display computed square values.
      //Aparapi.range(size).forEach(id -> System.out.printf("%6.0f %8.0f\n", values[id], squares[id]));

     /* JTPstartTime = System.currentTimeMillis();
      Device.jtp().forEach(1, size, gid-> sum[gid] = a[gid] + b[gid]);
      JTPendTime = System.currentTimeMillis(); 
      JTPtotTime = JTPendTime - JTPstartTime;
      System.out.println("JTP Using Time:" + JTPtotTime/1000+"sec");*/

   }//main
   void go(Device device, int size){
		double HSAstartTime,HSAendTime,HSAtotTime;
		HSAstartTime = System.currentTimeMillis();
		
		TestCase[] a = new TestCase[size];
		TestCase[] b = new TestCase[size];
		TestCase[] ans = new TestCase[size];
		
		for (int i = 0; i < size; i++) {
			a[i].number = (float)(Math.random()*100);
			b[i].number = (float)(Math.random()*100);
			ans[i].number = 0;
		 }
		
		device.forEach(size, gid->{
			TestCase adda = a[gid];
			TestCase addb = b[gid];
			TestCase addans = ans[gid];
			adda.caculate(a[gid].number,b[gid].number,ans[gid].number);
		});
		
		//
		
		/*device.forEach(addsize, gid -> {
            Body body = bodies[gid];
            body.updatePosition(bodies);
            body.draw(pr);
         });*/

		HSAendTime = System.currentTimeMillis();
        HSAtotTime = HSAendTime - HSAstartTime;
        System.out.println(" HSA Using Time:" + HSAtotTime/1000+"sec");
   }

}
