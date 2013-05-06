package de.luh.sim.java13.ue3.simulatorcore;

import java.util.ArrayList;
import java.util.List;

import de.luh.sim.java13.ue3.simulationmodel.IEreignis;

public class EreignisListe implements IEreignisliste {
	
	private List<IEreignis> list;
	
	public EreignisListe() {
		list = new ArrayList<IEreignis>();
	}

	public void einsortieren(IEreignis neuesEreignis) {
		if (list.isEmpty()) { // Sonderfall
			list.add(0,neuesEreignis);
			return;
		}
		int i=0;
		for (IEreignis o : list) {
			if (neuesEreignis.getAusfuehrungszeit()<o.getAusfuehrungszeit()) {
				list.add(i, neuesEreignis);
				return;
			}
			i++;
		}
		list.add(neuesEreignis);
	}

	public void erstesEreignisEntfernen() {
		list.remove(0);
	}

	public boolean hatNochInhalt() {
		return !list.isEmpty();
	}

	public IEreignis naechstesEreignis() {
		return hatNochInhalt() ? list.get(0) : null;
	}

}
