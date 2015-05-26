package com.amd.aparapi.sample.TestSync;

import com.amd.aparapi.Aparapi;
import com.amd.aparapi.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.IntStream;
import java.lang.*;
import java.io.*;

public class MainHSAIL{

   public static void main(String[] args) throws Exception{
		
		int[] ints = new int[1024];
		int[] Ans = new int[1024];
		
		for (int i = 0; i < 1024; i++) {
        ints[i] = (int)(Math.random()*100);
		System.out.println("Original:"+ints[i]);
		}
		
		final testKernel testkernelHSA = new testKernel(ints, Device.hsa());
		Ans = testkernelHSA.getResult();
		
		for (int i = 0; i < 1024; i++) {
		System.out.println("Ans:"+Ans[i]);
		}
	//Device.hsa().forEach(1, 1024, gid-> {execute(gid);});
	}//main
	
	public static class testKernel{
		private Device device;
		private int[] intCopy;
	
		public testKernel(int[] _ints, Device _device){
		
		device = _device;
		intCopy = new int[1024];
		
		for (int i = 0; i < 1024; i++){
		intCopy[i] = _ints[i];
		}
	}
	
		public int[] getResult(){
		device.forEach(1, 1024,gid->{execute(gid);});
		return intCopy;
		}
		
		public void execute(int gid){
			int foo = 1;
				if(gid % 2 == 0){
					intCopy[gid] = doIt(gid);
				}else{
					synchronized(this){
					intCopy[gid] = doIt2(foo);
					}
				}
		}
		
		synchronized int doIt(int a){
			return (int) (((int) 1) - a);
		}

		int doIt2(int a){
			return (int) (((int) 1) - a);
		}
		
	}//static class
}//class