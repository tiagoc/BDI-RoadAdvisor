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



public class MapPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private BufferedImage mapImage;
    private int numberOfPoints;
    private int numberOfClicks;
    private Node firstNode;
    private Node secondNode;
    private ArrayList<Node> mapNodes;

    public MapPanel() {
        try {
            mapImage = ImageIO.read(new File("resources/mapa.png"));
            numberOfPoints = 0;
            numberOfClicks = 0;
            mapNodes = new ArrayList<Node>();

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    // Position where the mouse was clicked
                    int xPos = e.getX();
                    int yPos = e.getY();

                    /* If the position clicked is a node
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
                    if (((mapImage.getRGB(xPos, yPos))
                            | (mapImage.getRGB(xPos - 1, yPos)) | (mapImage.getRGB(xPos - 2, yPos))
                            | (mapImage.getRGB(xPos + 1, yPos)) | (mapImage.getRGB(xPos + 2, yPos))
                            | (mapImage.getRGB(xPos, yPos - 1)) | (mapImage.getRGB(xPos, yPos - 2))
                            | (mapImage.getRGB(xPos, yPos + 1)) | (mapImage.getRGB(xPos, yPos + 2))
                            | (mapImage.getRGB(xPos - 1, yPos + 1)) | (mapImage.getRGB(xPos + 1, yPos - 1))
                            | (mapImage.getRGB(xPos + 1, yPos + 1)) | (mapImage.getRGB(xPos - 1, yPos - 1))
                            | (mapImage.getRGB(xPos + 1, yPos + 2)) | (mapImage.getRGB(xPos + 1, yPos - 2))
                            | (mapImage.getRGB(xPos + 2, yPos + 1)) | (mapImage.getRGB(xPos - 2, yPos + 1))
                            | (mapImage.getRGB(xPos + 2, yPos - 1)) | (mapImage.getRGB(xPos - 1, yPos + 2))
                            | (mapImage.getRGB(xPos - 1, yPos + 2)) | (mapImage.getRGB(xPos + 2, yPos - 1))) == (Color.white.getRGB())) {

                        if (numberOfClicks == 0) {
                            firstNode = new Node(xPos, yPos);
                        } else if (numberOfClicks == 1) {
                            secondNode = new Node(xPos, yPos);
                            addRoad();
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
        if (!firstNode.isNeighbourgNode(secondNode)) {
            for (int i = 0; i < mapNodes.size(); i++) {

                Node currentNode = mapNodes.get(i);

                if (currentNode.isTheSameAs(firstNode)) {
                    // Set second node as neighbor of first node  
                    currentNode.addNeighbourgNode(secondNode.getXPos(), secondNode.getYPos());
                    mapNodes.set(i, currentNode);
                } else if (currentNode.isTheSameAs(secondNode)) {
                    // Set first node as neighbor of second node
                    currentNode.addNeighbourgNode(firstNode.getXPos(), firstNode.getYPos());
                    mapNodes.set(i, currentNode);
                }
            }
        } else {
            // TODO window alert: "There is already a road between these points!"
        }

        firstNode = null;
        secondNode = null;
        numberOfClicks = 0;
    }

    public ArrayList<Node> getImagePoints() {
        ArrayList<Node> mapPoints = new ArrayList<Node>();
        numberOfPoints = 0;

        for (int y = 0; y < 611; y++) {
            for (int x = 0; x < 760; x++) {
                // If the pixel is white, it's a point/node of the map
                if (mapImage.getRGB(x, y) == Color.white.getRGB()) {
                    Node n = new Node(x, y);
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

    public void choosePoints() {

    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void drawRoads(Graphics g) {
        // TODO draw without repeating roads (graph style)

        for (Node currentNode : mapNodes) {

            ArrayList<Node> neiNodes = currentNode.getNeighborgNodes();

            // Draw a road between each neighbor node and the current node
            for (Node neighbor : neiNodes) {
                g.drawLine(currentNode.getXPos(), currentNode.getYPos(), neighbor.getXPos(), neighbor.getYPos());
            }

        }

    }

    public ArrayList<Node> updateMapPoints() {
        return this.mapNodes;
    }

    public int updateNumberOfNodes() {
        return this.numberOfPoints;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mapImage, 0, 0, null);
        if (mapNodes.size() != 0) {
            drawRoads(g);
        }
    }

}
