package es.ucm.fdi.model.events;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import es.ucm.fdi.model.RoadMap.RoadMap;
import es.ucm.fdi.model.trafficSimulator.SimulatorException;
/**
 * Make a vehicle in the RoadMap faulty
 * @author Carla Martínez, Beatriz Herguedas
 *
 */
public class MakeFaultyVehicleEvent extends Event {
	private ArrayList<String> id = new ArrayList<>();
	private int duration;
	/**
	 * Constructor
	 * @param time
	 * @param id
	 * @param duration
	 */
	public MakeFaultyVehicleEvent(int time, ArrayList<String> id, int duration) {
		super(time);
		this.id = id;
		this.duration = duration;
	}
	/**
	 * Make an existing vehicle in the RoadMap faulty 
	 */
	public void execute(RoadMap m) throws SimulatorException {
		for(int i = 0; i < id.size(); i++){
			try{
				m.getVehicle(id.get(i)).setFaultyTime(duration);
				m.getVehicle(id.get(i)).setActualSpeed(0);
			}catch(NoSuchElementException e){
				throw new SimulatorException("There has been a problem while making vehicle faulty ", e);
			}
		}
	}
}
