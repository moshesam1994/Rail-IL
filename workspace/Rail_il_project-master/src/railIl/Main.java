package railIl;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void readAllLines() {

		ArrayList<Line> AllLines = new ArrayList<>();
		ArrayList<Route> routs = new ArrayList<Route>();
		String departurePlace = null, arrivalPlace = null;
		LocalTime departureTime, arrivalTime;
		boolean fcontinu = true;
		int departureHour = 0, departureMinutes = 0, arrivalHour = 0, arrivalMinutes, counter = 0;
		do {

			try {
				File myObj = new File("Lines.txt");
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					System.out.println(data);
					if (counter == 0) {
						departurePlace = data;
						counter++;
					}

					if (counter == 1) {
						departureHour = Integer.parseInt(data);
						counter++;
					}
					if (counter == 2) {
						departureMinutes = Integer.parseInt(data);
						counter++;
					}
					if (counter == 3) {
						arrivalPlace = data;
						counter++;

					}
					if (counter == 4) {
						arrivalHour = Integer.parseInt(data);
						counter++;
					}
					if (counter == 5) {
						arrivalMinutes = Integer.parseInt(data);
						routs.add(new Route(LocalTime.of(departureHour, departureMinutes),
								LocalTime.of(arrivalHour, arrivalMinutes), departurePlace, arrivalPlace));
						counter = 0;
					}
					if (data.equalsIgnoreCase("New Line")) {
						if (AllLines.size() > 0) {
							AllLines.add(new Line(routs));
							counter = 0;
						}

					}

					if (data.equalsIgnoreCase("finish")) {
						fcontinu = false;
					}

				}

				myReader.close();

			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());

			}
		} while (fcontinu);
	}

	private static void findRoutes(Scanner s, ArrayList<Line> allLines) {
		String departureStation, destinationStation;
		int counter1 = 0;
		int hour, minutes;
		boolean fcontinu = true;
		ArrayList<Route> routes = new ArrayList<Route>();

		try {

			System.out.println("Enter a departure station ");
			departureStation = s.nextLine();
			System.out.println("Enter a destination station");
			destinationStation = s.nextLine();
			System.out.println("Enter a departure hour");
			hour = s.nextInt();
			System.out.println("Enter a departure minuts");
			minutes = s.nextInt();
			s.nextLine();
			for (int i = 0; i < allLines.size(); i++) {
				if (allLines.get(i).findRoute(departureStation, destinationStation,
						LocalTime.of(hour, minutes)) != null) {
					routes.add(allLines.get(i).findRoute(departureStation, destinationStation,
							LocalTime.of(hour, minutes)));
					counter1++;

				}

				/*
				 * if (counter1 == 3) { fcontinu = false; }
				 */
			}
		}

		catch (InputMismatchException e) {
			System.out.println(e.getMessage() + " try again");
			fcontinu = true;
			s.nextLine();

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + " try again");
		}

		// Sort the Route
		int i, j, counter = 0;
		Route temp;
		boolean swapped;
		for (i = 0; i < routes.size() - 1; i++) {
			swapped = false;
			for (j = 0; j < routes.size() - i - 1; j++) {
				if (routes.get(j).getDepartureTime().isAfter(routes.get(j + 1).getDepartureTime())) {
					temp = new Route(routes.get(j));
					routes.set(j, routes.get(j + 1));
					routes.set(j + 1, temp);
					counter++;
					swapped = true;

				}

			}

			if (swapped == false)
				break;
		}

		System.out.println(routes);

	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<Line> trsterLine = new ArrayList<Line>();
		ArrayList<Route> testerRoutse = new ArrayList<Route>();
		testerRoutse.add(new Route(LocalTime.of(13, 00), LocalTime.of(13, 30), "tel aviv", "tel aviv aagana"));
		testerRoutse.add(new Route(LocalTime.of(13, 35), LocalTime.of(14, 00), "tel aviv", "tel aviv aagana"));
		testerRoutse.add(new Route(LocalTime.of(14, 05), LocalTime.of(16, 00), "tel aviv aagana", "bar shava"));
		testerRoutse.add(new Route(LocalTime.of(16, 05), LocalTime.of(17, 00), "tel aviv", "eliat"));
		trsterLine.add(new Line(testerRoutse));
		findRoutes(s, trsterLine);
		ArrayList<Line> allLines = new ArrayList<>();
		ArrayList<Route> allRoutes = new ArrayList<>();
		int choice;
		LocalTime frequencyrTime;

		do {
			System.out.println("Enter your choice: ");
			for (int i = 1; i <= 4; i++) {
				System.out.println("[" + i + "]" + "-" + MenuHelper(i - 1));
			}
			System.out.println("[" + 9 + "]-To exit");
			choice = s.nextInt();

			switch (choice) {
			case 1: {
				allRoutes.add(new Route());
				System.out.println("new route added succefully");
				ArrayList<Route> allStops = new ArrayList<Route>();
				int stationCounter = 1;
				boolean bExceptionFree = true;
				int hourDelay = 0, minutesDelay = 0;
				do {
					try {
						System.out.println("what the frequency of the line you want?(hours and minutes)\n"
								+ "type hour frequency(0 for none): ");
						hourDelay = s.nextInt();
						System.out.println("type minutes frequency(0 for none): ");
						minutesDelay = s.nextInt();
						s.nextLine();

						;
						bExceptionFree = false;
					} catch (InputMismatchException e) {
						System.out.println("entered invalid input, try again...");
					}
				} while (bExceptionFree);
				System.out.println(
						"input details (if frequent, the time is for the first line of the day) of station number "
								+ stationCounter + " :");
				allStops.add(new Route());
				System.out.println("stop number " + stationCounter + " added successfully");
				stationCounter++;
				boolean bAnotherStation = true, bError = true;
				do {
					System.out.println("input details of station number " + stationCounter + " :");
					allStops.add(new Route(allStops.get(stationCounter - 2).getArrivalPlace(),
							allStops.get(stationCounter - 2).getArrivalTime()));
					System.out.println("stop number " + stationCounter + " added successfully");
					stationCounter++;
					System.out.println("do you want to add another station to the line? (Y/N)");
					char answer;
					do {
						try {
							answer = s.next().charAt(0);
							if (answer == 'n' || answer == 'N')
								bAnotherStation = false;
							else if (answer == 'y' || answer == 'Y') {
							} else
								throw new Exception("input can only be 'y' || 'Y' || 'n' || 'N'");
							bError = false;
						} catch (Exception e) {
							System.out.println("please type only 'Y' (yes) / 'N' (no)... please try again");
						}
					} while (bError);
				} while (bAnotherStation);
				allLines.add(new Line(allStops));
				if (hourDelay != 0 || hourDelay != 0) {
					while ((allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getHour() + hourDelay)
							+ (allLines.get(allLines.size() - 1).getLastStop().getArrivalTime().getHour()
									- allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getHour()) < 23
							&& (allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getMinute()
									+ hourDelay)
									+ (allLines.get(allLines.size() - 1).getLastStop().getArrivalTime().getMinute()
											- allLines.get(allLines.size() - 1).getLastStop().getDepartureTime()
													.getMinute()) < 59) {
						allLines.add(
								new Line(allLines.get(allLines.size() - 1).getAllStops(), hourDelay, minutesDelay));
					}
				}

				System.out.println();
				System.out.println("added new lines to the system from " + allStops.get(0).getDeparturePlace() + " to "
						+ allStops.get(allStops.size() - 1).getArrivalPlace() + " with frequency: " + hourDelay + ":"
						+ minutesDelay);

				break;
			}
			case 2: {
				BubblesortByTime(allRoutes);
				PrintAllRoutesDetails(allRoutes);
				PrintAllLinesDetails(allLines);
				break;
			}
			case 3: {

				if (allRoutes.isEmpty()) {
					System.out.println("No routes in the system");
					break;
				} else

					findRoutes(s, allLines);
			}
			case 4: {
				saveToFile(allRoutes, allLines);
			}

			}
		} while (choice != 9);
		System.out.println("Good bye:)");
		s.close();
	}

	private static void saveToFile(ArrayList<Route> allRoutes, ArrayList<Line> allLines) {

	}

	public static String MenuHelper(int i) {
		final String str[] = new String[9];
		str[0] = "To Add new route and stop stations to system";
		str[1] = "Show details of all lines";
		str[2] = "To find details Routs ";
		str[3] = "Save to file";

		return str[i];
	}

	public static void PrintAllRoutesDetails(ArrayList<Route> allRoutes) {
		for (int i = 0; i < allRoutes.size(); i++) {
			System.out.println(allRoutes.get(i).toString() + "\n");
		}
	}

	public static void PrintAllLinesDetails(ArrayList<Line> allLines) {
		for (int i = 0; i < allLines.size(); i++) {
			System.out.println(allLines.get(i).toString() + "\n");
		}
	}

	public static void BubblesortByTime(ArrayList<Route> allRoutes) {
		int i, j;
		Route temp;
		boolean swapped;
		for (i = 0; i < allRoutes.size() - 1; i++) {
			swapped = false;
			for (j = 0; j < allRoutes.size() - i - 1; j++) {
				if (allRoutes.get(j).getDepartureTime().isAfter(allRoutes.get(j + 1).getDepartureTime())) {
					temp = new Route(allRoutes.get(j));
					allRoutes.set(j, allRoutes.get(j + 1));
					allRoutes.set(j + 1, temp);
					swapped = true;
				}
			}

			if (swapped == false)
				break;
		}
	}

}
