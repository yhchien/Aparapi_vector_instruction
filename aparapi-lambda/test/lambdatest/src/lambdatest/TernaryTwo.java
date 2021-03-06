package lambdatest;

import com.amd.aparapi.Device;


public class TernaryTwo{


   static int[] in = new int[10240];
   static int[] out = new int[10240];

   static{
      for(int i = 0; i < 10240; i++){
         in[i] = i;
         out[i] = 0;
      }
   }

   void go(){

      Device device = Device.firstGPU();
      device.forEach(in.length, (i) -> {
         int v = in[i];
         out[i] = ((v % 2 == 0) ? ((v % 4 == 0) ? 4 : 2) : (v % 3 == 0) ? 3 : 0);
      });


      for(int i = 0; i < 64; i++){
         System.out.println(in[i] + " " + out[i]);
      }
   }

   public static void main(String[] args){
      new TernaryTwo().go();

   }
}
