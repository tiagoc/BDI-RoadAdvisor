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



public class WindowUI {

	static WindowUI window;
    private JFrame frame;
    public JPanel panel_4;
    public WorldBDI world;
    public Timer mapTimer;
    JComboBox<Object> comboBox_Weather;
    JComboBox<Object> comboBox_Traffic;
    JComboBox<Object> comboBox_InterestPoint;
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
        getFrame().setSize(1000, 700);
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

        JPanel panel_3 = new JPanel();
        getFrame().getContentPane().add(panel_3, BorderLayout.EAST);

        /* Time Period Options */
        ButtonGroup timePeriodButtons = new ButtonGroup();
        JLabel lblTimePeriod = new JLabel("Time Period");
        JRadioButton rdbtnDay = new JRadioButton("Day");
        JRadioButton rdbtnNight = new JRadioButton("Night");

        timePeriodButtons.add(rdbtnDay);
        timePeriodButtons.add(rdbtnNight);

        /* Weather Options */
        JLabel lbl_Weather = new JLabel("Weather");
        String[] WeatherStrings = {"Storm", "Snow", "Hail", "High Rain", "Light Rain", "Cloudy", "Sunny"};
        comboBox_Weather = new JComboBox<Object>(WeatherStrings);
        comboBox_Weather.setSelectedIndex(0);
        comboBox_Weather.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {

            	@SuppressWarnings("unchecked")
				JComboBox<Object> src = (JComboBox<Object>)e.getSource();
            	((MapPanel) panel_4).chosenWeather = (String)src.getSelectedItem();
            }
        });

        /* Traffic Options */
        JLabel lblTrnsito = new JLabel("Traffic");
        String[] trafficStrings = {"None", "Light", "Moderate", "High", "Stopped"};
        comboBox_Traffic = new JComboBox<Object>(trafficStrings);
        comboBox_Traffic.setSelectedIndex(0);
        comboBox_Traffic.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {

            	@SuppressWarnings("unchecked")
				JComboBox<Object> src = (JComboBox<Object>)e.getSource();
            	 ((MapPanel) panel_4).chosenTraffic = (String)src.getSelectedItem();
            }
        });

        /* Interest Point */
        JLabel lblIsInterestPoint = new JLabel("Is An Interest Point?");
        String[] options = {"Yes", "No"};
        comboBox_InterestPoint = new JComboBox<Object>(options);
        comboBox_InterestPoint.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {

            	@SuppressWarnings("unchecked")
				JComboBox<Object> src = (JComboBox<Object>)e.getSource();
                ((MapPanel) panel_4).interestPoint = (String)src.getSelectedItem();
            }
        });
       
        btnAddRoad = new JButton("Add Road");
        btnAddRoad.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {

                ((MapPanel) panel_4).choosePoints();
                world.setCurrentWeather(((MapPanel) panel_4).weather);
                world.setCurrentTraffic(((MapPanel) panel_4).traffic);
                update();

            }
        });
        
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	
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
        		
        		/* Reset window */
        		window = new WindowUI();
                window.getFrame().setVisible(true); 
                
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
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			// TODO add interest point to edge
    			((MapPanel) panel_4).traffic.add("None");
    			// TODO add traffic to edge
    			((MapPanel) panel_4).weather.add("Sunny");
    			// TODO add weather to edge
    			
    			firstVertex = new Vertex(73, 104);
                secondVertex = new Vertex(126, 120);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(126, 120);
                secondVertex = new Vertex(68, 162);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(126, 120);
                secondVertex = new Vertex(148, 67);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(126, 120);
                secondVertex = new Vertex(253, 106);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(253, 106);
                secondVertex = new Vertex(237, 42);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(253, 106);
                secondVertex = new Vertex(308, 141);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(308, 141);
                secondVertex = new Vertex(345, 39);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(308, 141);
                secondVertex = new Vertex(296, 175);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(308, 141);
                secondVertex = new Vertex(235, 182);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(296, 175);
                secondVertex = new Vertex(361, 124);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(361, 124);
                secondVertex = new Vertex(398, 51);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(398, 51);
                secondVertex = new Vertex(408, 78);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(235, 182);
                secondVertex = new Vertex(175, 164);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(175, 164);
                secondVertex = new Vertex(127, 192);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(127, 192);
                secondVertex = new Vertex(86, 179);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(86, 179);
                secondVertex = new Vertex(68, 162);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(86, 179);
                secondVertex = new Vertex(73, 204);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(73, 204);
                secondVertex = new Vertex(107, 239);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(107, 239);
                secondVertex = new Vertex(127, 192);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(127, 192);
                secondVertex = new Vertex(142, 250);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(142, 250);
                secondVertex = new Vertex(130, 302);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(142, 250);
                secondVertex = new Vertex(193, 330);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(193, 330);
                secondVertex = new Vertex(234, 225);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(234, 225);
                secondVertex = new Vertex(235, 182);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(107, 239);
                secondVertex = new Vertex(67, 241);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(107, 239);
                secondVertex = new Vertex(89, 274);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(89, 274);
                secondVertex = new Vertex(130, 302);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(89, 274);
                secondVertex = new Vertex(51, 309);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(89, 274);
                secondVertex = new Vertex(66, 338);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(51, 309);
                secondVertex = new Vertex(30, 376);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(66, 338);
                secondVertex = new Vertex(47, 392);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(66, 338);
                secondVertex = new Vertex(137, 344);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(66, 338);
                secondVertex = new Vertex(120, 398);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(66, 338);
                secondVertex = new Vertex(120, 398);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(120, 398);
                secondVertex = new Vertex(110, 438);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(110, 438);
                secondVertex = new Vertex(100, 500);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(110, 438);
                secondVertex = new Vertex(151, 480);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(151, 480);
                secondVertex = new Vertex(218, 474);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(218, 474);
                secondVertex = new Vertex(155, 379);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(155, 379);
                secondVertex = new Vertex(137, 344);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(193, 330);
                secondVertex = new Vertex(342, 310);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(342, 310);
                secondVertex = new Vertex(346, 376);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(342, 310);
                secondVertex = new Vertex(218, 474);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(218, 474);
                secondVertex = new Vertex(283, 440);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(346, 376);
                secondVertex = new Vertex(356, 447);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(356, 447);
                secondVertex = new Vertex(370, 494);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(218, 474);
                secondVertex = new Vertex(305, 531);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(305, 531);
                secondVertex = new Vertex(197, 556);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(197, 556);
                secondVertex = new Vertex(241, 585);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(305, 531);
                secondVertex = new Vertex(426, 521);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(426, 521);
                secondVertex = new Vertex(370, 494);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(346, 376);
                secondVertex = new Vertex(513, 431);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(513, 431);
                secondVertex = new Vertex(546, 406);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(296, 175);
                secondVertex = new Vertex(292, 246);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(292, 246);
                secondVertex = new Vertex(328, 223);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(292, 246);
                secondVertex = new Vertex(356, 265);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(328, 223);
                secondVertex = new Vertex(415, 167);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(415, 167);
                secondVertex = new Vertex(422, 114);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(415, 167);
                secondVertex = new Vertex(542, 125);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(542, 125);
                secondVertex = new Vertex(514, 165);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(514, 165);
                secondVertex = new Vertex(599, 166);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(422, 114);
                secondVertex = new Vertex(469, 82);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(469, 82);
                secondVertex = new Vertex(447, 50);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(469, 82);
                secondVertex = new Vertex(657, 95);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(599, 166);
                secondVertex = new Vertex(629, 201);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(629, 201);
                secondVertex = new Vertex(680, 176);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(680, 176);
                secondVertex = new Vertex(720, 123);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(356, 265);
                secondVertex = new Vertex(388, 247);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(388, 247);
                secondVertex = new Vertex(447, 299);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(447, 299);
                secondVertex = new Vertex(508, 263);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(508, 263);
                secondVertex = new Vertex(561, 291);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(356, 265);
                secondVertex = new Vertex(457, 374);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");
    			
    			firstVertex = new Vertex(457, 374);
                secondVertex = new Vertex(548, 335);
    			pairOfVertexes.add(firstVertex);
    			pairOfVertexes.add(secondVertex);
    			((MapPanel) panel_4).mapRoads.add(pairOfVertexes);
    			((MapPanel) panel_4).interestPoints.add(false);
    			((MapPanel) panel_4).traffic.add("None");
    			((MapPanel) panel_4).weather.add("Sunny");

    			((MapPanel) panel_4).repaint();
        	}
        });

        GroupLayout gl_panel_3 = new GroupLayout(panel_3);
        gl_panel_3.setHorizontalGroup(
        	gl_panel_3.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_3.createSequentialGroup()
        			.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel_3.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblTimePeriod))
        				.addComponent(rdbtnDay)
        				.addComponent(rdbtnNight)
        				.addGroup(gl_panel_3.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lbl_Weather))
        				.addComponent(comboBox_Weather, 0, 131, Short.MAX_VALUE)
        				.addGroup(gl_panel_3.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblTrnsito))
        				.addComponent(comboBox_Traffic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel_3.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblIsInterestPoint))
        				.addComponent(comboBox_InterestPoint, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnReset)
        				.addComponent(btnAddRoad)
        				.addComponent(btnAddPredefinedMap)
        				.addComponent(btnStart))
        			.addContainerGap())
        );
        gl_panel_3.setVerticalGroup(
        	gl_panel_3.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_3.createSequentialGroup()
        			.addGap(131)
        			.addComponent(lblTimePeriod)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(rdbtnDay)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(rdbtnNight)
        			.addGap(30)
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
        			.addGap(42)
        			.addComponent(btnAddRoad)
        			.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
        			.addComponent(btnStart)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnAddPredefinedMap)
        			.addGap(3)
        			.addComponent(btnReset)
        			.addGap(24))
        );
        panel_3.setLayout(gl_panel_3);

        panel_4 = new MapPanel();
        getFrame().getContentPane().add(panel_4, BorderLayout.CENTER);
        GroupLayout gl_panel_4 = new GroupLayout(panel_4);
        gl_panel_4.setHorizontalGroup(
                gl_panel_4.createParallelGroup(Alignment.LEADING)
                .addGap(0, 980, Short.MAX_VALUE)
        );
        gl_panel_4.setVerticalGroup(
                gl_panel_4.createParallelGroup(Alignment.LEADING)
                .addGap(0, 658, Short.MAX_VALUE)
        );
        panel_4.setLayout(gl_panel_4);

        /* TODO */
        // Initialize map vertexes
        ArrayList<Vertex> mapVertexes = getMapVertexes();
        int numberOfVertexes = getNumberOfVertexes();

        if (mapVertexes.size() != numberOfVertexes) {
            System.out.print("Something went wrong with the vertexes!");
            System.out.print("Number of vertexes: ");
            System.out.print(numberOfVertexes);
            System.out.print("mapVertexes size: ");
            System.out.print(mapVertexes.size());

            world.setNumberOfVertexes(numberOfVertexes);
            world.setMapVertexes(mapVertexes);
        } else {
            System.out.print("Number of vertexes: ");
            System.out.print(numberOfVertexes);
        }
        /* TODO */

    }
    
    public void startSimulation()
    {
    	/* Disable Options */
    	comboBox_Weather.setEnabled(false);
    	comboBox_Traffic.setEnabled(false);
    	comboBox_InterestPoint.setEnabled(false);
    	btnAddRoad.setEnabled(false);
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
