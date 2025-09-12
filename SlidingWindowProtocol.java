
package SMVITM.com;
import java.util.Arrays;
public class SlidingWindowProtocol {
     private int WindowSize;
     private int[] frames;
     private boolean[] ack;
     
     public SlidingWindowProtocol(int WindowSize,int framecount){
         this.WindowSize=WindowSize;
         this.frames=new int[framecount];
         this.ack=new boolean[framecount];
         
         for(int i=0;i<framecount;i++){
             frames[i]=i;
             ack[i]=false;
         }
     }
     public void SendFrames(){
         int SendIndex=0;
         
         while(SendIndex<frames.length){
             for(int i=0;i<WindowSize &&(SendIndex+i)<frames.length;i++){
             System.out.println("Sending frames:"+frames[SendIndex+i]);
         }
             for(int i=0;i<WindowSize && (SendIndex+i)<frames.length;i++){
                 ack[SendIndex+i]=receiveACK(SendIndex+i);
             }
             while(SendIndex<frames.length && ack[SendIndex]){
                 SendIndex++;
                 
             }
             while(SendIndex<frames.length && ack[SendIndex]){
                 SendIndex++;
             }
         }
     }
     private boolean receiveACK(int frames){
         System.out.println("Receive ack for frame :"+frames);
         return true;
     }

     public static void main(String[] args){
         int WindowSize=4;
         int framecount=10;
         
         SlidingWindowProtocol swp=new SlidingWindowProtocol(WindowSize,framecount);
         swp.SendFrames();
     }
}
