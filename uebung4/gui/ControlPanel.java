package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Color;
import de.luh.sim.java13.ue3.simulatorcore.Simulator;

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
				final Application fapp = getApp();

				int time = Integer.parseInt(simTimeField.getText());
				fapp.setSimulator(new Simulator(true, time, fapp.getModel()));
				fapp.getSimulator().init();
				fapp.getSimulator().run();
				fapp.setSimPanelTextArea(fapp.getSimulator().getResultFile());
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