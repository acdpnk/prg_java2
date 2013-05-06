package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ModelPanel extends JPanel {
	JButton loadButton, saveButton;
	JScrollPane scrollPane;
	JTextArea textArea;
	JPanel text, buttons, all;
	Application app;
	JLabel label;

	private Application getApp(){
		return app;
	}

	public ModelPanel(Application app, boolean debug){
		super();

		this.app = app;

		if(debug) setBackground(Color.GREEN);

		loadButton = new JButton("load model");
		loadButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JFileChooser chooser = new JFileChooser();
				final Application fapp = getApp();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("simulation models", "model");
				chooser.setFileFilter(filter);

				int option = chooser.showOpenDialog(fapp);
				if(option == JFileChooser.APPROVE_OPTION){
					try{
						BufferedReader reader = new BufferedReader(new FileReader(chooser.getSelectedFile()));
						textArea.read(reader, null);
						reader.close();
						textArea.requestFocus();
						fapp.setCurrentModelName(chooser.getSelectedFile().getName().replace(".model", ""));
					}
					catch(Exception e){
						System.out.println(e + "\ncould not open file.");
					}
				}
			}
		});

		saveButton = new JButton("save model");
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JFileChooser chooser = new JFileChooser();
				final Application fapp = getApp();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("simulation models", "model");
				chooser.setFileFilter(filter);
				chooser.setSelectedFile(new File(fapp.getCurrentModelName() + ".model"));

				int option = chooser.showSaveDialog(fapp);
				if(option == JFileChooser.APPROVE_OPTION){
					try{
						BufferedWriter writer = new BufferedWriter(new FileWriter(chooser.getSelectedFile()));
						textArea.write(writer);
						writer.close();
						textArea.requestFocus();
					}
					catch(Exception e){
						System.out.println(e + "\ncould not open file.");
					}
				}
			}
		});

		textArea = new JTextArea(25, 30);
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setLineWrap(true);
		textArea.setEditable(true);
		all = new JPanel(new BorderLayout());
		text = new JPanel();
		buttons = new JPanel();
		label = new JLabel(" ");
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(JLabel.CENTER);

		text.add(scrollPane);

		buttons.add(loadButton);
		buttons.add(saveButton);
		all.add(text, BorderLayout.NORTH);
		all.add(label, BorderLayout.CENTER);
		all.add(buttons, BorderLayout.SOUTH);
		this.add(all);
	}

	public ModelPanel(Application app){
		this(app, false);
	}

	public JButton getSaveButton(){
		return saveButton;
	}
	public JButton getLoadButton(){
		return loadButton;
	}
	public JTextArea getTextArea(){
		return textArea;
	}
	public File getModel(){
		File model = new File("model");
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(model));
			textArea.write(writer);
			writer.close();
			textArea.requestFocus();
		}
		catch(Exception e){
			System.out.println(e + "\ncould not open file.");
		}
		return model;
	}
}