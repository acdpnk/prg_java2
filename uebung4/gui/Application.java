package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class Application extends JFrame{
	ModelPanel modelPanel;
	SimulationPanel simPanel;
	ControlPanel controlPanel;
	boolean debug;
	String currentModelName;
	MenuBar menuBar;

	simulatorcore.SimulatorSeq simulator;

	public simulatorcore.SimulatorSeq getSimulator() {return this.simulator;}
	public void setSimulator(simulatorcore.SimulatorSeq sim) {this.simulator = sim;}

	public String getCurrentModelName() {return currentModelName;}
	public void setCurrentModelName(String name) {currentModelName = name;}

	public JButton getModelPanelSaveButton(){
		return modelPanel.getSaveButton();
	}
	public JButton getModelPanelLoadButton(){
		return modelPanel.getLoadButton();
	}
	public JTextArea getModelTextArea(){
		return modelPanel.getTextArea();
	}

	public Application(boolean debug){
		super();
		this.debug = debug;

		this.currentModelName = "";

		//control = new SimulationController(this);

		modelPanel = new ModelPanel(this, debug);
		simPanel = new SimulationPanel(this, debug);
		controlPanel = new ControlPanel(this, debug);
		menuBar = new MenuBar(this, debug);
		// getModelPanelSaveButton().addActionListener(control);
		// getModelPanelLoadButton().addActionListener(control);

		setJMenuBar(menuBar);
		add(modelPanel, BorderLayout.WEST);
		add(simPanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public Application(){
		this(false);
	}

}