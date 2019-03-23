/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE_423_lab_2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//com.jogamp or javax.media
//import javax.media.opengl.GLCapabilities;
//import javax.media.opengl.GLProfile;
//import javax.media.opengl.awt.GLCanvas;
//import javax.swing.JFrame;

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
public class OpenGL {

    /**
     * uses point drawing DDA algorithm 
     **/
    /*public static void main(String[] args) {
     //getting the capabilities object of GL2 profile
    final GLProfile profile=GLProfile.get(GLProfile.GL2);
    GLCapabilities capabilities=new GLCapabilities(profile);
    // The canvas
    final GLCanvas glcanvas=new GLCanvas(capabilities);
    ThirdGLEventListener b =new ThirdGLEventListener();
    glcanvas.addGLEventListener(b);
    glcanvas.setSize(400, 400);
    //creating frame
    final JFrame frame = new JFrame("My frame");
    //adding canvas to frame
    frame.add(glcanvas);
    //frame.setSize(640,480);
    frame.setSize(frame.getContentPane().getPreferredSize());
    frame.setVisible(true);
    }*/
    
    /**
     * uses line drawing method
     **/
    public static void main(String[] args) {

      //getting the capabilities object of GL2 profile        
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
   
      // The canvas
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      
//      DDALines drawing = new DDALines();    // for DDA algorithm calling
      
      MidPoint_Line drawing = new MidPoint_Line();  // for MidPoint algo calling
      
//      MidPointCircle drawing = new MidPointCircle();
      
      glcanvas.addGLEventListener(drawing);
      glcanvas.setSize(500, 500);
   
      //creating frame
      final JFrame frame = new JFrame ("MidPoint Circle");
   
      //adding canvas to frame
      frame.getContentPane().add(glcanvas);                 
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
      
   }
    
}


