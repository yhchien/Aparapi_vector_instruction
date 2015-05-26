package com.amd.aparapi.sample.Floyd;

import java.util.Random;

import com.amd.aparapi.*;
//import com.amd.aparapi.Kernel.EXECUTION_MODE;

public class MainHSAIL {

	public static final Random rnd = new Random(); 
	private Device device;
	
	public static void main ( String [] arg ){
		int n = 1024; 
		int [] dp = new int [ n*n ]; 
		for ( int i=0; i<dp.length; i++ ){
			dp[i] = rnd.nextInt( 100 ); 
		}

		//for (int i = 0; i <= 2; i++) {
			long start = System.currentTimeMillis();
			getDistanceIterative(n, dp/*, Device.hsa()*/);
			long end = System.currentTimeMillis();
			long time = end - start;
			System.out.println(time + " s ");
		//}

		/*for ( int i=0; i<n; i++ ){
			for ( int j=0; j<n; j++ ){
				System.out.print ( dp[i*n+j] + " " );
			}
			System.out.println(); 
		}*/
}//main
	
	
	public static int [] getDistanceIterative( final int n, final int [] dp/*, Device _device*/){
		
		//device =_device;
		Device.hsa().forEach(n,globalId -> {
			int i = globalId/n;
			int j = globalId%n;
				for(int k =0; k<n;k++){
				
				int a  = i*n + k;
				int b  = k*n + j;
				dp[globalId] = Math.min(dp[globalId], dp[a]+dp[b]);}
				
		/*for (  k=0; k<n; k++ ){
			for ( int i=0; i<n; i++ ){
				for ( int j=0; j<n; j++ ){
					int a = i*n+j; 
					int b = i*n+k; 
					int c = k*n+j; 
					dp[a] = Math.min( dp[b]+dp[c], dp[a] );
				}
			}
		}*/
		});
		
		/*for ( int k=0; k<n; k++ ){
			for ( int i=0; i<n; i++ ){
				for ( int j=0; j<n; j++ ){
					int a = i*n+j; 
					int b = i*n+k; 
					int c = k*n+j; 
					dp[a] = Math.min( dp[b]+dp[c], dp[a] );
				}
			}
		}*/
		return dp;
	}
	
	/*public static int [] getDistance( final int n, final int [] dp ) {
		Kernel kernel= new Kernel(){
			   @Override public void run(){
				   int t = getGlobalId(0);
				   int k = getPassId();
				   int i = t/n;
				   int j = t%n; 
				   int a  = i*n + k;
				   int b  = k*n + j; 
				   dp[t] = Math.min(dp[t], dp[a]+dp[b]);
			   }
		};
		kernel.setExecutionMode( EXECUTION_MODE.CPU );
		kernel.execute( dp.length, n );
		return dp;
	}*/
	
}
