package gui;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import utilities.Edge;
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
	public ArrayList<ArrayList<Vertex>> mapRoads;
	public ArrayList<Boolean> interestPoints;
	public ArrayList<String> weather;
	public ArrayList<String> traffic;
	public String chosenWeather;
	public String chosenTraffic;
	public String interestPoint;
	Boolean beginningPoint;
	Boolean endPoint;
	Boolean choosingPoints;
	public Vertex beginningVertex;
	public Vertex endVertex;
	

	public MapPanel() {
		try {
			mapImage = ImageIO.read(new File("resources/mapa.png"));

			numberOfPoints = 0;
			numberOfClicks = 0;
			mapVertexes = new ArrayList<Vertex>();
			mapRoads = new ArrayList<ArrayList<Vertex>>();
			interestPoints = new ArrayList<Boolean>();
			weather = new ArrayList<String>();
			traffic = new ArrayList<String>();
			clickActive = false;
			chosenWeather = "Snow";
			chosenTraffic = "None";
			interestPoint = "Yes";
			beginningVertex = null;
			endVertex = null;
			choosingPoints = false;
			beginningPoint = false;
			endPoint = false;

			addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {

					if (clickActive == true) {

						// Position where the mouse was clicked
						int xPos = e.getX();
						int yPos = e.getY();

						/*
						 * If the position clicked is a node around 5 pixels of
						 * the white pixel of the node
						 * 
						 * B B B B B B B B B B W B B B B B B B B B B
						 * 
						 * B - Black pixel. W - White pixel.
						 */
						boolean pointClicked = false;
						int trueXPos = 0;
						int trueYPos = 0;

						for (int y = 0; y < 5; y++) {
							for (int x = 0; x < 5; x++) {

								if(mapImage.getRGB(xPos, yPos) == Color.white.getRGB())
								{
									trueXPos = xPos;
									trueYPos = yPos;
									pointClicked = true;
								}
								else
									if(mapImage.getRGB(xPos - x, yPos - y) == Color.white.getRGB())
									{
										trueXPos = xPos - x;
										trueYPos = yPos - y;
										pointClicked = true;
									}
									else
										if(mapImage.getRGB(xPos - x, yPos) == Color.white.getRGB())
										{
											trueXPos = xPos - x;
											trueYPos = yPos;
											pointClicked = true;
										}
										else
											if(mapImage.getRGB(xPos, yPos - y) == Color.white.getRGB())
											{
												trueXPos = xPos;
												trueYPos = yPos - y;
												pointClicked = true;
											}
											else
												if(mapImage.getRGB(xPos + x, yPos + y) == Color.white.getRGB())
												{
													trueXPos = xPos + x;
													trueYPos = yPos + y;
													pointClicked = true;
												}
												else
													if(mapImage.getRGB(xPos + x, yPos - y) == Color.white.getRGB())
													{
														trueXPos = xPos + x;
														trueYPos = yPos - y;
														pointClicked = true;
													}
													else
														if(mapImage.getRGB(xPos - x, yPos + y) == Color.white.getRGB())
														{
															trueXPos = xPos - x;
															trueYPos = yPos + y;
															pointClicked = true;
														}
								
								
									/* TESTING */
									/*System.out.print("y: ");
									System.out.print(y);
									System.out.print("\n");
									System.out.print("x: ");
									System.out.print(x);
									System.out.print("\n");
									System.out
											.print("mapImage.getRGB(xPos, yPos)");
									System.out.print(mapImage
											.getRGB(xPos, yPos));
									System.out.print("\n");
									System.out
											.print("mapImage.getRGB(xPos-x, yPos-y)");
									System.out.print(mapImage.getRGB(xPos - x,
											yPos - y));
									System.out.print("\n");
									System.out
											.print("mapImage.getRGB(xPos-x, yPos)");
									System.out.print(mapImage.getRGB(xPos - x,
											yPos));
									System.out.print("\n");
									System.out
											.print("mapImage.getRGB(xPos, yPos-y)");
									System.out.print(mapImage.getRGB(xPos, yPos
											- y));
									System.out.print("\n");
									System.out
											.print("mapImage.getRGB(xPos+x, yPos+y)");
									System.out.print(mapImage.getRGB(xPos + x,
											yPos + y));
									System.out.print("\n");
									System.out
											.print("mapImage.getRGB(xPos+x, yPos-y)");
									System.out.print(mapImage.getRGB(xPos + x,
											yPos - y));
									System.out.print("\n");
									System.out
											.print("mapImage.getRGB(xPos-x, yPos+y)");
									System.out.print(mapImage.getRGB(xPos - x,
											yPos + y));
									System.out.print("\n");*/
									/* TESTING */
									
						
								
							}
						}

						if(pointClicked == true && beginningPoint == true && choosingPoints)
						{
							beginningVertex = new Vertex(trueXPos, trueYPos);
							beginningPoint = false;
						}else
							if(pointClicked == true && endPoint == true && choosingPoints)
							{
								endVertex = new Vertex(trueXPos, trueYPos);
								if(beginningVertex != null)
								{
									choosingPoints = false;
								}
								endPoint = false;
								clickActive = false;
							}else
							if (pointClicked && choosingPoints == false) {

							Vertex point = new Vertex(trueXPos, trueYPos);

							if (numberOfClicks == 0) {
								firstPoint = new Vertex(trueXPos, trueYPos);
								numberOfClicks++;
							} else if ((numberOfClicks == 1)
									&& (!firstPoint.isTheSameAs(point))) {
								secondPoint = new Vertex(trueXPos, trueYPos);

								// Add road to map
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
		// Check if the chosen vertexes are already neighbor vertexes (there is
		// a road between them)
		if (!firstPoint.isNeighborVertex(secondPoint)) {

			double distance = calculate_distance(firstPoint, secondPoint);

			// Set second vertex as neighbor of first vertex
		
			firstPoint.adjacencies = new Edge[]{new Edge(secondPoint, distance)};
			firstPoint.addNeighborgVertex(secondPoint.getXPos(), secondPoint.getYPos());
			
			// Set first vertex as neighbor of second vertex
			
			secondPoint.adjacencies = new Edge[]{new Edge(firstPoint, distance)};
			secondPoint.addNeighborgVertex(firstPoint.getXPos(), firstPoint.getYPos());
		

			// Add new road to the roads of the map
			ArrayList<Vertex> pairOfVertexes = new ArrayList<Vertex>();
			pairOfVertexes.add(firstPoint);
			pairOfVertexes.add(secondPoint);
			mapRoads.add(pairOfVertexes);

			// Interest point
			if (interestPoint == "Yes") {
				interestPoints.add(true);
			} else {
				interestPoints.add(false);
			}
			// TODO add interest point to edge

			// Traffic
			traffic.add(chosenTraffic);
			// TODO add traffic to edge

			// Weather
			weather.add(chosenWeather);
			// TODO add weather to edge

			// TODO add edge to map of edges

			repaint();

		} else {
			// TODO window alert:
			// "There is already a road between these points!"
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

					/* TESTING */
					/*
					System.out.print("NewPoint\n");
					System.out.print("x: ");
					System.out.print(x);
					System.out.print("\n");
					System.out.print("y: ");
					System.out.print(y);
					System.out.print("\n");*/
					/* TESTING */
				}
			}
		}
		
		System.out.println("Added points to graph.");
		
		return mapVertexes;
	}

	public void choosePoints() {
		clickActive = true;
	}

	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public void drawRoads(Graphics g) {

		if (!mapRoads.isEmpty()) {

			int iPoint = 0;

			for (ArrayList<Vertex> currentRoad : mapRoads) {

				Vertex firstVertex = currentRoad.get(0);
				Vertex secondVertex = currentRoad.get(1);

				// Draw a road between the two vertexes
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(4));

				// Interest Points
				if (interestPoints.get(iPoint)) {
					
					float[] dashingPattern3 = { 10f, 10f, 1f, 10f };
					Stroke stroke3 = new BasicStroke(4f,
							BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER,
							1.0f, dashingPattern3, 0.0f);
					g2.setStroke(stroke3);
				}

				// Traffic
				String trafficState = traffic.get(iPoint);

				if (trafficState == "Light") {
					g2.setColor(Color.GREEN);
				} else if (trafficState == "Moderate") {
					g2.setColor(Color.YELLOW);
				} else if (trafficState == "High") {
					g2.setColor(Color.ORANGE);
				} else if (trafficState == "Stopped") {
					g2.setColor(Color.RED);
				} else {
					g2.setColor(Color.GRAY);
				}

				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON); // antialiasing on
				Line2D road = new Line2D.Float(firstVertex.getXPos(),
						firstVertex.getYPos(), secondVertex.getXPos(),
						secondVertex.getYPos());
				g2.draw(road);

				// Weather

				// Find midpoint of the road
				double midPointX = (firstVertex.getXPos() + secondVertex
						.getXPos()) / 2;
				double midPointY = (firstVertex.getYPos() + secondVertex
						.getYPos()) / 2;

				String weatherState = weather.get(iPoint);

				if (weatherState == "Storm") {
					g2.setColor(Color.BLACK);
				} else if (weatherState == "Snow") {
					g2.setColor(Color.WHITE);
				} else if (weatherState == "Hail") {
					g2.setColor(Color.DARK_GRAY);
				} else if (weatherState == "High Rain") {
					g2.setColor(Color.GREEN);
				} else if (weatherState == "Light Rain") {
					g2.setColor(Color.BLUE);
				} else if (weatherState == "Cloudy") {
					g2.setColor(Color.GRAY);
				} else if (weatherState == "Sunny") {
					g2.setColor(Color.YELLOW);
				}

				g2.setStroke(new BasicStroke(4));
				g2.draw(new Ellipse2D.Double(midPointX, midPointY, 10, 10));

				iPoint++;
			}

		}

	}
	
	public double calculate_distance(Vertex point1, Vertex point2){
		double result = Math.sqrt(Math.pow((point1.getXPos()-point2.getXPos()), 2)+Math.pow((point1.getYPos()-point2.getYPos()), 2));
		return result;	
	}

	public ArrayList<Vertex> updateMapPoints() {
		return this.mapVertexes;
	}

	public int updateNumberOfPoints() {
		return this.numberOfPoints;
	}
	
	public void addRoad(ArrayList<Vertex> pairOfVertexes)
	{
		mapRoads.add(pairOfVertexes);
	}
	
	public void addInterestPoint(Boolean ip)
	{
		interestPoints.add(ip);
	}
	
	public void addTraffic(String t)
	{
		traffic.add(t);
	}
	
	public void addWeather(String w)
	{
		weather.add(w);
	}
	
	public void drawBEPoints(Graphics g)
	{
		// Change the color of the beginning and end points 
		Graphics2D g2 = (Graphics2D) g;
		
		// Beginning point (White)
	
		if(beginningPoint)
		{
		
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
						
				mapImage.setRGB(beginningVertex.getXPos(), beginningVertex.getYPos(), Color.WHITE.getRGB());
				mapImage.setRGB(beginningVertex.getXPos() - x, beginningVertex.getYPos() - y, Color.WHITE.getRGB());
				mapImage.setRGB(beginningVertex.getXPos() - x, beginningVertex.getYPos(), Color.WHITE.getRGB());
				mapImage.setRGB(beginningVertex.getXPos(), beginningVertex.getYPos() - y, Color.WHITE.getRGB());
				mapImage.setRGB(beginningVertex.getXPos() + x, beginningVertex.getYPos() + y, Color.WHITE.getRGB());
				mapImage.setRGB(beginningVertex.getXPos() + x, beginningVertex.getYPos() - y, Color.WHITE.getRGB());
				mapImage.setRGB(beginningVertex.getXPos() - x, beginningVertex.getYPos() + y, Color.WHITE.getRGB());
			
			
			}
		}
		}
		
		// End point (Red)
		if(endPoint)
		{
		
			for (int y = 0; y < 5; y++) {
				for (int x = 0; x < 5; x++) {
								
					mapImage.setRGB(endVertex.getXPos(), endVertex.getYPos(), Color.RED.getRGB());
					mapImage.setRGB(endVertex.getXPos() - x, endVertex.getYPos() - y, Color.RED.getRGB());
					mapImage.setRGB(endVertex.getXPos() - x, endVertex.getYPos(), Color.RED.getRGB());
					mapImage.setRGB(endVertex.getXPos(), endVertex.getYPos() - y, Color.RED.getRGB());
					mapImage.setRGB(endVertex.getXPos() + x, endVertex.getYPos() + y, Color.RED.getRGB());
					mapImage.setRGB(endVertex.getXPos() + x, endVertex.getYPos() - y, Color.RED.getRGB());
					mapImage.setRGB(endVertex.getXPos() - x, endVertex.getYPos() + y, Color.RED.getRGB());
				
				
				}
			}
		}
		
			
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mapImage, 0, 0, null);
		if (mapVertexes != null) {
			drawRoads(g);
			drawBEPoints(g);
		}

	}

}
