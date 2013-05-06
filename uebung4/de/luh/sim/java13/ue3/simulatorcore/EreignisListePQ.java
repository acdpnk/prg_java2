package de.luh.sim.java13.ue3.simulatorcore;

import java.util.PriorityQueue;

import de.luh.sim.java13.ue3.simulationmodel.IEreignis;

public class EreignisListePQ implements IEreignisliste{

	PriorityQueue<IEreignis> pq;
	
	public EreignisListePQ(){
		pq = new PriorityQueue<IEreignis>();
	}
	
	@Override
	public void einsortieren(IEreignis neuesEreignis) {
		pq.add(neuesEreignis);
		
	}

	@Override
	public IEreignis naechstesEreignis() {
		return pq.peek();
	}

	@Override
	public void erstesEreignisEntfernen() {
		pq.poll();
		
	}

	@Override
	public boolean hatNochInhalt() {
		return !pq.isEmpty();
	}

}
