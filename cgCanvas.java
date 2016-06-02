//

//  cgCanvas.java 20155
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor: Swetha Kannan Iyer
//

///
// This is a simple canvas class for adding functionality for the
// 2D portion of Computer Graphics.
//
///

import Jama.*;
import java.util.*;

public class cgCanvas extends simpleCanvas {
	
float storepolyvertx[]=new float[200];
float storepolyverty[]=new float[200];
float storepolyvert1x[]=new float[200];
float storepolyvert1y[]=new float[200];
float basematrix[][]=new float[3][3];
float viewmatrix[][]=new float[3][3];
float []wx=new float[200];
float []wy=new float[200];
float CW[][]=new float[3][3];
float send[][]=new float[3][1];
float clearM[][]=new float[3][3];
float clearM1[][]=new float[3][3];
float scale[][]=new float[3][3];
float scale1[][]=new float[3][3];
float send1[][]=new float[3][3];
float sending[][]=new float[3][3];
float sending1[][]=new float[3][3];
float RM[][]=new float[3][3];
float RM1[][]=new float[3][3];
float TM[][]=new float[3][3];
Rasterizer r=new Rasterizer(500);
clipper Cl=new clipper();
int side[]=new int[500];
int clip=0;
int a=0;
int j=0;
int s=0;
int sub=0;
int from=0;
int to=0;
    ///
    // Constructor
    //
    // @param w width of canvas
    // @param h height of canvas
    ///

    cgCanvas (int w, int h)
    {
        super (w, h);
        for(int k=0;k<3;k++){
    		for(int l=0;l<3;l++){
    			if(k==l){
    				clearM[k][l]=1;
    				
    			}
    			else{
    				clearM[k][l]=0;
    				
    			}
    		}
    	}
        
        // YOUR IMPLEMENTATION HERE if you need to modify the constructor
    }

    ///
    // addPoly - Adds and stores a polygon to the canvas.  Note that this
    //           method does not draw the polygon, but merely stores it for
    //           later draw.  Drawing is initiated by the draw() method.
    //
    //           Returns a unique integer id for the polygon.
    //
    // @param x - Array of x coords of the vertices of the polygon to be added
    // @param y - Array of y coords of the vertices of the polygon to be added
    // @param n - Number of verticies in polygon
    //
    // @return a unique integer identifier for the polygon
    ///

    public int addPoly (float x[], float y[], int n)
    {
        
    	
        j=0;
  
    		while(j!=n){
    			
    		storepolyvertx[a]=x[j]; //stores all the values in storepolyvertx and storepolyverty
    		storepolyverty[a]=y[j];
    	
    		s=a;
    		j=j+1;
    		a=a+1; 
    		}
    		
		side[s]=n;
    	
        return s;
    }

    ///
    // drawPoly - Draw the polygon with the given id.  Should draw the
    //        polygon after applying the current transformation on the
    //        vertices of the polygon.
    //
    // @param polyID - the ID of the polygon to be drawn
    ///

