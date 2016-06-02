//
//  Rasterizer.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Swetha Kannan Iyer
//

///
// 
// This is a class that performas rasterization algorithms
//
///

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Rasterizer {

	// /
	
	int n_scanlines, dx1, dy1;


	// /
	// Constructor
	//
	// @param n - number of scanlines
	//
	// /

	Rasterizer(int n) {
		n_scanlines = n;

	}

	// /
	// Draw a filled polygon in the simpleCanvas C.
	//
	// The polygon has n distinct vertices. The
	// coordinates of the vertices making up the polygon are stored in the
	// x and y arrays. The ith vertex will have coordinate (x[i], y[i])
	//
	// You are to add the implementation here using only calls
	// to C.setPixel()
	// /

	
	public void drawPolygon(int n, float x[], float y[], simpleCanvas C) {
		
		
		
		
		int count=0;
		int count2;
		int count3;
		int count4;
		int count5;
		float[][] ET = new float[n][4];
		
		int j = 1;
		float ymin, xval, dy, dx;
		float slope;
		float ymaxvalue=y[0];
		
		for(int i=1;i<n;i++){//find the maximum y value
			if(y[i]>ymaxvalue){
				ymaxvalue=y[i];
			}
		}
		
		for (int i = 0; i < n; i++) {//Edge Table with ymin,ymax,xvalue and 1/m as coloumns
			if (i == n - 1) {
				j = 0;
			}
			if (y[i] < y[j]) {
				ET[i][0] =  y[i];
				ET[i][1] =  y[j];
				xval =  x[i];
			} else {
				ET[i][0] =  y[j];
				ET[i][1] =  y[i];
				xval =  x[j];
			}
			ET[i][2] = xval;
			if (i != n - 1) {
				
				dy = Float.parseFloat(String.format("%.3f",y[i + 1]))-Float.parseFloat(String.format("%.3f",y[i])); //(y[i + 1] - y[i]);
				dx =  (x[i + 1] - x[i]);
				
			} else {
				dy =  (y[0] - y[i]);
				dx =  (x[0] - x[i]);
				
			}
			
			if (dy != 0) {
				slope =  (float)dx / (float)dy;
				
				ET[i][3] =  slope;
			} else{
				slope = (float)50;//if dy is 0,assign random slope
				ET[i][3] =  slope;
				
			}
       j++;
		}
    
		
		for(int i=0;i<n;i++){
			
			if(((float)(1)/(float)(ET[i][3]))!=(float)(1)/(float)(50)){
				
				count=count+1;
				
			}
		}
		
		int b=0;
		float[][] GT = new float[count][4];
		for(int i=0;i<ET.length;i++){
			
			if(((float)(1)/(float)(ET[i][3]))!=(float)(1)/(float)(50)){//making the global table GT with all the rows 
			                                                            //from ET excluding the one with dy=0
				GT[b][0]=( ET[i][0]);
				GT[b][1]=( ET[i][1]);
				GT[b][2]= (ET[i][2]);
				GT[b][3] =(ET[i][3]);
				b++;
			}
			else{
			
			}
		}

		for (int i = 0; i < GT.length; i++) {//sorting the GT table as per ymax first,if ymax is equal,
			                                  //sort as per xvalue,if xvalue is equal,den as per ymin
			for(int k=1;k<GT.length-i;k++){
				
						 if((GT[k][1])<(GT[k-1][1])){//0
							
							 float temp=GT[k][0];
							 GT[k][0]=GT[k-1][0];
							 GT[k-1][0]=temp;
							
							 float temp1=GT[k][1];
							 GT[k][1]=GT[k-1][1];
							 GT[k-1][1]=temp1;
							 float temp2=GT[k][2];
							 GT[k][2]=GT[k-1][2];
							 GT[k-1][2]=temp2;
							 float temp3=GT[k][3];
							 GT[k][3]=GT[k-1][3];
							 GT[k-1][3]=temp3;
						 }
						 else
							 if((GT[k][0]==GT[k-1][0])&&(GT[k][2]<GT[k-1][2])){
								 float temp=GT[k][0];
								 GT[k][0]=GT[k-1][0];
								 GT[k-1][0]=temp;
								 float temp1=GT[k][1];
								 GT[k][1]=GT[k-1][1];
								 GT[k-1][1]=temp1;
								 float temp2=GT[k][2];
								 GT[k][2]=GT[k-1][2];
								 GT[k-1][2]=temp2;
								 float temp3=GT[k][3];
								 GT[k][3]=GT[k-1][3];
								 GT[k-1][3]=temp3;
							 }
							 else
								 if(((GT[k][2]==GT[k-1][2])&&(GT[k][1]<GT[k-1][1]))){
									 float temp=GT[k][0];
									 GT[k][0]=GT[k-1][0];
									 GT[k-1][0]=temp;
									 float temp1=GT[k][1];
									 GT[k][1]=GT[k-1][1];
									 GT[k-1][1]=temp1;
									 float temp2=GT[k][2];
									 GT[k][2]=GT[k-1][2];
									 GT[k-1][2]=temp2;
									 float temp3=GT[k][3];
									 GT[k][3]=GT[k-1][3];
									 GT[k-1][3]=temp3;
								 }

				}
				
			}
		
//		
		for(int i=0;i<GT.length;i++){
			
			if(GT[i][0]==0&&GT[i][1]==0&&GT[i][2]==0&&GT[i][3]==0){//removing the redundant row of 0's
				for(int h=i+1;h<count;h++){
					GT[h-1][0]=GT[h][0];
					GT[h-1][1]=GT[h][1];
					GT[h-1][2]=GT[h][2];
					GT[h-1][3]=GT[h][3];
				}
			}
		}
		
		float yvalue=GT[0][0];
		float[][] AL = new float[count][3];
		for(int i=1;i<count;i++){
		
		if (GT[i][0]<yvalue){//finding the minimum y value to start the scanning
			
			yvalue=GT[i][0];
			
		}
		}
		int ctr=0;
		
		for(int i=0;i<count;i++){//i<count
			                      
			                         
			if((int)GT[i][0]==(int)yvalue){
				AL[ctr][0]=GT[i][1];
				AL[ctr][1]=GT[i][2];
				AL[ctr][2]=GT[i][3];
				ctr++;
			}
		}

		int count1=0;
		
		
		for(int i=0;i<AL.length;i++){
			if(AL[i][0]!=0){
				
				count1++;
			}
		}
		
		int a=0;
		
		
		int t=0;
		for(float y0=yvalue,x0=AL[a][1];x0<=AL[count1-1][1];x0++){//draw the first scanline
			
			 if((int)x0>=(int)AL[t][1]&&(int)x0<=(int)AL[t+1][1]){	
				
	    	      C.setPixel((int)x0,(int)y0);
	    	  }
	    	  else
	    		
	   		  if((t+2)!=AL.length&&(t+3)!=AL.length){
	    		  
	    		  if((int)x0>=(int)AL[t+2][1]&&(int)x0<=(int)AL[t+3][1]){
	    			  C.setPixel((int)x0,(int)y0);
	    		  }
	   		  }
	   		  else
	   			 if((t+4)!=AL.length&&(t+5)!=AL.length){
	       		  
	       		  if((int)x0>=(int)AL[t+4][1]&&(int)x0<=(int)AL[t+5][1]){
	       			  C.setPixel((int)x0,(int)y0);
	       		  }
	      		  }
			
		}
		while(yvalue<ymaxvalue){
		
		for(int i=0;i<AL.length;i++){//for each scanline calculate the xvalue=xvalue+(1/m)
			AL[i][1]=AL[i][1]+AL[i][2];
		}
		int alcount=0;
		for(int i=0;i<AL.length;i++){//removing redundant row of 0's
		
			if(AL[i][0]==0){
				
			}
			else{
				alcount=alcount+1;
			}
			
			}
		
		float newAL[][]=new float[alcount][3];
		int m=0;
		for(int i=0;i<AL.length;i++){
			if(AL[i][0]!=0){
				newAL[m][0]=AL[i][0];
				newAL[m][1]=AL[i][1];
				newAL[m][2]=AL[i][2];
				m++;
			}
		}
		AL=newAL;
		
       for (int i = 0; i <= AL.length-1; i++) {//sorting AL as per xvalue
    	   
			for(int k=1;k<AL.length-i;k++){
				
				if(AL[k][1]<AL[k-1][1]){
					
					float temporary=AL[k][1];
					AL[k][1]=AL[k-1][1];
					AL[k-1][1]=temporary;
					float temporary1=AL[k][0];
					AL[k][0]=AL[k-1][0];
					AL[k-1][0]=temporary1;
					float temporary2=AL[k][2];
					AL[k][2]=AL[k-1][2];
					AL[k-1][2]=temporary2;
			}
       }
			}
     
       
    	   yvalue=yvalue+1;
    	  
    	   int p=0;
    	      float [][] AL1 = new float[AL.length][3];
    	      for(int i=0;i<AL.length;i++){
    	   	   if((int)AL[i][0]!=(int)yvalue){//if the AL table has any edge which has ymax=current scanline y,delete from AL
    	   		   
    	   		
    	   		   AL1[p][0]=(AL[i][0]);
    	   		   AL1[p][1]=(AL[i][1]);
    	   		   AL1[p][2]=(AL[i][2]);
    	   		   p++;
    	   	   }
    	   	  
    	      }
    	  AL=AL1;
        count2=0;
   			
       count3=0;
      for(int i=0;i<AL.length;i++){
   		
          if(AL[i][0]==0&& AL[i][1]==0&& AL[i][2]==0){//removing redundant row of 0's
        	  
          }
          else{
        	  count3=count3+1;
          }
   			}
     
      float [][] AL2 = new float[count3][3];
      
      for(int i=0;i<count3;i++){
    	 
    	  AL2[i][0]=AL[i][0];
    	  AL2[i][1]=AL[i][1];
    	  AL2[i][2]=AL[i][2];
      }
      AL=AL2;
    
      count4=0;
      count5=0;
      for(int i=0;i<GT.length;i++){
    	
    	  if((int)GT[i][0]==(int)yvalue){
    		  
    		  count4=count4+1;
    		
    	  }
      }
     
     
    	  count5=count4+AL.length;
    	
      float[][]S=new float[count5][3];//count5
      for(int i=0;i<AL.length;i++){
    	 S[i][0] =AL[i][0];
    	 S[i][1] =AL[i][1];
    	 S[i][2] =AL[i][2];
      }
      //
     //added line
      for(int i=0;i<GT.length;i++){//count
    	 
    	  if((int)GT[i][0]==(int)yvalue){//if the current scanline y is equal to any of the ymin of the GT table,add to AL
    		 
    		  S[count3][0]=GT[i][1];
    		  S[count3][1]=GT[i][2];
    		  S[count3][2]=GT[i][3];
    		  count3++;
    	  }
    	  
      }
      AL=S;      
   
      for (int i = 0; i < AL.length; i++) {//sort AL
			
			for(int k=1;k<=AL.length-1-i;k++){
				
				if((int)AL[k][1]<(int)AL[k-1][1]){
					
					float temporary=AL[k][1];
					AL[k][1]=AL[k-1][1];
					AL[k-1][1]=temporary;
					float temporary1=AL[k][0];
					AL[k][0]=AL[k-1][0];
					AL[k-1][0]=temporary1;
					float temporary2=AL[k][2];
					AL[k][2]=AL[k-1][2];
					AL[k-1][2]=temporary2;
			}
     }
      }
    
      if((int)yvalue!=(int)ymaxvalue&&(int)yvalue<(int)ymaxvalue){
    	
    	  int v=0;
    	  int counter=0;
    	 
      for(float y1=yvalue,x1=AL[v][1];x1<=AL[AL.length-1][1];x1++){	
    	 
    	  
    	
    	  if(x1>=AL[v][1]&&x1<=AL[v+1][1]){
    		  
    	      C.setPixel((int)x1,(int)y1);
    	  }
    	  else
    		
   		  if((v+2)!=AL.length&&(v+3)!=AL.length){
    		  
    		  if(x1>=AL[v+2][1]&&x1<=AL[v+3][1]){
    			  C.setPixel((int)x1,(int)y1);
    		  }
   		  }
   		  else
   			 if((v+4)!=AL.length&&(v+5)!=AL.length){
       		  
       		  if(x1>=AL[v+4][1]&&x1<=AL[v+5][1]){
       			  C.setPixel((int)x1,(int)y1);
       		  }
      		  }
    	  counter++;
    	  
      }
      }
    
     
		}
       }
}
	


