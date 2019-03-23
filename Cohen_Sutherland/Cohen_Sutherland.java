/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE_423_lab_2.Cohen_Sutherland;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

/**
 *
 * @author User
 */
//public class Cohen_Sutherland {
//    int x1, y1, x2, y2;
//    
//    public Cohen_Sutherland(int x1, int y1, int x2, int y2){
//        this.x1 = x1;
//        this.x2 = x2;
//        this.y1 = y1;
//        this.y2 = y2;
//        
//        int x= 000;
//        int y = 010;
//        int z = 010;
//        System.out.println(x & y);
//        System.out.println(z & y);
//        System.out.println(z & x);
//    }
//}
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
public class Cohen_Sutherland implements GLEventListener {
    
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
        
        
        MidPoint(gl,-100, 100, -100, -100);
	MidPoint(gl,100, 100, -100, 100);
        
        MidPoint(gl, 100, 100, 100, -100);
        MidPoint(gl, 100, -100, -100, -100);

        
//        clip(gl, int x0, int y0, int x1, int y1, int x_max,int x_min,int y_max,int y_min);
        clip(gl, 0, 0, 90, 90, 120, -100, 100, -100);

        

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }
    
    public void clip(GL2 gl, int x0, int y0, int x1, int y1, int x_max,int x_min,int y_max,int y_min){
        int T = 0B10000;
        int B = 0B0100;
        int L = 0B0001;
        int R = 0B0110;
        int code = 0B0000;
        int code0 = 0B0000;
        int code1 = 0B0000;
        double m = (y1-y0)/(x1-x0);
        code0 = makeCode(x0, y0, x_max, x_min, y_max, y_min);
        code1 = makeCode(x1, y1, x_max, x_min, y_max, y_min);
        while(true){
            if(code0 + code1 == 0){
                MidPoint(gl, x0, y0, x1, y1);
                break;
            }
            else if((code0&code1)>0){
                break;
            }
            else{
                int x,y;
                if(code0>0){
                    code=code0;
                    x=x0;
                    y=y0;
                }else{
                    code=code1;
                    x=x1;
                    y=y1;
                }
                
                if((code&T)>0){
                    y=y_max;
                    x=(int) ((1/m)*(y_max-y0));
                }
                else if((code&B)>0){
                    y=y_min;
                    x=(int) ((1/m)*(y_min-y0));
                }
                if((code&R)>0){
                    x=x_max;
                    y=(int) ((m)*(x_max-x0));
                }
                else if((code&L)>0){
                    x=x_min;
                    y=(int) ((m)*(x_min-x0));
                }
                if(code == code0){
                    x0 = x;
                    y0 = y;
                    code0= makeCode(x, y, x_max, x_min, y_max, y_min);
                }
                else{
                    x1 = x;
                    y1 = y;
                    code1= makeCode(x, y, x_max, x_min, y_max, y_min);
                }
            }
            
        }
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
           y1 = -y1;
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
    
    public int makeCode(int x, int y,int x_max,int x_min,int y_max,int y_min){
        int code = 0B0000;
        if(x<x_min){ // L
            code += 0B0001;
        }else if(x>x_max){ // R
            code += 0B0010;
        }
        if(y<y_min){ //B
            code += 0B0100;
        }else if(y>y_max){ // T
            code += 0B1000;
        }
        return code;
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