    public void drawPoly (int polyID)
   {
    	
   
        int n;
    	for(int i=0;i<storepolyvert1x.length;i++){
    		storepolyvert1x[i]=0;
    		storepolyvert1y[i]=0;
    	}
    	for(int i=0;i<3;i++){
    		sending[i][0]=0;
    		sending1[i][0]=0;
    		send[i][0]=0;
    		send1[i][0]=0;
        	 
        	}
    	for(int i=0;i<wx.length;i++){
    		wx[i]=0;
    		wy[i]=0;
    	}
 
    		n=side[polyID];
    		
    		from=0;
    		to=0;
    		for(int h=from;h<=polyID;h++){             //retrieves the vertices of the given polygon with the help of id 
    			storepolyvert1x[to]=storepolyvertx[h];
    			storepolyvert1y[to]=storepolyverty[h];
    			to++;
    		}
    	 from=(polyID-n)+1;
    	 to=0;
    	 for(int h=0;h<storepolyvert1x.length;h++){
    		 storepolyvert1x[h]=0;
    		 storepolyvert1y[h]=0;
    	 }
    	 for(int m=from;m<=polyID;m++){
    		 storepolyvert1x[to]=storepolyvertx[m];
 			storepolyvert1y[to]=storepolyverty[m];
 			to++;
    	 }
    	
    	
    	for(int g=0;g<n;g++){              //multiplies  the transformation matrix with each vertex 
    		send[0][0]=storepolyvert1x[g];
			send[1][0]=storepolyvert1y[g];
			send[2][0]=1;
    	for(int i=0;i<3;i++){
    		for(int j=0;j<1;j++){
    			for(int k=0;k<3;k++){
    				
    				send1[i][j]=(clearM[i][k]*send[k][j])+send1[i][j];
    			}
    		}
    	}
    	
    	storepolyvert1x[g]=send1[0][0];
    	storepolyvert1y[g]=send1[1][0];
    	for(int i=0;i<3;i++){
    		send1[i][0]=0;
        	  
        	}
    	}
    	for(int i=0;i<storepolyvert1x.length;i++){
    		if(storepolyvert1x[i]!=0){
      	
    		}
      	}
    	
    	for(int i=0;i<3;i++){
    		send[i][0]=0;
    		send1[i][0]=0;
        	  
        	}
    	for(int g=0;g<n;g++){             //multiplies the clipwindow with each vertex 
    		send[0][0]=storepolyvert1x[g];
			send[1][0]=storepolyvert1y[g];
			send[2][0]=1;
			for(int v=0;v<3;v++){
			
			}
    	for(int i=0;i<3;i++){
    		for(int j=0;j<1;j++){
    			for(int k=0;k<3;k++){
    				
    				send1[i][j]=(CW[i][k]*send[k][j])+send1[i][j];
    			}
    		}
    	}
    	
    	storepolyvert1x[g]=send1[0][0];
    	storepolyvert1y[g]=send1[1][0];
    	for(int i=0;i<3;i++){
    		send1[i][0]=0;
        	 
        	}
    	}
    	for(int i=0;i<storepolyvert1x.length;i++){
    		if(storepolyvert1x[i]!=0){
      	
    		}
      	}
    	clip=Cl.clipPolygon( n,storepolyvert1x,storepolyvert1y, wx, wy,-1,-1,1,1 );
    	
    	
    	for(int j=0;j<clip;j++){
    		
    	}
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    
    		}
    	}
    	for(int i=0;i<3;i++){
    		sending[i][0]=0;
    		sending1[i][0]=0;
        	 
        	}
    	
    	for(int g=0;g<clip;g++){//multiplies the viewmatrix with each vertex of the clip window output
    		sending[0][0]=wx[g];
			sending[1][0]=wy[g];
			sending[2][0]=1;
			
    	for(int i=0;i<3;i++){
    		for(int j=0;j<1;j++){
    			for(int k=0;k<3;k++){
    				
    				sending1[i][j]=(viewmatrix[i][k]*sending[k][j])+sending1[i][j];
    		
    			}
    			
    		}
    	}
    	
    	storepolyvert1x[g]=sending1[0][0];
    	storepolyvert1y[g]=sending1[1][0];
    	
    	for(int i=0;i<3;i++){
    		sending1[i][0]=0;
        	}
    	}
    	
    	
    	r.drawPolygon(clip, storepolyvert1x,storepolyvert1y,this);
    	
    
    }

    ///
    // clearTransform - Set the current transformation to the identity matrix.
    ///

    public void clearTransform()
    {
        // YOUR IMPLEMENTATION HERE
    	
    	for(int k=0;k<3;k++){
    		for(int l=0;l<3;l++){
    			if(k==l){
    				clearM[k][l]=1;
    				
    			}
    			else{
    				clearM[k][l]=0;
    				
    			}
    		}
    	}
    	
    }

    ///
    // translate - Add a translation to the current transformation by
    //             pre-multiplying the appropriate translation matrix to
    //             the current transformation matrix.
    //
    // @param x - Amount of translation in x
    // @param y - Amount of translation in y
    ///

    public void translate (float x, float y)
    {
        // YOUR IMPLEMENTATION HERE
    	
    	for(int k=0;k<3;k++){
    		for(int l=0;l<3;l++){
    			if(k==l){
    				TM[k][l]=1;
    				
    			}
    			else{
    				TM[k][l]=0;
    				
    			}
    		}
    	}
    	TM[0][2]=x;
    	TM[1][2]=y;
    	for(int i=0;i<3;i++){
    		
    		
    		clearM1[i][0]=0;
    		clearM1[i][1]=0;
    		clearM1[i][2]=0;
    		
    	}
    	
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			for(int k=0;k<3;k++){
    				clearM1[i][j]=(TM[i][k]*clearM[k][j])+clearM1[i][j];
    			}
    		}
    	}
    	
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			clearM[i][j]=clearM1[i][j];
    		}
    	}
    	
    }

    ///
    // rotate - Add a rotation to the current transformation by
    //          pre-multiplying the appropriate rotation matrix to the
    //          current transformation matrix.
    //
    // @param degrees - Amount of rotation in degrees
    ///

    public void rotate (float degrees)
    {
        // YOUR IMPLEMENTATION HERE
    	
    	double radians = Math.toRadians(degrees);//convert from degrees to radians

    	RM[0][0]=RM[1][1]= (float) Math.cos(radians);
    	RM[2][2]=1;
    	RM[0][1]=(float) -( Math.sin(radians));
    	RM[1][0]=(float) Math.sin(radians);
    	for(int i=0;i<3;i++){
    		
    		RM1[i][0]=0;
    		RM1[i][1]=0;
    		RM1[i][2]=0;
    	}
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			for(int k=0;k<3;k++){
    				RM1[i][j]=(RM[i][k]*clearM[k][j])+RM1[i][j];
    			}
    		}
    	}
    	
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			clearM[i][j]=RM1[i][j];
    		}
    	}
    	
    		}
    

    ///
    // scale - Add a scale to the current transformation by pre-multiplying
    //         the appropriate scaling matrix to the current transformation
    //         matrix.
    //
    // @param x - Amount of scaling in x
    // @param y - Amount of scaling in y
    ///

    public void scale (float x, float y)
    {
        // YOUR IMPLEMENTATION HERE
    	scale[0][0]=x;
    	scale[1][1]=y;
    	scale[2][2]=1;
    	for(int i=0;i<3;i++){
    		
    		scale1[i][0]=0;
    		scale1[i][1]=0;
    		scale1[i][2]=0;
    	}
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			for(int k=0;k<3;k++){
    				scale1[i][j]=(scale[i][k]*clearM[k][j])+scale1[i][j];
    			}
    		}
    	}
    	
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			clearM[i][j]=scale1[i][j];
    		}
    	}
    }

    ///
    // setClipWindow - defines the clip window
    //
    // @param bottom - y coord of bottom edge of clip window (in world coords)
    // @param top - y coord of top edge of clip window (in world coords)
    // @param left - x coord of left edge of clip window (in world coords)
    // @param right - x coord of right edge of clip window (in world coords)
    ///

    public void setClipWindow (float bottom, float top, float left, float right)
    {
    	
        // YOUR IMPLEMENTATION HERE
    	CW[0][0]=2/(right-left);
    	float temp=(-2*left)/(right-left);
    	
    	CW[0][2]=temp-(float)1;
    	CW[1][1]=2/(top-bottom);
    	CW[1][2]=(((-2*bottom)/(top-bottom))-1);
    	CW[2][2]=1;
    	
    	
    	
    }

    ///
    // setViewport - defines the viewport
    //
    // @param xmin - x coord of lower left of view window (in screen coords)
    // @param ymin - y coord of lower left of view window (in screen coords)
    // @param width - width of view window (in world coords)
    // @param height - width of view window (in world coords)
    ///

    public void setViewport (int x, int y, int width, int height)
    {
        // YOUR IMPLEMENTATION HERE
    	int xvmax=x+width;
    	int xvmin=x;
    	int yvmax=y+height;
    	int yvmin=y;
    	viewmatrix[0][0]=(xvmax-xvmin)/2;
    	viewmatrix[0][2]=(xvmax+xvmin)/2;
    	viewmatrix[1][1]=(yvmax-yvmin)/2;
    	viewmatrix[1][2]=(yvmax+yvmin)/2;
    	viewmatrix[2][2]=1;
    	
    	
    }

}
