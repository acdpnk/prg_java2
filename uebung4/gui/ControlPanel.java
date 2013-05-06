package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Color;

public class ControlPanel extends JPanel{
	Application app;
	boolean debug;
	JButton helpButton, closeButton, startSimButton;
	JTextField simTimeField;

	private Application getApp(){
	return app;
	}

	public ControlPanel(Application app, boolean debug){
		super();
		this.app = app;
		this.debug = debug;

		startSimButton = new JButton("run simulation");
		startSimButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int time = Integer.parseInt(simTimeField.getText());
				app.setSimulator(new simulatorcore.SimulatorSeq());
			}
		});

		simTimeField = new JTextField(15);
		simTimeField.setHorizontalAlignment(JTextField.RIGHT);
		simTimeField.setText("15000");

		add(simTimeField);
		add(startSimButton);

		setLayout(new FlowLayout(FlowLayout.RIGHT, 23, 23));

	}
}