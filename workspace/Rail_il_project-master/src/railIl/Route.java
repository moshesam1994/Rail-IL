package railIl;

import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Route {
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private String departurePlace;
	private String arrivalPlace;
	private LocalTime frequencyrTime;

	public Route(LocalTime departureTime, LocalTime arrivalTime, String departurePlace, String arrivalPlace) {
		setDepartureTime(departureTime);
		setArrivalTime(arrivalTime);
		setDeparturePlace(departurePlace);
		setArrivalPlace(arrivalPlace);
	}

	public Route(Route another) {
		this.departurePlace = another.departurePlace;
		this.arrivalTime = another.arrivalTime;
		this.arrivalPlace = another.arrivalPlace;
		this.departureTime = another.departureTime;

	}

	public Route() {
		boolean fcontinue = true;
		do {
			try {
				Scanner s = new Scanner(System.in);
				System.out.println("Enter departure location:");
				setDeparturePlace(s.nextLine());
				System.out.println("Enter departure hour:");
				int hour = s.nextInt();
				System.out.println("Enter departure minutes:");
				int minutes = s.nextInt();
				s.nextLine();
				setDepartureTime(LocalTime.of(hour, minutes));
				System.out.println("Enter destination location:");
				setArrivalPlace(s.nextLine());
				System.out.println("Enter destenation hour:");
				hour = s.nextInt();
				System.out.println("Enter destenation minutes:");
				minutes = s.nextInt();
				s.nextLine();
				setArrivalTime(LocalTime.of(hour, minutes));
				fcontinue = false;
			} catch (InputMismatchException e) {
				System.out.println("try again " + e.getMessage());

			} catch (IllegalArgumentException e) {
				System.out.println("try again " + e.getMessage());
			}
		} while (fcontinue);

	}

	public Route(String departurePlace, LocalTime departurelTime) {
		Scanner s = new Scanner(System.in);
		this.departurePlace = departurePlace;
		this.departureTime = departurelTime;
		System.out.println("Enter destination location:");
		setArrivalPlace(s.nextLine());
		System.out.println("Enter destenation hour:");
		int hour = s.nextInt();
		System.out.println("Enter destenation minutes:");
		int minutes = s.nextInt();
		s.nextLine();
		setArrivalTime(LocalTime.of(hour, minutes));
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		if (departureTime != null) {
			do {
				if (departureTime.isBefore(arrivalTime))
					break;
				System.out.println("departure time cannot be after arrival time... try again");
				Scanner s = new Scanner(System.in);
				System.out.println("Enter destenation hour:");
				int hour = s.nextInt();
				System.out.println("Enter destenation minutes:");
				int minutes = s.nextInt();
				s.nextLine();
				arrivalTime = LocalTime.of(hour, minutes);
			} while (true);
		}
		this.arrivalTime = arrivalTime;
		return;
	}

	public LocalTime getFrequencyrTime() {
		return frequencyrTime;
	}

	public void setFrequencyrTime(LocalTime frequencyrTime) {
		this.frequencyrTime = frequencyrTime;
	}

	public String getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}

	public String getArrivalPlace() {
		return arrivalPlace;
	}

	public void setArrivalPlace(String arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}

	@Override
	public String toString() {

		return departurePlace + ", " + departureTime.toString() + ", " + arrivalPlace + ", " + arrivalTime.toString();
	}

}
