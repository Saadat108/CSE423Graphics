/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE_423_lab_2;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.lang.Math;
import javax.swing.JFrame;
import java.util.Scanner;


/**
 *
 * @author Saadat
 * this implements the MidPoint Line drawing algorithm
 */
public class MidPoint_Line implements GLEventListener {
    
        /**
     * Interface to the GLU library.
     */
    private GLU glu;

    /**
     * Take care of initialization here.
     * @param gld
     */
    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    /**
     * Take care of drawing here.
     * @param drawable
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        /*
         * put your code here
         */
        MidPoint(gl, -100, -100, 0, 150);
	MidPoint(gl, 100, -100, 0, 150);
        
        MidPoint(gl, -130, 100, 130, 100);
        MidPoint(gl, 130, 100, -130, 100);
        
        MidPoint(gl, -130, 100, 100, -100); // doesn't work
        MidPoint(gl, 100, -100, -130, 100);
        
        MidPoint(gl, -100, -100, 130, 100);
        
        

        

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }

    private void MidPoint(GL2 gl, int x1, int y1, int x2, int y2) {
        
       //write your own code
       gl.glPointSize(1.0f);
       gl.glColor3d(1, 0, 0);
       gl.glBegin(GL2.GL_POINTS);
       
       int dx, dy, d, incE, incNE, x, y, zone;
       
       zone = findZone(x1,y1,x2,y2);
       // converting everyrthing to zone 0
       if(zone==1){     // y, x
           int temp = x1;
           x1 = y1;
           y1 = temp;
           temp = x2;
           x2 = y2;
           y2 = temp;
       }else if(zone==2){   // y, -x
           int temp = x1;
           x1 = y1;
           y1 = -temp;
           temp = x2;
           x2 = y2;
           y2 = -temp;
       }else if(zone==3){   // -x, y
           x1 = -x1;
           x2 = -x2;
       }else if(zone==4){   // -x, -y
           x1 = -x1;
           x2 = -x2;
           y2 = -y2;
           x2 = -x2;
       }else if(zone==5){   // -y, -x
           int temp = x1;
           x1 = -y1;
           y1 = -temp;
           temp = x2;
           x2 = -y2;
           y2 = -temp;
       }else if(zone==6){   // -y, x
           int temp = x1;
           x1 = -y1;
           y1 = temp;
           temp = x2;
           x2 = -y2;
           y2 = temp;
       }else if(zone==7){   // x, -y
           y1 = -y1;
           y2 = -y2;
       }
       
       dx = x2 - x1;
       dy = y2 - y1;
       d   = 2*dy - dx;
       incE   = 2*dy;
       incNE = 2*(dy - dx);
       y = y1;
       
       for (x=x1; x<=x2; x++) 
       {
//         gl.glVertex2d(x, y);
         WritePixel(gl, x, y, zone);
         if (d>0){
             d = d + incNE;
             y = y + 1;
         } else{
             d = d + incE;
           } 
        }

       
       gl.glVertex2d(x1, y1);
       gl.glVertex2d(x2, y2);	
       gl.glEnd();
    }
    
    
    
    private int findZone(int x1, int y1, int x2, int y2){
        
        float dx, dy;
        dy = y2-y1;
        dx = x2-x1;
        
        if(Math.abs(dy) > Math.abs(dx)){
            if(dy>0 && dx>0){return 1;}
            else if(dy>0 && dx<0){return 2;}
            else if(dy<0 && dx<0){return 5;}
            else{return 6;}
        }
        else{
            if(dy>0 && dx>0){return 0;}
            else if(dy>0 && dx<0){return 3;}
            else if(dy<0 && dx<0){return 7;}
            else{return 4;}
        }
        
    }
    private void WritePixel(GL2 gl,int x, int y, int zone){
    if(zone == 0){gl.glVertex2i(x , y );}
    else if(zone == 1){gl.glVertex2i(y , x );}
    else if(zone == 2){gl.glVertex2i(-y , x);}
    else if(zone == 3){gl.glVertex2i(-x , y );}
    else if(zone == 4){gl.glVertex2i(-x , -y );}
    else if(zone == 5){gl.glVertex2i(-y , -x );}
    else if(zone == 6){gl.glVertex2i(y , -x );}
    else{gl.glVertex2i(x , -y );}
    }

    
    public void dispose(GLAutoDrawable arg0) {
        //do nothing
    }
    
}
