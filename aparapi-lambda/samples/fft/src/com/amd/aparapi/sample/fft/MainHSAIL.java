//package com.amd.aparapi.sample.extension;
package com.amd.aparapi.sample.fft;
import java.util.Arrays;

import com.amd.aparapi.Device;
import com.amd.aparapi.OpenCL;
import com.amd.aparapi.OpenCLDevice;
import com.amd.aparapi.Range;

public class MainHSAIL{
 
   public static class fftKernel{
   	
	private final short dir;
	private Device device;
	private int n, i1, i2, j, j1, j2, k, m, l, l1, l2, length;
	private double tempx, tempy ,c1, c2, t1, t2, u1, u2, z;
	private float[] dataReal;
	private float[] dataImaginary;
	
	
    public fftKernel(float[] _inputReal, float[] _inputImaginary, int _length, Device _device)
	{
	
	/*short dir;
    long m;
    int n, i, i1, j, k, i2, l, l1, l2;
    double c1, c2, tx, ty, t1, t2, u1, u2, z;*/
	
	dir = 1;
	m = 16;  // m need to modify by squres 依照次方數而定
	device = _device;
	length = _length;
	dataReal = new float[length];
	dataImaginary = new float[length]; 

	    /* Calculate the number of points */
    n = 1;
    for (int i = 0; i < m; i++)
        n *= 2;
		
	/* Do the bit reversal */
      i2 = n >> 1;
      j = 0;
      for (int i = 0; i < n - 1; i++) {
         if (i < j) {
            tempx = _inputReal[i];
            tempy = _inputImaginary[i];
            _inputReal[i] = _inputReal[j];
            _inputImaginary[i] = _inputImaginary[j];
            _inputReal[j] = (float) tempx;
            _inputImaginary[j] = (float) tempy;
         }
         k = i2;
         while (k <= j) {
            j -= k;
            k >>= 1;
         }
         j += k;
      }
		
		for (int i=0; i<length; i++)
		{
		dataReal[i] = _inputReal[i];
		dataImaginary[i] = _inputImaginary[i];
		}
		
	/* Compute the FFT */
	c1 = -1.0;
    c2 = 0.0;
    l2 = 1;
	
	
	
  }//public fftKernel
  
  public float[] getResult()
  {
	device.forEach(m, gid ->{ComputeFFT(gid);});
	
	return dataReal;
  }
  
  public void ComputeFFT(int gid){
		//for (l = 0; l < m; l++) {
			l1 = l2;
			l2 <<= 1;
			u1 = 1.0;
			u2 = 0.0;
			for (int j = 0; j < l1; j++) {
				for (int i = j; i < n; i += l2) {
					i1 = i + l1;
					t1 = u1 * dataReal[i1] - u2 * dataImaginary[i1];
					t2 = u1 * dataImaginary[i1] + u2 * dataReal[i1];
					dataReal[i1] = (float) (dataReal[i] - t1);
					dataImaginary[i1] = (float) (dataImaginary[i] - t2);
					dataReal[i] += (float) t1;
					dataImaginary[i] += (float) t2;
				}
            z = u1 * c1 - u2 * c2;
            u2 = u1 * c2 + u2 * c1;
            u1 = z;
         }
			c2 = Math.sqrt((1.0 - c1) / 2.0);
			if (dir == 1)
				c2 = -c2;
			c1 = Math.sqrt((1.0 + c1) / 2.0);
		//}
	}  
}//public static class fftKernel




   public static void main(String[] args) {
   
      final int LEN = 65536;
      float initial[] = new float[LEN];
      float real[] = new float[LEN];
      float imaginary[] = new float[LEN];
      float referenceReal[] = Arrays.copyOf(real, real.length);
      float referenceImaginary[] = Arrays.copyOf(imaginary, imaginary.length);
      double startTime,endTime,totTime;
	  
	  
	  System.out.println("Start\n");
	
	  for (int i = 0; i < LEN; i++) {
         initial[i] = real[i] = referenceReal[i] = (float) (Math.random() * 256);
         imaginary[i] = referenceImaginary[0] = 0f;
      }
	  
	 /* for (int i = 0; i < LEN; i++) {
	  System.out.printf("%d %5.2f \n", i, initial[i]);
      }*/
	  
	  System.out.println("initial over\n");
	  
	  startTime = System.currentTimeMillis();
	  final fftKernel fftkernelHSA = new fftKernel(referenceReal, referenceImaginary, LEN, Device.hsa());
	  endTime = System.currentTimeMillis();//取得程式結束的時間
	  
	  real = fftkernelHSA.getResult();
	  
	 /* startTime = System.currentTimeMillis();
	  final fftKernel fftkernelSEQ = new fftKernel(referenceReal, referenceImaginary, LEN, Device.seq());
	  endTime = System.currentTimeMillis();//取得程式結束的時間
	  
	  real = fftkernelSEQ.getResult();*/
	  
	/* startTime = System.currentTimeMillis();
	  final fftKernel fftkernelJTP = new fftKernel(referenceReal, referenceImaginary, LEN, Device.jtp());
	  endTime = System.currentTimeMillis();//取得程式結束的時間
	  
	  real = fftkernelJTP.getResult();*/
	  
	 /* for (int i = 0; i < LEN; i++){
	  System.out.printf("%d %5.2f \n", i, real[i]);
	  }*/
	  //fft(referenceReal, referenceImaginary);
	  totTime = endTime - startTime;
		System.out.println("Using Time:" + totTime/1000+"sec");
	  System.out.println("over compute\n");
	 
     /* for (int i = 0; i < LEN; i++) {
         if (Math.abs(real[i] - referenceReal[i]) > 0.01) {
            System.out.printf("%d %5.2f %5.2f\n", i, initial[i], real[i]);
         }
      }*/
		
   }//main
}// whole
