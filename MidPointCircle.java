/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE_423_lab_2;

/**
 *
 * @author Saadat
 */

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


public class MidPointCircle implements GLEventListener {
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
        MidPoint_Circle(gl, 100,0,0);
	MidPoint_Circle(gl, 25,50,50);
        MidPoint_Circle(gl, 25,50,-50);
        MidPoint_Circle(gl, 25,-50,50);
        MidPoint_Circle(gl, 25,-50,-50);
        
        MidPoint_Circle(gl, 50,-50,0);
        MidPoint_Circle(gl, 50,50,0);
        MidPoint_Circle(gl, 50,0,-50);
        MidPoint_Circle(gl, 50,0,50);

        
        

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }

    private void MidPoint_Circle(GL2 gl, int r, int centerX, int centerY) {
       //write your own code
       gl.glPointSize(1.0f);
       gl.glColor3d(1, 0, 0);
       gl.glBegin(GL2.GL_POINTS);
       
       int y = r;
       int x = 0;
       
//       gl.glVertex2d(x, y);
       draw8Way(gl,x,y,centerX,centerY);
       
       double d = (5.0/4.0) - r;
       double dE = (2.0*x)+3;
       double dSE = (2*x)-(2*y)+5;
       

       
       while(y>x){
           if(d<0){ // choose East 
               d+=(2.0*x)+3;
               
           }else{ //choose South East 
               d+=(2*x)-(2*y)+5;;
               y--;
               

           }
           x++;
//           gl.glVertex2d(x, y);
           System.out.println(x + "  " + y + "  " + d);
           draw8Way(gl,x,y,centerX,centerY);
       }
//       gl.glVertex2d(x, y);
               

       gl.glEnd();
    }
    public void draw8Way(GL2 gl, int x, int y, int Cx, int Cy){
        gl.glVertex2i(x+Cx, y+Cy);
        gl.glVertex2i(y+Cx ,x+Cy);
        gl.glVertex2i(-y+Cx, x+Cy);
        gl.glVertex2i(-x+Cx, y+Cy);
        gl.glVertex2i(-x+Cx, -y+Cy);
        gl.glVertex2i(-y+Cx, -x+Cy);
        gl.glVertex2i(y+Cx, -x+Cy);
        gl.glVertex2i(x+Cx, -y+Cy);
    }
    
    public void dispose(GLAutoDrawable arg0) {
        //do nothing
    }
}
