package es.upm.dit.aled.lab5;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import es.upm.dit.aled.lab5.gui.Position2D;

/**
 * Extension of Area that maintains a strict queue of the Patients waiting to
 * enter in it. After a Patient exits, the first one in the queue will be
 * allowed to enter.
 * 
 * @author rgarciacarmona
 */
public class AreaQueue extends Area {
	
	private Queue<Patient> waitQueue;
	
	public AreaQueue(String name, int time, int capacity, Position2D position) {
		super(name, time, capacity, position);
		this.waitQueue = new LinkedList<Patient>();
	}

	@Override
	public synchronized void enter(Patient p) {
		try {		
			this.waiting++;
			this.waitQueue.add(p);
			while(numPatients>=capacity) {
				wait();
				
			}
			this.waitQueue.remove();
			this.numPatients++;
			this.waiting--;
		}catch (InterruptedException i) {}
		
	}
}
