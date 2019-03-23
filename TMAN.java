import java.io.*;
import java.util.Arrays;
import java.util.*;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities;

public class TMAN{
	double x;
	double y;
	int usrId;
	double theta;
	int [] neighbourList;
	
	
	
	 public static double distcalcB(double x1,double y1,double x2,double y2,int own, int other ,int numNode)  //function for distance in "b" topology
	    {
	    	if (own==numNode ) {
	    	
	    		if(other==1 || other==numNode-1)
	    		{
	    			return 1;
	    		}
	    		else {
	    			return Double.MAX_VALUE;
	    		}
	    	}	
	    		
	    	if (other==numNode)
	    		{
	    			 if(own==1 || own==numNode-1) {
	    				return 1;
	    			}
	    			
	    			else {
	    				return Double.MAX_VALUE;
	    			}
	    		}
	    	else {
	    			double distance = Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)));
	    			return distance;
	    		}
	    	}
	 public static double distcalcS(double x1, double y1,double x2, double y2, int own, int other, int numNode)  //function for distance in "spectacle" topology
	 {
		 double distance = Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)));
	     return distance;
	 }
	public static void main(String args[]) throws IOException {

		double[] fsum = new double[41];
		char structure;
		double sum=0;
        Random rand = new Random();
        int numNode=Integer.parseInt(args[0]);
        int k=Integer.parseInt(args[1]);
        structure=args[2].charAt(0);
        System.out.println("The number of nodes is " + numNode);
        System.out.println("The view size is " + k);
        System.out.println("The structure is  " + structure);
        TMAN[] objName=new TMAN[numNode+1];
        String name1= structure+"_N"+Integer.toString(numNode)+"_k"+Integer.toString(k)+".txt";
        String name2= structure+"_N"+Integer.toString(numNode)+"_k"+Integer.toString(k)+"_1.txt";
        String name3= structure+"_N"+Integer.toString(numNode)+"_k"+Integer.toString(k)+"_5.txt";
        String name4= structure+"_N"+Integer.toString(numNode)+"_k"+Integer.toString(k)+"_10.txt";
        String name5= structure+"_N"+Integer.toString(numNode)+"_k"+Integer.toString(k)+"_15.txt";
        PrintStream objFile1 = new PrintStream(new File(name1));
        PrintStream objFile2 = new PrintStream(new File(name2));
        PrintStream objFile3 = new PrintStream(new File(name3));
        PrintStream objFile4 = new PrintStream(new File(name4));
        PrintStream objFile5 = new PrintStream(new File(name5));
        //*********************************************initializing object and user id**************************************************
        for(int f=1;f<=numNode;f++)
        {
        	objName[f]=new TMAN();
        	objName[f].usrId=f;
        	objName[f].neighbourList=new int[k];
        	if(structure=='B')                               //calculating theta and X and Y cordinate for b structure
            {
            objName[f].theta=((Math.PI/2)-((f-1)*Math.PI/(numNode-2)));
            
            objName[f].x=1+Math.cos(objName[f].theta);
            objName[f].y=-Math.sin(objName[f].theta);                  //Setting the X and Y co-ordinate
//            System.out.print("  X coordinate " +objName[f].x+"\t");
//            System.out.println("  Y coordinate " +objName[f].y+"\t");
        
            }
        	
        	if(structure=='S')
            {
           	 if(f<=(numNode*2)/5)
           	 {
           		 objName[f].theta=((f-1)*((5*Math.PI)/(numNode)));
           		 objName[f].x=(Math.cos(objName[f].theta));
           		 objName[f].y=Math.sin(objName[f].theta);
           	 }
           	 else if(f>(numNode*2)/5 && f<=(3*numNode)/5)
           	 {
           		 objName[f].theta=Math.PI+((f-((2*numNode)/5))*(5*Math.PI)/(numNode+5));
           		 objName[f].x=(2+Math.cos(objName[f].theta));
           		 objName[f].y=-(Math.sin(objName[f].theta));
           	 }
           	 else
           	 {
           		 objName[f].theta=(Math.PI+((f-(3*numNode)/5)*(5*Math.PI)/numNode));
           		 objName[f].x=(4+Math.cos(objName[f].theta));
           		 objName[f].y=Math.sin(objName[f].theta); 
           	 }
           	 
           	 
            }
        }
        //********************************************initialization of neighbour list***************************************************
        
        for(int f=1;f<=numNode;f++)
        {
        	
        	for(int i=0;i<k;i++)
            {

               int  random = rand.nextInt(numNode) + 1;
               while(random==f)
                {
                   random = rand.nextInt(numNode) + 1;
               
                } 
               int j=0;
               while(j<i)
               {
                   
                   if(random==objName[f].neighbourList[j] || random==f)
                   {
                      // System.out.println("got wrong value");
                       j=0;
                       random=rand.nextInt(numNode) + 1;
                       continue;
                   }
                    j=j+1;
               }
            
                //System.out.println("print"+random);
                  objName[f].neighbourList[i]=random;
            }
        	if(f==numNode)
        	{
        		objName[f].neighbourList[0]=1;
        		objName[f].neighbourList[1]=numNode-1;
        		
        	}
        	System.out.print("User id is :"+objName[f].usrId);
            System.out.print("(");
            for(int i=0;i<k;i++)
               {
                System.out.print(objName[f].neighbourList[i]+"  ");
                }
                System.out.println(")");
     //************************printing neighbour list for cycle 1 to the file*****************************************************************       
                
                objFile2.print("Neighbour List of node "+f+"   [");
                for(int i=0;i<k;i++)
                {
                	objFile2.print(" "+objName[f].neighbourList[i]+" ");
                }
                objFile2.println(" ]");
        
        //****************************************printing the sum for the first iteration*******************************
        for(int i=0;i<k;i++)
        {
      	  double fdistance=0;  
      	  if(structure =='B')
      		  fdistance=distcalcB(objName[f].x, objName[f].y, objName[objName[f].neighbourList[i]].x, objName[objName[f].neighbourList[i]].y, f, objName[f].neighbourList[i], numNode);
      	  if(structure =='S')
      		  fdistance=distcalcS(objName[f].x, objName[f].y, objName[objName[f].neighbourList[i]].x, objName[objName[f].neighbourList[i]].y, f, objName[f].neighbourList[i], numNode);
            if(fdistance==Double.MAX_VALUE)
            {
          	  fdistance =0;
            }
            sum= sum+fdistance;
        }
    }
        fsum[0]=sum;
        
        System.out.println(" final sum" +sum);
        objFile1.println("final sum : " +sum);  // writing the sum of distance of the initialization phase to the output file
        
        
       
        
        
        
        
         //******************************************end of neighbour list initialization******************************************************************
        
        
        
        //*****************************************Start of evolution phase*******************************************************************************
	for(int h=0;h<40;h++)
	{
		sum=0;
	for(int f=1;f<=numNode;f++)
	{
		int randp = rand.nextInt(k);  //choosing random position from k neighbours
		int sendNode=objName[f].neighbourList[randp];
		int [] arr1= new int[k];
		int [] arr2 = new int[k];
		int [] arr3= new int[2*k];
		int [] arr4 =new int[2*k];
		for(int i=0;i<k;i++)
		{
			arr1[i]=objName[f].neighbourList[i];  //putting own node neighbour list in arr1
		}
		arr1[randp]=f;
		for(int i=0;i<k;i++) /// array 2 has value of neighbours of SendNode and replacing the value of f with sendNode
		{
			int self=objName[sendNode].neighbourList[i];
			if(self==f) {
				self=sendNode;
			}
			arr2[i]=self;
		}
		for(int i=0;i<k;i++)   // merging of own neighbour list and final neighbour list of sendNode
		{
			arr3[i]=objName[f].neighbourList[i];
			arr3[i+k]=arr2[i];
		}
		//**************************************printing arr3 ie initial mergerd list on the own side***************************
//		System.out.print("own id "+f);
//		System.out.print("  SendNode "+ sendNode);
//		System.out.print("(");
//		for(int i=0;i<2*k;i++)
//		{
//			System.out.print(arr3[i]+"  ");
//		}
//		System.out.println(")");
		
		for(int i=0;i<k;i++)
		{
			arr4[i]=objName[sendNode].neighbourList[i];
			arr4[i+k]=arr1[i];
		}
		//**************************************printing arr4 ie initial mergerd list on the other side***************************
//	    System.out.print("sendNode "+sendNode);
//		System.out.print("  own id "+ f);
//		System.out.print("(");
//		for(int i=0;i<2*k;i++)
//		{
//			System.out.print(arr4[i]+"  ");
//		}
//		System.out.println(")");
		
		//*******************************removing duplicates ****************************************************
        int flag1=0;
        int flag2=0;
		for(int i=0;i<2*k;i++)   //removing duplicates from array 3
        	{
       	 	int x1=arr3[i];
       	 	for(int j=0;j<2*k;j++)
       	 		{
       	 			if((x1==arr3[j] && i!=j))
       	 			{
							arr3[j]=0;
       	 			}
       	 			
       	 		}
       	 	
        	}
        for(int i=0;i<2*k;i++)     //removing duplicates from array 4
    	{
   	 	int x2=arr4[i];
   	 	for(int j=0;j<2*k;j++)
   	 		{
   	 			if((x2==arr4[j] && i!=j))
   	 			{
						arr4[j]=0;
   	 			}
   	 		}
   	 	
    	}
        for(int i=0;i<2*k;i++)
        {
        	if(arr3[i]==0)
        		flag1++;
        	if(arr4[i]==0)
        		flag2++;
        }
        //**************************************************************************************************************************
//        System.out.print("array 3 without duplicates");
//      System.out.print("( ");
//      for(int i=0;i<2*k;i++)
//         {
//          	System.out.print(arr3[i]+"  ");
//          
//         }
//      System.out.println(")");
//      
//      System.out.print("array 4 without duplicates");
//      System.out.print("( ");
//      for(int i=0;i<2*k;i++)
//         {
//          	System.out.print(arr4[i]+"  ");
//          
//         }
//      System.out.println(")");
      
      //**********************************************************removing all the zeros from the arrays*************************************
      int [] arr5= new int[(2*k)-flag1];
      int [] arr6= new int[(2*k)-flag2];
      int s1=0;
      int s2=0;
      for(int i=0;i<2*k;i++)
      {
    	  if(arr3[i]==0)
    		  continue;
    	  
    	  arr5[s1]=arr3[i];
    	  s1++;
    	  
      }
      for(int i=0;i<2*k;i++)
      {
    	  if(arr4[i]==0)
    		  continue;
    	  
    	  arr6[s2]=arr4[i];
    	  s2++;
    	  
      }
      //*************************************************printing after removing all zeroes**************************************************
//      System.out.print("array 5 ");
//      System.out.print("( ");
//      for(int i=0;i<arr5.length;i++)
//         {
//          	System.out.print(arr5[i]+"  ");
//          
//         }
//      System.out.println(")");
//      
//      System.out.print("array 6");
//      System.out.print("( ");
//      for(int i=0;i<arr6.length;i++)
//         {
//          	System.out.print(arr6[i]+"  ");
//          
//         }
//      System.out.println(")");
		
		//*************************************calculating distance*************************************************************
      double [] dist5 = new double[arr5.length];
      double [] dist6 = new double[arr6.length];
      
      for(int i=0; i<arr5.length;i++)
      {   
    	  double distance=0;
    	  if(structure =='B')
    		  distance = distcalcB(objName[f].x, objName[f].y, objName[arr5[i]].x ,objName[arr5[i]].y, f, arr5[i], numNode);
    	  if(structure=='S')
    		  distance = distcalcS(objName[f].x, objName[f].y, objName[arr5[i]].x ,objName[arr5[i]].y, f, arr5[i], numNode);
    	  dist5[i]=distance;
      }
      
      for(int i=0; i<arr6.length;i++)
      {
    	  double distance =0;
    	  if(structure =='B')
    		  distance = distcalcB(objName[sendNode].x, objName[sendNode].y, objName[arr6[i]].x ,objName[arr6[i]].y, sendNode, arr6[i], numNode);
    	  if(structure =='S')
    		  distance = distcalcS(objName[sendNode].x, objName[sendNode].y, objName[arr6[i]].x ,objName[arr6[i]].y, sendNode, arr6[i], numNode);
    	  dist6[i]=distance;
      }
      //*************************************displaying the distance*****************************************************
//      System.out.print("dist 5 ");
//      System.out.print("( ");
//      for(int i=0;i<arr5.length;i++)
//       	{
//    	  System.out.print(dist5[i]+"  ");
//        
//       	}
//      System.out.println(")");
//    
//      System.out.print("dist 6");
//      System.out.print("( ");
//      for(int i=0;i<arr6.length;i++)
//       	{
//    	  System.out.print(dist6[i]+"  ");
//        
//       	}
//      System.out.println(")");
      //***********************************swapping the neighbour list to get in ascending order of distance***********************
		
      for(int i=0;i<dist5.length;i++)
      {
     	 for(int j=i+1;j<dist5.length;j++)
     	 {
     		 if(dist5[i]>dist5[j])
     		 {
     			 double temp1=0;
     			 int temp2=0;
     			 temp1=dist5[j];
     			 dist5[j]=dist5[i];
     			 dist5[i]=temp1;
     			 
     			 temp2=arr5[j];
     			 arr5[j]=arr5[i];
     			 arr5[i]=temp2;
     		 }
     	 }
      }
      
      for(int i=0;i<dist6.length;i++)
      {
     	 for(int j=i+1;j<dist6.length;j++)
     	 {
     		 if(dist6[i]>dist6[j])
     		 {
     			 double temp1=0;
     			 int temp2=0;
     			 temp1=dist6[j];
     			 dist6[j]=dist6[i];
     			 dist6[i]=temp1;
     			 
     			 temp2=arr6[j];
     			 arr6[j]=arr6[i];
     			 arr6[i]=temp2;
     		 }
     	 }
      }
     //***************************************************printing the dstance in ascending order********************************************* 
//      System.out.print("dist 5 ");
//      System.out.print("( ");
//      for(int i=0;i<arr5.length;i++)
//       	{
//    	  System.out.print(dist5[i]+"  ");
//        
//       	}
//      System.out.println(")");
//    
//      System.out.print("dist 6");
//      System.out.print("( ");
//      for(int i=0;i<arr6.length;i++)
//       	{
//    	  System.out.print(dist6[i]+"  ");
//        
//       	}
//      System.out.println(")");
      
      //*************************************************selecting k neighbours****************************************************
      int [] final1 = new int[k];
      int [] final2 = new int[k];
      for(int i=0;i<k;i++)
      {
    	  final1[i]=arr5[i];
    	  final2[i]=arr6[i];
      }
	//*************************************************printing final neighbours***************************************************
//      System.out.print("(");
//      for(int i=0;i<k;i++)
//      {
//    	  System.out.print(" "+final1[i]);
//      }
//      System.out.println(")");
//      
//      System.out.print("(");
//      for(int i=0;i<k;i++)
//      {
//    	  System.out.print(" "+final2[i]);
//      }
//      System.out.println(")");
	//*****************************************************putting in the object now********************************************
      for(int i=0;i<k;i++)
      {
    	  objName[f].neighbourList[i]=final1[i];
    	  objName[sendNode].neighbourList[i]=final2[i];
      }
      for(int i=0;i<k;i++)
      {
    	  double fdistance=0;  
    	  if(structure =='B')
    		  fdistance=distcalcB(objName[f].x, objName[f].y, objName[objName[f].neighbourList[i]].x, objName[objName[f].neighbourList[i]].y, f, objName[f].neighbourList[i], numNode);
    	  if(structure =='S')
    		  fdistance=distcalcS(objName[f].x, objName[f].y, objName[objName[f].neighbourList[i]].x, objName[objName[f].neighbourList[i]].y, f, objName[f].neighbourList[i], numNode);
          if(fdistance==Double.MAX_VALUE)
          {
        	  fdistance =0;
          }
          sum= sum+fdistance;
      }
      
      
    //**************************************storing the neigbour list in a text file***********************************************
  	if(h==5)
  	{
  		objFile3.print("Neighbour List of node "+f+"   [");
          for(int i=0;i<k;i++)
          {
          	objFile3.print(" "+objName[f].neighbourList[i]+" ");
          }
          objFile3.println(" ]");
  	}
  	
  	if(h==10)
  	{
  		objFile4.print("Neighbour List of node "+f+"   [");
          for(int i=0;i<k;i++)
          {
          	objFile4.print(" "+objName[f].neighbourList[i]+" ");
          }
          objFile4.println(" ]");
  	}
  	
  	if(h==15)
  	{
  		objFile5.print("Neighbour List of node "+f+"   [");
          for(int i=0;i<k;i++)
          {
          	objFile5.print(" "+objName[f].neighbourList[i]+" ");
          }
          objFile5.println(" ]");
  	}
      
	
 }
	
	fsum[h+1]=sum;
	System.out.println(" final sum" +sum);
	objFile1.println("final sum : "+sum);
	
	
	//***********************************************Code for plotting after the cycles**************************
	if(h==0 || h==4 || h==9 || h==14 )
	{
		final XYSeries plot1 = new XYSeries( "Plot",false,true);

		if(structure=='B') {
			plot1.add(objName[1].x,(1+objName[numNode-1].y) );
		}
	      for(int i=1;i<numNode;i++)
			{
				for(int j =0;j<k;j++)
				{
					plot1.add(objName[i].x,objName[i].y);
					plot1.add(objName[objName[i].neighbourList[j]].x,objName[objName[i].neighbourList[j]].y);
					
			    }
			}
	      
	      
	      final XYSeriesCollection data_set = new XYSeriesCollection( );
	      data_set.addSeries( plot1 );

	      JFreeChart xylineChart = ChartFactory.createXYLineChart(
	         "Plot for the topology",
	         "X",
	         "Y",
	         data_set,
	         PlotOrientation.VERTICAL, 
	         true, true, false);
	      
	      int width = 720;   /* Width of the image */
	      int height = 480;  /* Height of the image */
	      String img1 = (structure+"_N"+Integer.toString(numNode)+"_k"+Integer.toString(k)+"_"+(h+1)+".jpeg");
	      File Plot = new File( img1 ); 
	      ChartUtilities.saveChartAsJPEG( Plot, xylineChart, width, height);
	
	}
	
	
	
	
}
	objFile1.close();
	objFile2.close();
	objFile3.close();
	objFile4.close();
	objFile5.close();
	

	
	//************************************code for plotting the x-y graph***********************
	
	LineChart_AWT chart = new LineChart_AWT("Cycles vs Sum of Distance" ,"Cycles vs Sum of Distance",fsum);
		  chart.pack( );
	      RefineryUtilities.centerFrameOnScreen( chart );
	      chart.setVisible( true );
	
		
	
	}
		
}
