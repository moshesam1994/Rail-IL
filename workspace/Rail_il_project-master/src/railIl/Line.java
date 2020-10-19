package railIl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Line {
	private LocalTime frequencyrTime;

	private ArrayList<Route> allStops;

	public Line(ArrayList<Route> allStops) {
		this.allStops = allStops;
	}

	public Line(ArrayList<Route> allStops, int hourDelay, int minutesDelay) {// constructor with time delay
		this.allStops = new ArrayList<>();
		for (Route r : allStops) {
			this.allStops.add(new Route(r.getDepartureTime().plusHours(hourDelay).plusMinutes(minutesDelay),
					r.getArrivalTime().plusHours(hourDelay).plusMinutes(minutesDelay), r.getDeparturePlace(),
					r.getArrivalPlace()));
		}
	}

	public Route getLastStop() {
		return allStops.get(allStops.size() - 1);
	}

	public ArrayList<Route> getAllStops() {
		return allStops;
	}

	public Route findRoute(String departurePlace, String arrivalPlace, LocalTime departureTime) {
		// find routs
		Route findRoute;
		for (int i = 0; i < allStops.size(); i++) {
			if (departurePlace.equals(allStops.get(i).getDeparturePlace())
					&& (arrivalPlace.equals(allStops.get(i).getArrivalPlace())
							&& departureTime.isBefore(allStops.get(i).getDepartureTime()))) {
				findRoute = allStops.get(i);
				return findRoute;
			}

		}
		return null;

	}

	public LocalTime getFrequencyrTime() {
		return frequencyrTime;
	}

	public void setFrequencyrTime(LocalTime frequencyrTime) {
		this.frequencyrTime = frequencyrTime;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(
				allStops.get(0).getDepartureTime() + " : " + allStops.get(0).getDeparturePlace().toString() + " --> ");
		for (int i = 0; i < allStops.size(); i++) {
			str.append(allStops.get(i).getArrivalTime() + " : " + allStops.get(i).getArrivalPlace().toString());
			if (i < allStops.size() - 1)
				str.append(" --> ");
		}
		return str.toString();
	}

}
