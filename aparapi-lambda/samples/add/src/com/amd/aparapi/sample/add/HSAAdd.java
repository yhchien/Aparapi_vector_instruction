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

   public static void main(String[] _args){

    double HSAstartTime,HSAendTime,HSAtotTime;
	double SEQstartTime,SEQendTime,SEQtotTime;
	double JTPstartTime,JTPendTime,JTPtotTime;

	final int size = 2048;
    final float[] a = new float[size];
    final float[] b = new float[size];

      for (int i = 0; i < size; i++) {
         a[i] = (float)(Math.random()*100);
         b[i] = (float)(Math.random()*100);
      }

      final float[] sum = new float[size];
	 
      /** Initialize input array. */
     // Aparapi.range(size).forEach(gid -> values[gid] = gid);

      HSAstartTime = System.currentTimeMillis();
      Device.hsa().forEach(1, size, gid-> sum[gid] = a[gid] + b[gid]);
	  
	  HSAendTime = System.currentTimeMillis();
      HSAtotTime = HSAendTime - HSAstartTime;
      System.out.println(" HSA Using Time:" + HSAtotTime/1000+"sec");	

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

   }

}
