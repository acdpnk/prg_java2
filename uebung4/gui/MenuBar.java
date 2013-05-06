package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class MenuBar extends JMenuBar{
	JMenu menu;
	JMenuItem help, close;
	Application app;
	boolean debug;

	private Application getApp(){
		return this.app;
	}

	public MenuBar(Application app, boolean debug){
		super();
		this.debug = debug;
		this.app = app;

		menu = new JMenu("simulation");

		help = new JMenuItem("help");
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				final Application fapp = getApp();
				JOptionPane.showMessageDialog(fapp, "you’re on your own.");
				System.out.println("you’re on your own.");
			}
		});

		close = new JMenuItem("close");
		close.addActionListener(new ActionListener(){
			final Application fapp = getApp();
			public void actionPerformed(ActionEvent ae){
				fapp.dispose();
			}
		});

		menu.add(help);
		menu.add(close);
		this.add(menu);
	}
}