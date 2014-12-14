package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import utilities.Vertex;



public class MapPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private BufferedImage mapImage;
    private int numberOfPoints;
    private int numberOfClicks;
    private Vertex firstPoint;
    private Vertex secondPoint;
    private ArrayList<Vertex> mapVertexes;
    private boolean clickActive;
    private ArrayList<ArrayList<Vertex>> mapRoads;
    private ArrayList<Boolean> interestPoints;

    public MapPanel() {
        try {
            mapImage = ImageIO.read(new File("resources/mapa.png"));
            numberOfPoints = 0;
            numberOfClicks = 0;
            mapVertexes = new ArrayList<Vertex>();
            mapRoads = new ArrayList<ArrayList<Vertex>>();
            clickActive = false;

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    if (clickActive == true) {

                        // Position where the mouse was clicked
                        int xPos = e.getX();
                        int yPos = e.getY();

                        /* If the position clicked is a node around 5 pixels of the white pixel of the node
                         * 
                         * 		  B	B B
                         * 		B B	B B	B
                         * 		B B W B B
                         *       B B B B B
                         * 		  B B B	
                         * 
                         * B - Black pixel.
                         * W - White pixel.
                         * 
                         * */
                        boolean pointClicked = false;

                        for (int y = 0; y < 5; y++) {
                            for (int x = 0; x < 5; x++) {

                                if (((mapImage.getRGB(xPos, yPos)) | (mapImage.getRGB(xPos - x, yPos - y)) | (mapImage.getRGB(xPos - x, yPos)) | (mapImage.getRGB(xPos, yPos - y)) | (mapImage.getRGB(xPos + x, yPos + y)) | (mapImage.getRGB(xPos + x, yPos - y)) | (mapImage.getRGB(xPos - x, yPos + y))) == (Color.white.getRGB())) {
                                    /* TODO TESTE */
                                    System.out.print("y: ");
                                    System.out.print(y);
                                    System.out.print("\n");
                                    System.out.print("x: ");
                                    System.out.print(x);
                                    System.out.print("\n");
                                    System.out.print("mapImage.getRGB(xPos, yPos)");
                                    System.out.print(mapImage.getRGB(xPos, yPos));
                                    System.out.print("\n");
                                    System.out.print("mapImage.getRGB(xPos-x, yPos-y)");
                                    System.out.print(mapImage.getRGB(xPos - x, yPos - y));
                                    System.out.print("\n");
                                    System.out.print("mapImage.getRGB(xPos-x, yPos)");
                                    System.out.print(mapImage.getRGB(xPos - x, yPos));
                                    System.out.print("\n");
                                    System.out.print("mapImage.getRGB(xPos, yPos-y)");
                                    System.out.print(mapImage.getRGB(xPos, yPos - y));
                                    System.out.print("\n");
                                    System.out.print("mapImage.getRGB(xPos+x, yPos+y)");
                                    System.out.print(mapImage.getRGB(xPos + x, yPos + y));
                                    System.out.print("\n");
                                    System.out.print("mapImage.getRGB(xPos+x, yPos-y)");
                                    System.out.print(mapImage.getRGB(xPos + x, yPos - y));
                                    System.out.print("\n");
                                    System.out.print("mapImage.getRGB(xPos-x, yPos+y)");
                                    System.out.print(mapImage.getRGB(xPos - x, yPos + y));
                                    System.out.print("\n");
                                    /* TODO TESTE */

                                    pointClicked = true;
                                }
                            }
                        }

                        if (pointClicked) {

                        	Vertex point = new Vertex(xPos, yPos);
                        	
                            if (numberOfClicks == 0) {
                                firstPoint = new Vertex(xPos, yPos);
                                numberOfClicks++;
                            } else if ((numberOfClicks == 1) && (!firstPoint.isTheSameAs(point))) {
                                secondPoint = new Vertex(xPos, yPos);
                                addRoad();
                            }
                        }

                    }
                }

            });

        } catch (IOException ex) {
            // handle exception...
        }

    }

    public void addRoad() {
        // Check if the chosen vertexes are already neighbor vertexes (there is a road between them)
        if (!firstPoint.isNeighborVertex(secondPoint)) {
            for (int i = 0; i < mapVertexes.size(); i++) {

                Vertex currentVertex = mapVertexes.get(i);

                /*if (currentVertex.isTheSameAs(firstPoint)) {
                    // TODO Set second vertex as neighbor of first vertex  
                	currentVertex.addNeighborgVertex(secondPoint.getXPos(), secondPoint.getYPos());
                    mapVertexes.set(i, currentVertex);
                } else if (currentVertex.isTheSameAs(secondPoint)) {
                    // TODO Set first vertex as neighbor of second vertex
                	currentVertex.addNeighborgVertex(firstPoint.getXPos(), firstPoint.getYPos());
                    mapVertexes.set(i, currentVertex);
                }*/
                
                // Add new road to the roads of the map
                ArrayList<Vertex> pairOfVertexes = new ArrayList<Vertex>();
                pairOfVertexes.add(firstPoint);
                pairOfVertexes.add(secondPoint);
                mapRoads.add(pairOfVertexes);
                
                repaint();
        }
        } else {
            // TODO window alert: "There is already a road between these points!"
        }

        firstPoint = null;
        secondPoint = null;
        numberOfClicks = 0;
        clickActive = false;
    }

    public ArrayList<Vertex> getImagePoints() {
        numberOfPoints = 0;

        for (int y = 0; y < 611; y++) {
            for (int x = 0; x < 760; x++) {
                // If the pixel is white, it's a point/node of the map
                if (mapImage.getRGB(x, y) == Color.white.getRGB()) {
                    Vertex v = new Vertex(x, y);

                    mapVertexes.add(v);
                    numberOfPoints = numberOfPoints + 1;

                    /* TODO TESTE */
                    System.out.print("NewPoint\n");
                    System.out.print("x: ");
                    System.out.print(x);
                    System.out.print("\n");
                    System.out.print("y: ");
                    System.out.print(y);
                    System.out.print("\n");
                    /* TODO TESTE */
                }
            }
        }

        return mapVertexes;
    }

    public void choosePoints() {
        clickActive = true;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void drawRoads(Graphics g) {

    	if(mapRoads.size() > 0)
    	{
    		
    		int iPoint = 0;
    		
        for (ArrayList<Vertex> currentRoad : mapRoads) {

            Vertex firstVertex = currentRoad.get(0);
            Vertex secondVertex = currentRoad.get(1);

            // Draw a road between the two vertexes    
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4));
            if(interestPoints.get(iPoint))
            {
            	g2.setColor(Color.RED);
            }
            else
            {
            	g2.setColor(Color.GRAY);
            }
            
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // antialiasing on
            g2.draw(new Line2D.Float(firstVertex.getXPos(), firstVertex.getYPos(), secondVertex.getXPos(), secondVertex.getYPos()));
            
            iPoint++;
        }

        }

    }

    public ArrayList<Vertex> updateMapPoints() {
        return this.mapVertexes;
    }

    public int updateNumberOfPoints() {
        return this.numberOfPoints;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mapImage, 0, 0, null);
        if (mapVertexes.size() != 0) {
            drawRoads(g);
        }
    }

}
