package gui;

import agents.WorldBDI;

import java.awt.EventQueue;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Timer;

import utilities.Vertex;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.util.ArrayList;
import javax.swing.JSeparator;
import javax.swing.JSpinner;



public class WindowUI {

	static WindowUI window;
    private JFrame frame;
    public JPanel panel_4;
    public WorldBDI world;
    public Timer mapTimer;
    JComboBox<Object> comboBox_Weather;
    JComboBox<Object> comboBox_Traffic;
    JComboBox<Object> comboBox_InterestPoint;
    JComboBox<Object> comboBoxPoints;
    JComboBox<Object> comboBoxTrip;
    JButton btnAddRoad;
    JButton btnStart;
    JButton btnAddPredefinedMap;

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    window = new WindowUI();
                    window.getFrame().setVisible(true); 
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public WindowUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	world = new WorldBDI();
        setFrame(new JFrame());
        getFrame().setBounds(100, 100, 822, 388);
        getFrame().setSize(980, 655);
        getFrame().setResizable(false);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        getFrame().getContentPane().add(panel, BorderLayout.NORTH);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGap(0, 1000, Short.MAX_VALUE)
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGap(0, 10, Short.MAX_VALUE)
        );
        panel.setLayout(gl_panel);

        JPanel panel_1 = new JPanel();
        getFrame().getContentPane().add(panel_1, BorderLayout.SOUTH);
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 1000, Short.MAX_VALUE)
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 10, Short.MAX_VALUE)
        );
        panel_1.setLayout(gl_panel_1);

        JPanel panel_2 = new JPanel();
        getFrame().getContentPane().add(panel_2, BorderLayout.WEST);
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
                gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGap(0, 10, Short.MAX_VALUE)
        );
        gl_panel_2.setVerticalGroup(
                gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGap(0, 658, Short.MAX_VALUE)
        );
        panel_2.setLayout(gl_panel_2);

        /* Time Period Options */
        ButtonGroup timePeriodButtons = new ButtonGroup();

        /* Weather Options */
        String[] WeatherStrings = {"Storm", "Snow", "Hail", "High Rain", "Light Rain", "Cloudy", "Sunny"};

        /* Traffic Options */
        String[] trafficStrings = {"None", "Light", "Moderate", "High", "Stopped"};

        /* Interest Point */
        String[] options = {"Yes", "No"};
        
        /* Pick A Point */
        String[] points = {"None", "Beginning", "End"};
        
        /* Trip Options */
        String[] trip = {"Shortest", "Fastest", "Most Cities", "Most Interest Points"};

        panel_4 = new MapPanel();
        getFrame().getContentPane().add(panel_4, BorderLayout.CENTER);
        
                JPanel panel_3 = new JPanel();
                JLabel lblTimePeriod = new JLabel("Time Period");
                JRadioButton rdbtnDay = new JRadioButton("Day");
                JRadioButton rdbtnNight = new JRadioButton("Night");
                
                        timePeriodButtons.add(rdbtnDay);
                        timePeriodButtons.add(rdbtnNight);
                        JLabel lbl_Weather = new JLabel("Weather");
                        comboBox_Weather = new JComboBox<Object>(WeatherStrings);
                        comboBox_Weather.setSelectedIndex(0);
                        comboBox_Weather.addActionListener(new ActionListener() {
                            
                            public void actionPerformed(ActionEvent e) {

                            	@SuppressWarnings("unchecked")
				JComboBox<Object> src = (JComboBox<Object>)e.getSource();
                            	((MapPanel) panel_4).chosenWeather = (String)src.getSelectedItem();
                            }
                        });
                        JLabel lblTrnsito = new JLabel("Traffic");
                        comboBox_Traffic = new JComboBox<Object>(trafficStrings);
                        comboBox_Traffic.setSelectedIndex(0);
                        comboBox_Traffic.addActionListener(new ActionListener() {
                            
                            public void actionPerformed(ActionEvent e) {

                            	@SuppressWarnings("unchecked")
				JComboBox<Object> src = (JComboBox<Object>)e.getSource();
                            	 ((MapPanel) panel_4).chosenTraffic = (String)src.getSelectedItem();
                            }
                        });
                        
                        JLabel lblIsInterestPoint = new JLabel("Is An Interest Point?");
                        comboBox_InterestPoint = new JComboBox<Object>(options);
                        comboBox_InterestPoint.addActionListener(new ActionListener() {
                            
                            public void actionPerformed(ActionEvent e) {

                            	@SuppressWarnings("unchecked")
				JComboBox<Object> src = (JComboBox<Object>)e.getSource();
                                ((MapPanel) panel_4).interestPoint = (String)src.getSelectedItem();
                            }
                        });
                        
                         btnAddRoad = new JButton("Add Road");
                         btnAddRoad.setEnabled(!((MapPanel) panel_4).choosingPoints);
                         btnAddRoad.addActionListener(new ActionListener() {
                             
                             public void actionPerformed(ActionEvent e) {

                                 ((MapPanel) panel_4).choosePoints();
                                 world.setCurrentWeather(((MapPanel) panel_4).weather);
                                 world.setCurrentTraffic(((MapPanel) panel_4).traffic);
                                 update();

                             }
                         });
                         
                         JLabel lblPickPoint = new JLabel("Pick Point");
                         
                         comboBoxPoints = new JComboBox<Object>(points);
                         comboBoxPoints.addActionListener(new ActionListener() {
                             
                             public void actionPerformed(ActionEvent e) {

                             	@SuppressWarnings("unchecked")
                             	JComboBox<Object> src = (JComboBox<Object>)e.getSource();
                                String chosenOption = (String)src.getSelectedItem();
                                
                                if(chosenOption == "None")
                                {
                                	/* Enable Other Options */
                                    comboBox_Weather.setEnabled(true);
                                    comboBox_Traffic.setEnabled(true);
                                    comboBox_InterestPoint.setEnabled(true);
                                    btnAddPredefinedMap.setEnabled(true);
                                    comboBoxTrip.setEnabled(true);
                                    btnAddRoad.setEnabled(true);
                                    btnStart.setEnabled(true);
                                }
                                else
                                	if(chosenOption == "Beginning")
                                	{
                                		/* Disable Other Options */
                                    	comboBox_Weather.setEnabled(false);
                                    	comboBox_Traffic.setEnabled(false);
                                    	comboBox_InterestPoint.setEnabled(false);
                                    	btnAddPredefinedMap.setEnabled(false);
                                    	comboBoxTrip.setEnabled(false);
                                    	btnAddRoad.setEnabled(false);
                                    	btnStart.setEnabled(false);
                                    	((MapPanel) panel_4).beginningVertex = null;
                     			    	((MapPanel) panel_4).choosingPoints = true;
                                    	((MapPanel) panel_4).beginningPoint = true;
                                    	((MapPanel) panel_4).choosePoints();
                                
                                	} 
                                	else 
                                		if(chosenOption == "End")
                                	{
                                		/* Disable Other Options */
                                        comboBox_Weather.setEnabled(false);
                                        comboBox_Traffic.setEnabled(false);
                                        comboBox_InterestPoint.setEnabled(false);
                                        btnAddPredefinedMap.setEnabled(false);
                                        comboBoxTrip.setEnabled(false);
                                        btnAddRoad.setEnabled(false);
                                        btnStart.setEnabled(false);
                                        ((MapPanel) panel_4).endVertex = null;
                                        ((MapPanel) panel_4).choosingPoints = true;
                                        ((MapPanel) panel_4).endPoint = true;
                                		((MapPanel) panel_4).choosePoints();
                                		
                                	}
                                		
                             }
                         });
                         
                         JButton btnReset = new JButton("Reset");
                         btnReset.addActionListener(new ActionListener() {
                             
                             public void actionPerformed(ActionEvent e) {
                             	
                            	window.getFrame().setVisible(false);
                             	window = new WindowUI();
                                window.getFrame().setVisible(true); 
  
                             }
                         });
                         
                         btnStart = new JButton("Start");
                         btnStart.addActionListener(new ActionListener() {
                         	public void actionPerformed(ActionEvent e) {
                         		
                         		/* Map Timer */
                         		mapTimer = new Timer((int) world.getUpdateTime(), new ActionListener() {
                         			    public void actionPerformed(ActionEvent e) {
                         				
                         			    	
                         			    	world.updateRoadsStates();
                             				((MapPanel) panel_4).weather = world.getCurrentWeather();
                             				((MapPanel) panel_4).traffic = world.getCurrentTraffic();
                             				((MapPanel) panel_4).repaint();

                         			        if (world.getAtDestination()) {
                         			        	mapTimer.stop();
                         			        }
                         			    }    
                         			});
                         			
                         		
                         		startSimulation();
   
                         	}
                         });
                         
                         btnAddPredefinedMap = new JButton("Add Predefined Map");
                         btnAddPredefinedMap.addActionListener(new ActionListener() {
                         	public void actionPerformed(ActionEvent e) {
                                 
                                 /* Add predefined map */
                                 
                                 // TODO Set second vertexes as neighbor of the first vertexes
                         		// TODO Set first vertexes as neighbor of the second vertexes
                                 // TODO add all edges to map of edges
                                 // TODO add interest points to edges
                                 // TODO add traffic to edges
                                 // TODO add weather to edges

                                 ArrayList<Vertex> pairOfVertexes = new ArrayList<Vertex>();
                                 Vertex firstVertex = null;
                                 Vertex secondVertex = null;
    			

                                 // Add new roads to the roads of the map
                                 firstVertex = new Vertex(96, 41);
                                 secondVertex = new Vertex(73, 104);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			// TODO add interest point to edge
    			((MapPanel) panel_4).addTraffic("High");
    			// TODO add traffic to edge
    			((MapPanel) panel_4).addWeather("Sunny");
    			// TODO add weather to edge
    			
    			firstVertex = new Vertex(73, 104);
                                 secondVertex = new Vertex(126, 120);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(126, 120);
                                 secondVertex = new Vertex(68, 162);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(126, 120);
                                 secondVertex = new Vertex(148, 67);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Light Rain");
    			
    			firstVertex = new Vertex(126, 120);
                                 secondVertex = new Vertex(253, 106);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("Moderate");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(253, 106);
                                 secondVertex = new Vertex(237, 42);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(253, 106);
                                 secondVertex = new Vertex(308, 141);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(308, 141);
                                 secondVertex = new Vertex(345, 39);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("Stopped");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(308, 141);
                                 secondVertex = new Vertex(296, 175);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("Moderate");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(308, 141);
                                 secondVertex = new Vertex(235, 182);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("High Rain");
    			
    			firstVertex = new Vertex(296, 175);
                                 secondVertex = new Vertex(361, 124);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(361, 124);
                                 secondVertex = new Vertex(398, 51);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(398, 51);
                                 secondVertex = new Vertex(408, 78);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(235, 182);
                                 secondVertex = new Vertex(175, 164);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Storm");
    			
    			firstVertex = new Vertex(175, 164);
                                 secondVertex = new Vertex(127, 192);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(127, 192);
                                 secondVertex = new Vertex(86, 179);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Snow");
    			
    			firstVertex = new Vertex(86, 179);
                                 secondVertex = new Vertex(68, 162);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(86, 179);
                                 secondVertex = new Vertex(73, 204);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(73, 204);
                                 secondVertex = new Vertex(107, 239);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Cloudy");
    			
    			firstVertex = new Vertex(107, 239);
                                 secondVertex = new Vertex(127, 192);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(127, 192);
                                 secondVertex = new Vertex(142, 250);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("Light");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(142, 250);
                                 secondVertex = new Vertex(130, 302);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(142, 250);
                                 secondVertex = new Vertex(193, 330);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(193, 330);
                                 secondVertex = new Vertex(234, 225);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(234, 225);
                                 secondVertex = new Vertex(235, 182);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(107, 239);
                                 secondVertex = new Vertex(67, 241);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(107, 239);
                                 secondVertex = new Vertex(89, 274);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Light Rain");
    			
    			firstVertex = new Vertex(89, 274);
                                 secondVertex = new Vertex(130, 302);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(89, 274);
                                 secondVertex = new Vertex(51, 309);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("High");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(89, 274);
                                 secondVertex = new Vertex(66, 338);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(51, 309);
                                 secondVertex = new Vertex(30, 376);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(66, 338);
                                 secondVertex = new Vertex(47, 392);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(66, 338);
                                 secondVertex = new Vertex(137, 344);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(66, 338);
                                 secondVertex = new Vertex(120, 398);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(66, 338);
                                 secondVertex = new Vertex(120, 398);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(120, 398);
                                 secondVertex = new Vertex(110, 438);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(110, 438);
                                 secondVertex = new Vertex(100, 500);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(110, 438);
                                 secondVertex = new Vertex(151, 480);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(151, 480);
                                 secondVertex = new Vertex(218, 474);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(218, 474);
                                 secondVertex = new Vertex(155, 379);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(155, 379);
                                 secondVertex = new Vertex(137, 344);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("High Rain");
    			
    			firstVertex = new Vertex(193, 330);
                                 secondVertex = new Vertex(342, 310);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(342, 310);
                                 secondVertex = new Vertex(346, 376);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(342, 310);
                                 secondVertex = new Vertex(218, 474);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(218, 474);
                                 secondVertex = new Vertex(283, 440);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(346, 376);
                                 secondVertex = new Vertex(356, 447);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("Light");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(356, 447);
                                 secondVertex = new Vertex(370, 494);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(218, 474);
                                 secondVertex = new Vertex(305, 531);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(305, 531);
                                 secondVertex = new Vertex(197, 556);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(197, 556);
                                 secondVertex = new Vertex(241, 585);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(305, 531);
                                 secondVertex = new Vertex(426, 521);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Hail");
    			
    			firstVertex = new Vertex(426, 521);
                                 secondVertex = new Vertex(370, 494);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(346, 376);
                                 secondVertex = new Vertex(513, 431);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(513, 431);
                                 secondVertex = new Vertex(546, 406);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(296, 175);
                                 secondVertex = new Vertex(292, 246);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(292, 246);
                                 secondVertex = new Vertex(328, 223);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("High");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(292, 246);
                                 secondVertex = new Vertex(356, 265);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(328, 223);
                                 secondVertex = new Vertex(415, 167);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(415, 167);
                                 secondVertex = new Vertex(422, 114);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(415, 167);
                                 secondVertex = new Vertex(542, 125);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("High");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(542, 125);
                                 secondVertex = new Vertex(514, 165);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("Moderate");
    			((MapPanel) panel_4).addWeather("Light Rain");
    			
    			firstVertex = new Vertex(514, 165);
                                 secondVertex = new Vertex(599, 166);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(422, 114);
                                 secondVertex = new Vertex(469, 82);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(469, 82);
                                 secondVertex = new Vertex(447, 50);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("High");
    			((MapPanel) panel_4).addWeather("Snow");
    			
    			firstVertex = new Vertex(469, 82);
                                 secondVertex = new Vertex(657, 95);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(599, 166);
                                 secondVertex = new Vertex(629, 201);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(629, 201);
                                 secondVertex = new Vertex(680, 176);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(680, 176);
                                 secondVertex = new Vertex(720, 123);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(356, 265);
                                 secondVertex = new Vertex(388, 247);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(388, 247);
                                 secondVertex = new Vertex(447, 299);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(447, 299);
                                 secondVertex = new Vertex(508, 263);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("Low");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(508, 263);
                                 secondVertex = new Vertex(561, 291);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("Low");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(356, 265);
                                 secondVertex = new Vertex(457, 374);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("None");
    			((MapPanel) panel_4).addWeather("Sunny");
    			
    			firstVertex = new Vertex(457, 374);
                                 secondVertex = new Vertex(548, 335);
                                 pairOfVertexes = new ArrayList<Vertex>();
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).addRoad(pairOfVertexes);
    			((MapPanel) panel_4).addInterestPoint(false);
    			((MapPanel) panel_4).addTraffic("High");
    			((MapPanel) panel_4).addWeather("Light Rain");
    			
    			 world.setCurrentWeather(((MapPanel) panel_4).weather);
                                  world.setCurrentTraffic(((MapPanel) panel_4).traffic);
                                  update();

    			((MapPanel) panel_4).repaint();
                         	}
                         });
                                 
                                 JSeparator separator = new JSeparator();
                                 
                                 JSeparator separator_1 = new JSeparator();
                                 
                                 JSeparator separator_2 = new JSeparator();
                                 
                                 JLabel lblMaxDistance = new JLabel("Max. Distance:");
                                 
                                 JSpinner spinner = new JSpinner();
                                 
                                 JLabel lblMaxDetours = new JLabel("Max. Detours:");
                                 
                                 JSpinner spinner_1 = new JSpinner();
                                 
                                 JLabel lblTripOptions = new JLabel("Trip Options:");
                                 
                                 comboBoxTrip = new JComboBox(trip);
                                 
                         
                                 GroupLayout gl_panel_3 = new GroupLayout(panel_3);
                                 gl_panel_3.setHorizontalGroup(
                                 	gl_panel_3.createParallelGroup(Alignment.TRAILING)
                                 		.addGroup(gl_panel_3.createSequentialGroup()
                                 			.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                 				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
                                 					.addContainerGap()
                                 					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                 						.addGroup(gl_panel_3.createSequentialGroup()
                                 							.addComponent(comboBox_Weather, 0, 183, Short.MAX_VALUE)
                                 							.addPreferredGap(ComponentPlacement.RELATED))
                                 						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                 						.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                 						.addGroup(gl_panel_3.createSequentialGroup()
                                 							.addGap(6)
                                 							.addComponent(lbl_Weather)
                                 							.addPreferredGap(ComponentPlacement.RELATED))
                                 						.addGroup(gl_panel_3.createSequentialGroup()
                                 							.addGap(6)
                                 							.addComponent(lblTrnsito)
                                 							.addPreferredGap(ComponentPlacement.RELATED))
                                 						.addGroup(gl_panel_3.createSequentialGroup()
                                 							.addGap(6)
                                 							.addComponent(lblPickPoint)
                                 							.addPreferredGap(ComponentPlacement.RELATED)))
                                 					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
                                 				.addGroup(gl_panel_3.createSequentialGroup()
                                 					.addContainerGap()
                                 					.addComponent(lblTimePeriod))
                                 				.addGroup(gl_panel_3.createSequentialGroup()
                                 					.addContainerGap()
                                 					.addComponent(rdbtnDay))
                                 				.addGroup(gl_panel_3.createSequentialGroup()
                                 					.addContainerGap()
                                 					.addComponent(rdbtnNight))
                                 				.addGroup(gl_panel_3.createSequentialGroup()
                                 					.addContainerGap()
                                 					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                 						.addComponent(comboBox_Traffic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 						.addGroup(gl_panel_3.createSequentialGroup()
                                 							.addGap(6)
                                 							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                 								.addComponent(comboBox_InterestPoint, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                 								.addComponent(lblIsInterestPoint)
                                 								.addComponent(btnAddRoad)))))
                                 				.addGroup(gl_panel_3.createSequentialGroup()
                                 					.addContainerGap()
                                 					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                 						.addComponent(comboBoxPoints, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 						.addGroup(gl_panel_3.createSequentialGroup()
                                 							.addGap(6)
                                 							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                 								.addComponent(lblMaxDistance)
                                 								.addComponent(lblMaxDetours)
                                 								.addComponent(lblTripOptions))
                                 							.addPreferredGap(ComponentPlacement.RELATED)
                                 							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                 								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 								.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                 				.addGroup(gl_panel_3.createSequentialGroup()
                                 					.addContainerGap()
                                 					.addComponent(btnStart)
                                 					.addPreferredGap(ComponentPlacement.RELATED)
                                 					.addComponent(btnReset))
                                 				.addGroup(gl_panel_3.createSequentialGroup()
                                 					.addContainerGap()
                                 					.addComponent(btnAddPredefinedMap))
                                 				.addGroup(gl_panel_3.createSequentialGroup()
                                 					.addContainerGap()
                                 					.addComponent(comboBoxTrip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                 			.addContainerGap())
                                 );
                                 gl_panel_3.setVerticalGroup(
                                 	gl_panel_3.createParallelGroup(Alignment.LEADING)
                                 		.addGroup(gl_panel_3.createSequentialGroup()
                                 			.addContainerGap()
                                 			.addComponent(lblTimePeriod)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(rdbtnDay)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(rdbtnNight)
                                 			.addPreferredGap(ComponentPlacement.UNRELATED)
                                 			.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(lbl_Weather)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(comboBox_Weather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(lblTrnsito)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(comboBox_Traffic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(lblIsInterestPoint)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(comboBox_InterestPoint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(btnAddRoad)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                 				.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 				.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(lblPickPoint)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(comboBoxPoints, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
                                 				.addComponent(lblMaxDistance)
                                 				.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                 			.addGap(18)
                                 			.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
                                 				.addComponent(lblMaxDetours)
                                 				.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                 			.addGap(25)
                                 			.addComponent(lblTripOptions)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addComponent(comboBoxTrip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                 			.addGap(12)
                                 			.addComponent(btnAddPredefinedMap)
                                 			.addPreferredGap(ComponentPlacement.RELATED)
                                 			.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
                                 				.addComponent(btnStart)
                                 				.addComponent(btnReset))
                                 			.addGap(19))
                                 );
                                 panel_3.setLayout(gl_panel_3);
                                
        GroupLayout gl_panel_4 = new GroupLayout(panel_4);
        gl_panel_4.setHorizontalGroup(
        	gl_panel_4.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel_4.createSequentialGroup()
        			.addContainerGap(762, Short.MAX_VALUE)
        			.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        gl_panel_4.setVerticalGroup(
        	gl_panel_4.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_4.createSequentialGroup()
        			.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_4.setLayout(gl_panel_4);
       
        // Initialize map vertexes
        ArrayList<Vertex> mapVertexes = getMapVertexes();
        int numberOfVertexes = getNumberOfVertexes();

        if (mapVertexes.size() != numberOfVertexes) {
            
        	/* TESTING */
        	/*System.out.print("Something went wrong with the vertexes!");
            System.out.print("Number of vertexes: ");
            System.out.print(numberOfVertexes);
            System.out.print("mapVertexes size: ");
            System.out.print(mapVertexes.size());*/
            /* TESTING */

            world.setNumberOfVertexes(numberOfVertexes);
            world.setMapVertexes(mapVertexes);
        } else {
        	/* TESTING */
            /*System.out.print("Number of vertexes: ");
            System.out.print(numberOfVertexes + "\n");*/
            /* TESTING */
        }

    }
    
    public void startSimulation()
    {
    	/* Disable Options */
    	comboBox_Weather.setEnabled(false);
    	comboBox_Traffic.setEnabled(false);
    	comboBox_InterestPoint.setEnabled(false);
    	comboBoxTrip.setEnabled(false);
    	btnAddPredefinedMap.setEnabled(false);
    	btnAddRoad.setEnabled(false);
    	comboBoxPoints.setEnabled(false);
    	btnStart.setEnabled(false);

    	mapTimer.start(); 
  
    }

    public void update() {
        // Update map nodes
    	world.setMapVertexes(((MapPanel) panel_4).updateMapPoints());
    	world.setNumberOfVertexes(((MapPanel) panel_4).updateNumberOfPoints());
    }

    /* Get initial number of vertexes */
    public ArrayList<Vertex> getMapVertexes() {
        ArrayList<Vertex> mapVertexes = ((MapPanel) panel_4).getImagePoints();

        return mapVertexes;
    }

    /* Get initial number of nodes */
    public int getNumberOfVertexes() {
        int numberOfNodes = ((MapPanel) panel_4).getNumberOfPoints();

        return numberOfNodes;
    }

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
