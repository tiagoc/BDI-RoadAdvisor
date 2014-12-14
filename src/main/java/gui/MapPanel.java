package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    public MapPanel() {
        try {
            mapImage = ImageIO.read(new File("resources/mapa.png"));
            numberOfPoints = 0;
            numberOfClicks = 0;
            mapVertexes = new ArrayList<Vertex>();
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

                            if (numberOfClicks == 0) {
                                firstPoint = new Vertex(xPos, yPos);
                                numberOfClicks++;
                            } else if (numberOfClicks == 1) {
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
        // Check if the chosen nodes are already neighbor nodes (there is a road between them)
        if (!firstPoint.isNeighborVertex(secondPoint)) {
            for (int i = 0; i < mapVertexes.size(); i++) {

                Vertex currentVertex = mapVertexes.get(i);

                if (currentVertex.isTheSameAs(firstPoint)) {
                    // Set second node as neighbor of first node  
                	currentVertex.addNeighborgVertex(secondPoint.getXPos(), secondPoint.getYPos());
                    mapVertexes.set(i, currentVertex);
                } else if (currentVertex.isTheSameAs(secondPoint)) {
                    // Set first node as neighbor of second node
                	currentVertex.addNeighborgVertex(firstPoint.getXPos(), firstPoint.getYPos());
                    mapVertexes.set(i, currentVertex);
                }
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
    	// TODO draw without repeating roads (graph style)

        for (Vertex currentVertex : mapVertexes) {

            ArrayList<Vertex> neiVertexes = currentVertex.getNeighborgVertexes();

            if (neiVertexes.size() > 0) {

                // Draw a road between each neighbor node and the current node
                for (Vertex neighbor : neiVertexes) {
                    g.drawLine(currentVertex.getXPos(), currentVertex.getYPos(), neighbor.getXPos(), neighbor.getYPos());
                }

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
