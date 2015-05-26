package com.amd.aparapi.sample.Vector;

import com.amd.aparapi.Aparapi;
import com.amd.aparapi.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.IntStream;
import java.lang.*;
import java.io.*;
import com.amd.aparapi.sample.Vector.Float4;

public class VecADD{

	/*public static class VectoraddHSA{
	
		private Device device;
		private Float4 a;
		private Float4 b;
		private Float4 c;
	*/	
		/*Float4 a = new Float4();
		Float4 b = new Float4();
		Float4 c = new Float4();*/
		
	/*	public VectoraddHSA(Float4 _A, Float4 _B, Float4 _C, Device _device){
		
		device =_device;
		a = _A;
		b = _B;
		c = _C;
		}
		
		public Float4 getResult(){
		device.forEach(1, 1, globalId->{Vecadd(a,b,c);});
		return c;
		}
		
		public Float4 Vecadd(Float4 aa, Float4 bb, Float4 cc) {
		
        cc.x = aa.x + bb.x;
        cc.y = aa.y + bb.y;
        cc.z = aa.z + bb.z;
        cc.w = aa.w + bb.w;
		return cc;
		}
	}*///class VectoraddHSA

    public static void main(String[] _args){

    double HSAstartTime,HSAendTime,HSAtotTime;

	Float4 A[] = new Float4[1000];
	Float4 B[] = new Float4[1000];
	Float4 C[] = new Float4[1000];
	Float4 temp[] = new Float4[1000];
//	Float4 Sum = new Float4();
	for(int i=0; i<1000; i++){
		A[i] = new Float4();
		B[i] = new Float4();
		C[i] = new Float4();
		A[i].setValues(i,i,i,i);
		B[i].setValues(i,i,i,i);
		C[i].setValues(0,0,0,0);
	}
/*
	Float4 A = new Float4();
	Float4 B = new Float4();
	Float4 C = new Float4();
	A.setValues(1,2,3,4);
	B.setValues(5,6,7,8);
	C.setValues(0,0,0,0);
*/
//	Sum.setValues(0,0,0,0);

      HSAstartTime = System.currentTimeMillis();
      //Device.hsa().forEach(1, 1000, gid-> sum[gid] = a[gid] + b[gid]);
      //Device.hsa().forEach(1, 1000, gid -> C[gid].Vecadd(A[gid] , B[gid]));
     // Device.hsa().forEach(1, 1000, gid -> C[gid].Vecadd(A[gid] , B[gid], C[gid]));	  
      Device.hsa().forEach(1, 1000, gid -> temp[gid]=C[gid].Vecadd(A[gid],B[gid],C[gid])); // this way will not emmit 'pop' insturction
	  
	 /* final VectoraddHSA VectoraddkernelHSA
	  = new VectoraddHSA(A, B, C, Device.hsa());
	  C = VectoraddkernelHSA.getResult();
	  */
	for(int i =0 ;i<10; i++)
	{System.out.println(C[i].get(2)+"");}
	  
      HSAendTime = System.currentTimeMillis();
      HSAtotTime = HSAendTime - HSAstartTime;
      System.out.println(" HSA Using Time:" + HSAtotTime/1000+"sec");	

   }//main
}//class VecADD
