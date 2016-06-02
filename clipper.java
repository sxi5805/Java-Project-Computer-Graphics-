//
//  Clipper.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:Swetha Kannan Iyer
//

///
// Object for performing clipping
//
///

public class clipper {
    int outLength=0;
    float px=0;
    float py=0;
    float sx=0;
    float sy=0;
    int count=0;
  
    ///
    // inside below
    //The routine checks  whether the given vertex is above the below clipping line
    // @param x - x coords of vertices of polygon 
    // @param y - y coords of vertices of polygon 
    // @param llx - x coord of lower left of clipping rectangle.
    // @param lly - y coord of lower left of clipping rectangle.
    // @param urx - x coord of upper right of clipping rectangle.
    // @param ury - y coord of upper right of clipping rectangle.
    //
    // @return a boolean value 
    //
    ///
	public boolean insidebelow(float x,float y,float llx,float lly,float urx,float ury){//check whether the point is above the below line
	
		if(y>=lly){
			
			return true;
		}
		return false;
	}
	 ///
    // inside right
    //The routine checks  whether the given vertex is left of the right clipping line
    // @param x - x coords of vertices of polygon 
    // @param y - y coords of vertices of polygon 
    // @param llx - x coord of lower left of clipping rectangle.
    // @param lly - y coord of lower left of clipping rectangle.
    // @param urx - x coord of upper right of clipping rectangle.
    // @param ury - y coord of upper right of clipping rectangle.
    //
    // @return a boolean value 
    //
    ///
	public boolean insideright(float x,float y,float llx,float lly,float urx,float ury){//check whether the point is inside the right line
		
			if(x<=urx){
				
				return true;
			}
			return false;
		}
	 ///
    // inside up
    //The routine checks  whether the given vertex is below the above clipping line 
    // @param x - x coords of vertices of polygon 
    // @param y - y coords of vertices of polygon 
    // @param llx - x coord of lower left of clipping rectangle.
    // @param lly - y coord of lower left of clipping rectangle.
    // @param urx - x coord of upper right of clipping rectangle.
    // @param ury - y coord of upper right of clipping rectangle.
    //
    // @return a boolean value 
    //
    ///
	public boolean insideup(float x,float y,float llx,float lly,float urx,float ury){//check whether the point is below the above line
		
			if(y<=ury){
				
				return true;
			}
			return false;
		}
	 ///
    // inside left
    //The routine checks  whether the given vertex is right of the left clipping line
    // @param x - x coords of vertices of polygon 
    // @param y - y coords of vertices of polygon 
    // @param llx - x coord of lower left of clipping rectangle.
    // @param lly - y coord of lower left of clipping rectangle.
    // @param urx - x coord of upper right of clipping rectangle.
    // @param ury - y coord of upper right of clipping rectangle.
    //
    // @return a boolean value 
    //
    ///
	public boolean insideleft(float x,float y,float llx,float lly,float urx,float ury){////check whether the point is inside the left line
		
			if(x>=llx){
				
				return true;
			}
			return false;
		}
	 ///
    // intersect
    //The routine calculates the intersection of the two given lines
    // @param px - x coords of one of the lines
    // @param py - y coords of one of the lines
	// @param sx - x coords of one of the lines
    // @param sy - y coords of one of the lines
    // @param llx - x coord of lower left of clipping rectangle.
    // @param lly - y coord of lower left of clipping rectangle.
    // @param urx - x coord of upper right of clipping rectangle.
    // @param ury - y coord of upper right of clipping rectangle.
	// @param i - array that stores the output vertices
    // @param t-against which window the intersection is to be found
    //
    // @return a boolean value 
    //
    ///
	public float[] intersect(float px,float py,float sx,float sy,float llx,float lly,float urx,float ury,float[]i,int t){
		float temp[]=new float[4];
		float a1=0;
		float a2=0;
		   
			if(t==0){//for the intersection with the lower side
			
				
				float x1 = sx;
				float x2 = px;
				float x3 = llx;
				float x4 = urx;
				float y1 = sy;
				float y2 = py;
				float y3 = lly;
				float y4 = lly;
				float d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
				if (d == 0){}
			

				float xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
				float yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

				i[0] = xi;
				i[1] = yi;
				
				}
			else
				if(t==1){//for the intersection with the right side
					
					float x1 = sx;
					float x2 = px;
					float x3 = urx;
					float x4 = urx;
					float y1 = sy;
					float y2 = py;
					float y3 = lly;
					float y4 = ury;
					float d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
					if (d == 0){}
						//return null;

					float xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
					float yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

					i[0] = xi;
					i[1] = yi;
					
				}
				else
					if(t==2){//for the intersection with the up side
						
						float x1 = sx;
						float x2 = px;
						float x3 = urx;
						float x4 = llx;
						float y1 = sy;
						float y2 = py;
						float y3 = ury;
						float y4 = ury;
						float d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
						if (d == 0){}
						

						float xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
						float yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

						i[0] = xi;
						i[1] = yi;
						
					}
					else
						if(t==3){//for the intersection with the left side
							
							
							float x1 = sx;
							float x2 = px;
							float x3 = llx;
							float x4 = llx;
							float y1 = sy;
							float y2 = py;
							float y3 = ury;
							float y4 = lly;
							float d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
							if (d == 0){}
								

							float xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
							float yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

							i[0] = xi;
							i[1] = yi;
							
						}
//			
			return i;	
		}
		
