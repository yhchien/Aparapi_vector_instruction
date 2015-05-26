import com.amd.aparapi.Device;
///dist/aparapi

public class Squares_test{


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
         out[i] = in[i] * in[i];
      });


      for(int i = 0; i < 64; i++){
         System.out.println(in[i] + " " + out[i]);
      }
   }

   public static void main(String[] args){
      new Squares().go();

   }
}
