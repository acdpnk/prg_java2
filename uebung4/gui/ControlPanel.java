package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Color;
import de.luh.sim.java13.ue3.simulatorcore.Simulator;

public class ControlPanel extends JPanel{
	Application app;
	boolean debug;
	JButton helpButton, closeButton, startSimButton;
	JTextField simTimeField;
	JLabel label;
	JPanel left, right;

	private Application getApp(){
	return app;
	}

	public ControlPanel(Application app, boolean debug){
		super();
		this.app = app;
		this.debug = debug;

		label = new JLabel("simulation time (in time units):");

		startSimButton = new JButton("run simulation");
		startSimButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				final Application fapp = getApp();

				int time = Integer.parseInt(simTimeField.getText());
				fapp.setSimulator(new Simulator(false, time, fapp.getModel()));
				fapp.getSimulator().init();
				fapp.getSimulator().run();
				fapp.setSimPanelTextArea(fapp.getSimulator().getResultFile());
			}
		});

		if(debug){
			setBackground(Color.PINK);
		}
		simTimeField = new JTextField(7);
		simTimeField.setHorizontalAlignment(JTextField.RIGHT);
		simTimeField.setText("15000");

		left = new JPanel();
		right = new JPanel();

		left.add(label);
		left.add(simTimeField);
		if(debug){
			left.setBackground(Color.CYAN);
		}

		right.add(startSimButton);
		if(debug){
			right.setBackground(Color.ORANGE);
		}

		add(left);
		add(right);
		setLayout(new GridLayout(1,2));
		//setLayout(new FlowLayout(FlowLayout.CENTER, 13, 23));

	}
}