package com.amd.aparapi.sample.Matrixmul;

import java.util.Random;
import com.amd.aparapi.*;
 
/**
 * @author Vasanth Raja Chittampally
 */
 
public class MainHSAIL  {

	public static class Matrixmulkernel{
	
	private Device device;
	private float[] matA;
	private float[] matB;
	private float[] matC;
	private int rows, cols1, cols2;
	
	public Matrixmulkernel(int _r, float[] _matrixA, float[] _matrixB, /*float[] _matrixC,*/ int _c1, int _c2, Device _device){
	
	rows = _r;
	cols1 = _c1;
	cols2 = _c2;
	device =_device;
	matA = new float[rows*cols1];
	matB = new float[cols1*cols2];
	matC = new float[rows*cols2];
	
	for(int i = 0; i < rows; i++ )
		for(int j = 0 ; j < cols1; j++ )
			{matA[i*cols1+j] = _matrixA[i*cols1+j];}
				
	for(int i = 0; i < rows; i++ )
		for(int j = 0 ; j < cols1; j++ )
			{matB[i*cols2+j] = _matrixB[i*cols1+j];}
			
	for(int i = 0; i < rows; i++ )
		for(int j = 0 ; j < cols1; j++ )
			{matC[i*cols1+j] = 0;}
		
	}
	
	public float[] getResult(){
		device.forEach(rows, globalId->{MatMulCalc(globalId);});
		return matC;
	}
	
	public void MatMulCalc(int globalId){
        for(int j = 0; j < cols2; j++)
           {
            float sum = 0;
            for(int k = 0; k < cols1; k++)
                   {sum += matA[globalId*cols1+k] * matB[k*rows+j];}
            matC[globalId * cols2 + j] = sum;
           }
	}
	
}//kernel class
	
    public static void main(String [] args) throws Exception
    {
    //final int r = 2048;
	final int r = 64;
	//final int r = 512;
	//final int r = 1024;
	//final int r = 810;
	
    final int c1 = r;
    final int c2 = r;
	final int LENA, LENB, LENC;
	float matrixA[];
    float matrixB[];
    float matrixC[];
	float Ans[];
	double startHSATime,endHSATime,totHSATime;
	double startJTPTime,endJTPTime,totJTPTime;		
        matrixA = new float [r * c1];
        matrixB = new float [c1 * c2];
        matrixC = new float [r * c2];

		Ans = new float [r * c2];
		
		LENA=r*c1;
		LENB=c1*c2;
		LENC=r*c2;
		
        
        //Here matrix A is initialized with random numbers
        for(int i = 0; i < r; i++ )
            for(int j = 0 ; j < c1; j++ )
                matrixA[i*c1+j] = new Random().nextFloat();
           
        // Here matrix B is initialized with random numbers
        for(int i = 0; i < r; i++ )
            for(int j = 0 ; j < c1; j++ )
                matrixB[i*c2+j] = new Random().nextFloat();
		
		
		//System.out.println("Enter hsa");
		
		startHSATime = System.currentTimeMillis();	
		final Matrixmulkernel MatrixmulkernelHSA
		= new Matrixmulkernel(r, matrixA, matrixB, c1, c2, Device.hsa());
		Ans = MatrixmulkernelHSA.getResult();
		endHSATime = System.currentTimeMillis();
		
		totHSATime = endHSATime - startHSATime;
		System.out.println("Using Time:" + totHSATime/1000+"sec");
		
		startJTPTime = System.currentTimeMillis();	
		final Matrixmulkernel MatrixmulkernelJTP
		= new Matrixmulkernel(r, matrixA, matrixB, c1, c2, Device.jtp());
		Ans = MatrixmulkernelJTP.getResult();
		endJTPTime = System.currentTimeMillis();
		totJTPTime = endJTPTime - startJTPTime;
		System.out.println("Using Time:" + totJTPTime/1000+"sec");
		/*startTime = System.currentTimeMillis();	
		final Matrixmulkernel MatrixmulkernelSEQ
		= new Matrixmulkernel(r, matrixA, matrixB, c1, c2, Device.seq());
		Ans = MatrixmulkernelSEQ.getResult();*/
		
		//endTime = System.currentTimeMillis();  
		
       /*for(int i=0; i<r;i++)
        {
            for(int j=0; j<c2; j++ )
            {System.out.println("Ans:"+Ans[i*c2 +j]);}
        }*/
		//System.out.println("over");
		/*totTime = endTime - startTime;
		System.out.println("Using Time:" + totTime/1000+"sec");*/
    }//main
}//whole
 
