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
import utilities.Vertex;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.util.ArrayList;



public class WindowUI {

    private JFrame frame;
    public JPanel panel_4;
    public WorldBDI wm;

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowUI window = new WindowUI();
                    window.frame.setVisible(true);
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
        wm = new WorldBDI();
        frame = new JFrame();
        frame.setBounds(100, 100, 822, 388);
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
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
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
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
        frame.getContentPane().add(panel_2, BorderLayout.WEST);
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
        frame.getContentPane().add(panel_3, BorderLayout.EAST);

        /* Time Period Options */
        ButtonGroup timePeriodButtons = new ButtonGroup();

        JLabel lblTimePeriod = new JLabel("Time Period");

        JRadioButton rdbtnDay = new JRadioButton("Day");

        JRadioButton rdbtnNight = new JRadioButton("Night");

        timePeriodButtons.add(rdbtnDay);
        timePeriodButtons.add(rdbtnNight);

        /* Wheather Options */
        JLabel lbl_Wheather = new JLabel("Weather");
        String[] wheatherStrings = {"Snow", "Hail", "High Rain", "Light Rain", "Cloudy", "Sunny"};
        JComboBox<Object> comboBox_Wheather = new JComboBox<Object>(wheatherStrings);

        /* Traffic Options */
        JLabel lblTrnsito = new JLabel("Traffic");
        String[] trafficStrings = {"None", "Light", "Moderate", "High", "Stopped"};
        JComboBox<Object> comboBox_Traffic = new JComboBox<Object>(trafficStrings);

        JButton btnAddRoad = new JButton("Add Road");
        btnAddRoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ((MapPanel) panel_4).choosePoints();
                update();

            }
        });

        JButton btnAddInterestPoint = new JButton("Add Interest Point");

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
                                        .addComponent(lbl_Wheather))
                                .addComponent(comboBox_Wheather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_panel_3.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblTrnsito))
                                .addComponent(comboBox_Traffic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddRoad)
                                .addComponent(btnAddInterestPoint))
                        .addContainerGap(26, Short.MAX_VALUE))
        );
        gl_panel_3.setVerticalGroup(
                gl_panel_3.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
                        .addGap(131)
                        .addComponent(lblTimePeriod)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(rdbtnDay)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(rdbtnNight)
                        .addGap(30)
                        .addComponent(lbl_Wheather)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(comboBox_Wheather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblTrnsito)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(comboBox_Traffic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(125)
                        .addComponent(btnAddRoad)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnAddInterestPoint)
                        .addContainerGap(124, Short.MAX_VALUE))
        );
        panel_3.setLayout(gl_panel_3);

        panel_4 = new MapPanel();
        frame.getContentPane().add(panel_4, BorderLayout.CENTER);
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
        ArrayList<Vertex> mapVertexes = getMapVertexes();
        int numberOfVertexes = getNumberOfVertexes();

        if (mapVertexes.size() != numberOfVertexes) {
            System.out.print("Something went wrong with the vertexes!");
            System.out.print("Number of vertexes: ");
            System.out.print(numberOfVertexes);
            System.out.print("mapVertexes size: ");
            System.out.print(mapVertexes.size());

            //wm.setNumberOfNodes(numberOfNodes);
            //wm.setMapNodes(mapNodes);
        } else {
            System.out.print("Number of vertexes: ");
            System.out.print(numberOfVertexes);
        }
        /* TODO */

    }

    public void update() {
        // Update map nodes
        wm.setMapVertexes(((MapPanel) panel_4).updateMapPoints());
        wm.setNumberOfVertexes(((MapPanel) panel_4).updateNumberOfPoints());
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

}
