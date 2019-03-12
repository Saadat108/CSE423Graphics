/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE_423_lab_2;

/**
 *
 * @author User
 */

//import javax.media.opengl.GL2;
//import javax.media.opengl.GLAutoDrawable;
//import javax.media.opengl.GLEventListener;
//import javax.media.opengl.glu.GLU;


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
 * @author sbiswas.amit
 */
public class DDALines implements GLEventListener {
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
        DDA(gl, -100, -50, 60, 70);
        DDA(gl, -100, -50, -100, 50);
        DDA(gl, -100, 50, 60, 70);
        

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }

    private void DDA(GL2 gl, int x1, int y1, int x2, int y2) {
       //write your own code
       gl.glPointSize(1.0f);
       gl.glColor3d(1, 0, 0);
       gl.glBegin(GL2.GL_POINTS);
       
       float length;
       float dX = Math.abs(x2-x1);
       float dY = Math.abs(y2-y1);
       if(dX>=dY){
           length = dX;
       }else{
           length = dY;
           double i = 3.8;
           double j = 2.9;
       }
       dX = Math.abs(x2-x1)/length;
       dY = Math.abs(y2-y1)/length;
       
//       int x = x1 + (1/2)*Sign(dX);
//       int y = y1 + (1/2)*Sign(dY);
       
       float x = x1 + (dX);
       float y = y1 + (dY);
       
       for(int i=0;i<=length;i++){
           gl.glVertex2d(x, y); //plotting X and Y
           System.out.println(x +"---"+ y);
           x += dX;
           y += dY;
       }
       
       gl.glVertex2d(x1, y1);
       gl.glVertex2d(x2, y2);	
       gl.glEnd();
    }
    
    private int Sign(float num){
        
        if(num>0){
            return 1;
        }
        else if(num<0){
            return -1;
        }
        else{
            return 0;
        }
        
    }
    
    
    public void dispose(GLAutoDrawable arg0) {
        //do nothing
    }
}
