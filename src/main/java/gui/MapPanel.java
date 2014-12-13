package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import utilities.Node;

public class MapPanel extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage mapImage;
	private int numberOfPoints;

    public MapPanel() {
       try {                
    	   mapImage = ImageIO.read(new File("mapa.png"));
          
       } catch (IOException ex) {
            // handle exception...
       }
    }

    
    public ArrayList<Node> getImagePoints()
    {
    	ArrayList<Node> mapPoints = new ArrayList<Node>();
    	numberOfPoints = 0;
    	
    	for(int y = 0; y < 611; y++)
        {
      	  for(int x = 0; x < 760; x++)
      	  {
      		  // If the pixel is white, it's a point/node of the map
      		  if(mapImage.getRGB(x,y) == Color.white.getRGB())
      		  {
      			 Node n = new Node(x,y);
      			 mapPoints.add(n);
      			numberOfPoints = numberOfPoints + 1;
      			System.out.print("NewPoint\n");
      			System.out.print("x: ");
      			System.out.print(x);
      			System.out.print("\n");
      			System.out.print("y: ");
      			System.out.print(y);
      			System.out.print("\n");
      		  }
      	  }
        }
    	
    	return mapPoints;
    }
    
    public int getNumberOfPoints()
    {
    	return numberOfPoints;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mapImage, 0, 0, null);
    }

    
}