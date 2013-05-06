/**
 *
 */
package de.luh.sim.java13.ue3.simulatorcore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.luh.sim.java13.ue3.simulationmodel.Ankunft;
import de.luh.sim.java13.ue3.simulationmodel.IEreignis;
import de.luh.sim.java13.ue3.simulationmodel.ISimulationEntity;
import de.luh.sim.java13.ue3.simulationmodel.Warteschlange;

/**
 * @author Ronald
 *
 */
public class Simulator extends BasicSimulator {

	private final int SIMULATION_LENGTH;

	private boolean debug;

	private File file;

	private File resultFile;

	public File getResultFile(){
		return resultFile;
	}

	public Simulator(boolean debug, int simulationLength, File file) {
		super();
		SIMULATION_LENGTH = simulationLength;
		ereignisliste = new EreignisListePQ();
		this.debug = debug;
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.luh.sim.java09.ue08.BasicSimulator#init()
	 */
	@Override
	public void init() {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String nextLine = br.readLine();
			while(nextLine.startsWith("%")){
				nextLine = br.readLine();
			}
			double ankunftsrate = Double.parseDouble(nextLine);
			nextLine = br.readLine();
			while (nextLine != null) {
				if(!nextLine.startsWith("%")){
					String[] warteschlangeninformation = nextLine.split(" ");
					if (warteschlangeninformation.length > 1) {
						double bedienrate = Double
								.parseDouble(warteschlangeninformation[1]);
						addSimulationEntity(new Warteschlange(bedienrate,
								warteschlangeninformation[0]));
					}
				}
				nextLine = br.readLine();
			}
			IEreignis ersteAnkunft = new Ankunft(1 / ankunftsrate, debug,
					ankunftsrate);
			neuesEreignisIntegrieren(ersteAnkunft);
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Datei konnte nicht gelesen werden!");
			System.out.println("Beende Simulation");
			// System.exit(0);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Ankunftsrate nicht lesbar");
			System.out.println("Beende Simulation");
			// System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.luh.sim.java09.ue08.BasicSimulator#run()
	 */
	@Override
	public void run() {
		// hole zeitlich nächstes Ereignis
		IEreignis aktuellesEreignis = ereignisliste.naechstesEreignis();
		// solange noch ein Ereignis in der Liste ist, und nicht
		// ein Zeitlimit erreicht ist ...
		while (aktuellesEreignis != null && simulationszeit < SIMULATION_LENGTH) {
			// erhöhe Simulationszeit
			simulationszeit = aktuellesEreignis.getAusfuehrungszeit();
			aktualisiereWarteschlangenlaenge();
			// aktiviere Ereignis
			aktuellesEreignis.ereignisAusfuehren(this);

			// und hole das nächste Ereignis
			aktuellesEreignis = ereignisliste.naechstesEreignis();

			if (debug) {
				for (int i = 0; i < simulationsEntity.size(); i++) {
					System.out
							.printf("Simulationszeit: %6.2f  Laenge der Warteschlange %s: %2d\n",
									simulationszeit,
									simulationsEntity.get(i).getName(),
									simulationsEntity.get(i).getZustand());
				}
			}
		}
		aktualisiereWarteschlangenlaenge();

		try {
			String ergebnisname = file.getAbsolutePath();
			ergebnisname = ergebnisname.substring(0, ergebnisname.length() - 6);
			ergebnisname += "_Simulationsergebnis.txt";
			resultFile = new File(ergebnisname);
			FileWriter fw = new FileWriter(resultFile, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append("Simulierte Zeit: " + simulationszeit + "\n \n");
			bw.append("%Kenngrößen der Warteschlangen \n");
			for (int i = 0; i < simulationsEntity.size(); i++) {
				System.out
						.println("Mittlere Warteschlangenlänge der Warteschlange "
								+ simulationsEntity.get(i).getName()
								+ ": "
								+ (summeWarteschlangenlaenge.get(i) / simulationszeit));
				bw.append(simulationsEntity.get(i).getName() + " " + simulationsEntity.get(i).getBedienrate()
						+ " "
						+ (summeWarteschlangenlaenge.get(i) / simulationszeit) + "\n");
			}
			bw.append("\n");
			bw.append("%Kenngrößen des Gesamtsystems \n");
			double durchsatz = abgefertigteObjekte / simulationszeit;
			System.out.println("Durchsatz: " + durchsatz);
			bw.append("DSZ: " + durchsatz + "\n");
			double umlaufbestand = 0;
			for (int i = 0; i < summeWarteschlangenlaenge.size(); i++) {
				umlaufbestand += summeWarteschlangenlaenge.get(i) / simulationszeit;
			}
			System.out.println("Work in Process " + umlaufbestand);
			bw.append("WIP " + umlaufbestand + "\n" );
			System.out.println("Durchlaufzeit " + umlaufbestand / durchsatz);
			bw.append("DLZ " + umlaufbestand / durchsatz);

			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