	 ///
    // clipPolygon
    //
    // Clip the polygon with vertex count in and vertices inx/iny
    // against the rectangular clipping region specified by lower-left corner
    // (llx,lly) and upper-right corner (urx,ury). The resulting vertices are
    // placed in outx/outy.
    //
    // The routine should return the the vertex count of the polygon
    // resulting from the clipping.
    //
    // @param in the number of vertices in the polygon to be clipped
    // @param inx - x coords of vertices of polygon to be clipped.
    // @param iny - y coords of vertices of polygon to be clipped.
    // @param outx - x coords of vertices of polygon resulting after clipping.
    // @param outy - y coords of vertices of polygon resulting after clipping.
    // @param llx - x coord of lower left of clipping rectangle.
    // @param lly - y coord of lower left of clipping rectangle.
    // @param urx - x coord of upper right of clipping rectangle.
    // @param ury - y coord of upper right of clipping rectangle.
    //
    // @return number of vertices in the polygon resulting after clipping
    //
    ///
    public int clipPolygon(int in, float inx[], float iny[],
                  float outx[], float outy[],
                  float llx, float lly, float urx, float ury)
    {
    	 int counter=0;
    	
    	
    	 float subsx[]=new float[200];
    	 float subsy[]=new float[200];
    	     int c=0;
    		 outLength = 0;
    		 px = inx[in - 1 ];
    		
    		 py=iny[in-1];
    		
    		 for ( int j = 0; j < in; j++) {//for checking with the lower side of the clipping window
    			 float i[]=new float[16];
    			
    		 sx = inx[j];
    		 sy=iny[j];
    		
    		 
    		 if( insidebelow( sx,sy,llx,lly,urx,ury ) ) {
    			
    		 if ( insidebelow( px,py,llx,lly,urx,ury )) { 
    			
    		 outx[c]=sx;
    		 outy[c]=sy;
    		 c=c+1;
    		
    		 } else {
   			
    			 
    		 intersect( px,py, sx,sy,llx,lly,urx,ury, i,0);
    		
    		
    		 outx[c]=i[0];
    		 outy[c]=i[1];
    		 c=c+1;
    		 if(i[2]!=0){
    			 outx[c]=i[2];
    			 outy[c]=i[3];
    			 c=c+1;
    		 }
    		
    		  outx[c]=sx;
    		  outy[c]=sy;
    		  c=c+1;
    		 }
    		 } else {
    		 if( insidebelow ( px,py,llx,lly,urx,ury ) ) { 
    			
    		 intersect(px,py, sx,sy,llx,lly,urx,ury, i,0);
    	
        		 outx[c]=i[0];
        		 outy[c]=i[1];
        		 c=c+1;
        		 if(i[2]!=0){
        			 outx[c]=i[2];
        			 outy[c]=i[3];
        			 c=c+1;
        		 }
        		
    		 }
    		 }
    		 px = sx;
    		 py=sy;
    		
    		 } 
    		 for(int h=0;h<outx.length;h++){
     			
    		 }
    		
    		 if(c!=0){
    		 c=0;
    		 for(int h=0;h<outx.length;h++){
        			
     			 subsx[h]=outx[h];
     			 subsy[h]=outy[h];
       		 }
    		 
    		 px = outx[in - 1 ];
			 py=outy[in-1];
    		 for ( int j = 0; j < in; j++) {//for checking with the right side of the clipping window
    			 
    			 float i[]=new float[16];
        		 sx = subsx[j];
        		 sy=subsy[j];
        		 
        		 
        		 if( insideright( sx,sy,llx,lly,urx,ury ) ) {
        		
        		 if ( insideright( px,py,llx,lly,urx,ury )) { 
        			
        		 outx[c]=sx;
        		 outy[c]=sy;
        		 c=c+1;
        		
        		 } else {
       			
        		 intersect( px,py, sx,sy,llx,lly,urx,ury, i,1);
        		
        		
        		 outx[c]=i[0];
        		 outy[c]=i[1];
        		 c=c+1;
       	
        		  outx[c]=sx;
        		  outy[c]=sy;
        		  c=c+1;
        		 }
        		 } else {
        		 if( insideright ( px,py,llx,lly,urx,ury ) ) { 
        		
        		 intersect(px,py, sx,sy,llx,lly,urx,ury, i,1);
        	
            		 outx[c]=i[0];
            		 outy[c]=i[1];
            		 c=c+1;
            	
            		
            		
        		 }
        		 }
        		 px = sx;
        		 py=sy;
        		
        		 }
    		 for(int h=0;h<outx.length;h++){
      			
     		 }
    		
    		 if(c!=0){
    		 c=0;
    		 for(int h=0;h<outx.length;h++){
        			
     			 subsx[h]=outx[h];
     			 subsy[h]=outy[h];
       		 }
    		 px = outx[in - 1 ];
			 py=outy[in-1];
    		 for ( int j = 0; j < in; j++) {//for checking with the upper side of the clipping window
    			 float i[]=new float[16];
    			
        		 sx = subsx[j];
        		 sy=subsy[j];

        		 
        		 if( insideup( sx,sy,llx,lly,urx,ury ) ) {
        			
        		 if ( insideup( px,py,llx,lly,urx,ury )) { 
        			
        		 outx[c]=sx;
        		 outy[c]=sy;
        		 c=c+1;
        		
        		 } else {
       			
        		 intersect( px,py, sx,sy,llx,lly,urx,ury, i,2);
        		
        		
        		 outx[c]=i[0];
        		 outy[c]=i[1];
        		 c=c+1;   
        		
        		  outx[c]=sx;
        		  outy[c]=sy;
        		  c=c+1;
        		 }
        		 } else {
        		 if( insideup ( px,py,llx,lly,urx,ury ) ) { 
        			
        		 intersect(px,py, sx,sy,llx,lly,urx,ury, i,2);
        	
            		 outx[c]=i[0];
            		 outy[c]=i[1];
            		 c=c+1;
            		 if(i[2]!=0){
            			 outx[c]=i[2];
            			 outy[c]=i[3];
            			 c=c+1;
            		 }
            		
        		 }
        		 }
        		 px = sx;
        		 py=sy;
        		
        		 }
    		
    	
    		 if(c!=0){
    		 c=0;
    		
    		 for(int h=0;h<outx.length;h++){
       			
    			 subsx[h]=outx[h];
    			 subsy[h]=outy[h];
      		 }
    		
    		 px = outx[in - 1 ];
			 py=outy[in-1];
    		 for ( int j = 0; j < in; j++) {//for checking with the left side of the clipping window
    			
    			 float i[]=new float[16];
    			 for(int h=0;h<outx.length;h++){
    				 if(subsx[h]!=0){
    	       			
    				 }
    	      		 }
    				
        		 sx = subsx[j];
        		
        		 sy=subsy[j];       		
        		
        		 if( insideleft( sx,sy,llx,lly,urx,ury ) ) {
        			
        		 if ( insideleft( px,py,llx,lly,urx,ury )) { 
        			
        		 outx[c]=sx;
        		 outy[c]=sy;
        		
        		 c=c+1;
        		 
        		 } else {
       			
        		 intersect( px,py, sx,sy,llx,lly,urx,ury, i,3);
        		
        		
        		 outx[c]=i[0];
        		 outy[c]=i[1];
        		
        		 c=c+1;        		 
        		  outx[c]=sx;
        		  outy[c]=sy;
        		
        		  c=c+1;
        		 
        		 }
        		 } else {
        			
        		 if( insideleft ( px,py,llx,lly,urx,ury ) ) { 
        			 
        		 intersect(px,py, sx,sy,llx,lly,urx,ury, i,3);
        	
            		 outx[c]=i[0];
            		 outy[c]=i[1];
            	
            		 c=c+1;
            		
            		
        		 }
        		 }
        		 px = sx;
        		 py=sy;
        		
        		 
        		 }
    		 
    		
    		for(int h=0;h<outx.length;h++){
    			
    			if (outx[h]!=0){
    				counter=counter+1;
    			
    			}
    		}
    		 }
    		 }
    		 }
         
        return counter; // should return number of vertices in clipped poly.
    }

}
