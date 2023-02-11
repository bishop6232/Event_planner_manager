
import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner();
        Scanner keyboard = new Scanner(System.in);
        eventPlanner.createLocations();
        // Display the menu
        while (true) {
            System.out.println("(01) Create a new event");
            System.out.println("(02) Show a report of all events");
            System.out.println("(03) Search for events by name");
            System.out.println("(04) Search for events by location");
            System.out.println("(05) Show all events on a particular day");
            System.out.println("(06) Show the most used location");
            System.out.println("(07) Show the least used location");
            System.out.println("(08) Exit");

            int choice = keyboard.nextInt();
            keyboard.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("please enter event ID");
                    String id = keyboard.nextLine();
                    eventPlanner.createEvent(Integer.parseInt(id));
                    break;
                case 2:
                    eventPlanner.showReport();
                    break;
                case 3:
                    System.out.println("please enter the Title of the event you want to search: ");
                    String title= keyboard.nextLine();
                    eventPlanner.searchByName(title);
                    break;
                case 4:
                    System.out.println("please enter the Location name of the event you want to search: ");
                    String input = keyboard.nextLine();
                        eventPlanner.searchByLocation(input);
                    break;
                case 5:
                    System.out.println("please enter the Date of the event you want to search: ");
                    String date = keyboard.nextLine();
                    eventPlanner.searchByDate(date);
                    break;
                case 6:
                    eventPlanner.showMostUsedLocation();
                    break;
                case 7:
                    eventPlanner.showLeastUsedLocation();
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        }
    }
}
