
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class EventPlanner {
    Scanner keyboard = new Scanner(System.in);
    private Event[] events;
   // private int numEvents;
    private Location[] locations;
    private int numLocations;

    public EventPlanner() {
        events = new Event[0];
       // numEvents = 0;
        locations = new Location[0];
        numLocations = 0;
    }

    public int getNumEvents() {
        return this.events.length;
    }

    public void addEvent(Event event) {
        int numEvents = this.getNumEvents();
        Event[] newEvents = new Event[numEvents + 1];
        for (int i = 0; i < numEvents; i++) {
            newEvents[i] = this.events[i];
        }
        newEvents[numEvents] = event;
        this.events = newEvents;
    }

    public void createLocations() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of locations: ");
        this.numLocations = input.nextInt();
        for (int i = 0; i < this.numLocations; i++) {
            addLocation();
        }
    }

    public void addLocation() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter location name: ");
        String name = input.nextLine();
        System.out.print("Enter location capacity: ");
        int capacity = input.nextInt();
        Location newLocation = new Location(name, capacity);
        Location[] newLocations = new Location[this.locations.length + 1];
        for (int i = 0; i < this.locations.length; i++) {
            newLocations[i] = this.locations[i];
        }
        newLocations[newLocations.length - 1] = newLocation;
        this.locations = newLocations;
        System.out.println("Location added successfully.");
    }

    public Location searchLocation(String name) {
        if (this.locations.length == 0) {
            return null;
        }
        for (int i = 0; i < this.locations.length; i++) {
            if (this.locations[i] != null && this.locations[i].getName().equalsIgnoreCase(name)) {
                return this.locations[i];
            }
        }
        return null;
    }

    public void createEvent(int id){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter event title: ");
        String title = keyboard.nextLine();
        System.out.print("Enter event type (Onsite or Online): ");
        String eventType = keyboard.nextLine();
        Event event;
        if (eventType.equalsIgnoreCase("Onsite")) {
            System.out.print("Enter event location: ");
            String location = keyboard.nextLine();
            Location eventLocation = searchLocation(location);
            if (eventLocation == null) {
                System.out.println("Invalid location. Event not created.");
                return;
            }
            System.out.print("Enter event start date (YYYY-MM-DD): ");
            LocalDate start = LocalDate.parse((keyboard.nextLine()));
            System.out.print("Enter event length: ");
            int length = keyboard.nextInt();
            System.out.print("Enter event unit (HOUR, DAY, MONTH): ");
            Unit unit = Unit.valueOf(this.keyboard.nextLine().toUpperCase());
            System.out.print("Enter number of participants: ");
            int numParticipants = keyboard.nextInt();
            String[] participants = new String[numParticipants];
           event = new OnsiteEvent(id, title, eventLocation, start, length, unit, participants);
            if (checkOverlap(event)) {
                System.out.println("\nERROR: Cannot create event - this location already in use.\n");
                return;
            }

        } else if (eventType.equalsIgnoreCase("Online")) {
            System.out.print("Enter event start date (YYYY-MM-DD): ");
            LocalDate start = LocalDate.parse((this.keyboard.nextLine()));
            System.out.print("Enter event length: ");
            int length = keyboard.nextInt();
            this.keyboard.nextLine();
            System.out.print("Enter event unit (HOUR, DAY, MONTH): ");
            Unit unit = Unit.valueOf(this.keyboard.nextLine().toUpperCase());

            event = new OnlineEvent(id, title, start, length, unit);
        } else {
            System.out.println("Invalid event type. Event not created.");
            return;
        }
        this.addEvent(event);
        System.out.println("Event created successfully.");
    }

    public void showReport() {
        for (int i = 0; i < this.events.length; i++) {
            Event event = this.events[i];
            System.out.println("Event ID: " + event.getId());
            System.out.println("Title: " + event.getTitle());
            if (event instanceof OnsiteEvent) {
                OnsiteEvent onsiteEvent = (OnsiteEvent) event;
                System.out.println("Location: " + onsiteEvent.getLocation().getName());
            } else {
                System.out.println("Location: Online");
            }
            System.out.println("Start Date: " + event.getStart().toString());
            System.out.println("Length: " + event.getLength() + " " + event.getUnit().toString());
        }
        System.out.println("-------------------");
    }

    public void searchByName(String name) {
        for (int i = 0; i < this.events.length; i++) {
            Event event = this.events[i];
            if (event.getTitle().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("Event ID: " + event.getId());
                System.out.println("Title: " + event.getTitle());
                if (event instanceof OnsiteEvent) {
                    OnsiteEvent onsiteEvent = (OnsiteEvent) event;
                    System.out.println("Location: " + onsiteEvent.getLocation().getName());
                } else {
                    System.out.println("Location: Online");
                }
                System.out.println("Start Date: " + event.getStart().toString());
                System.out.println("Length: " + event.getLength() + " " + event.getUnit().toString());
            }
            System.out.println("-------------------");
        }
    }

    public void searchByLocation(String name) {
        for (int i = 0; i < this.events.length; i++) {
            Event event = this.events[i];
            if (event instanceof OnsiteEvent) {
                OnsiteEvent onsiteEvent = (OnsiteEvent) event;
                if (onsiteEvent.getLocation().getName().equalsIgnoreCase(name)) {
                    System.out.println("Event ID: " + event.getId());
                    System.out.println("Title: " + event.getTitle());
                    System.out.println("Location: " + onsiteEvent.getLocation().getName());
                    System.out.println("Start Date: " + event.getStart().toString());
                    System.out.println("Length: " + event.getLength() + " " + event.getUnit().toString());

                    System.out.println("-------------------");
                }
            }
        }
    }

    public void searchByDate(String dateString) {
        LocalDate date = LocalDate.parse((dateString));
        for (int i = 0; i < this.events.length; i++) {
            Event event = this.events[i];
            if (event.getStart().equals(date)) {
                System.out.println("Event ID: " + event.getId());
                System.out.println("Title: " + event.getTitle());
                if (event instanceof OnsiteEvent) {
                    OnsiteEvent onsiteEvent = (OnsiteEvent) event;
                    System.out.println("Location: " + onsiteEvent.getLocation().getName());
                } else {
                    System.out.println("Location: Online");
                }
                System.out.println("Start Date: " + event.getStart().toString());
                System.out.println("Length: " + event.getLength() + " " + event.getUnit().toString());

                System.out.println("-------------------");
            }
        }
    }
    public void showMostUsedLocation() {
        int maxCount = 0;
        Location mostUsed = null;
        for (int i = 0; i < this.locations.length; i++) {
            Location location = this.locations[i];
            int count = 0;
            for (int j = 0; j < this.events.length; j++) {
                Event event = this.events[j];
                if (event instanceof OnsiteEvent) {
                    OnsiteEvent onsiteEvent = (OnsiteEvent) event;
                    if (onsiteEvent.getLocation().equals(location)) {
                        count++;
                    }
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostUsed = location;
            }
        }
        if (mostUsed != null) {
            System.out.println("Most used location: " + mostUsed.getName() + " (" + maxCount + " events)");
        } else {
            System.out.println("No events found.");
        }
    }

    public void showLeastUsedLocation() {
        int minCount = Integer.MAX_VALUE;
        Location leastUsed = null;
        for (int i = 0; i < this.locations.length; i++) {
            Location location = this.locations[i];
            int count = 0;
            for (int j = 0; j < this.events.length; j++) {
                Event event = this.events[j];
                if (event instanceof OnsiteEvent) {
                    OnsiteEvent onsiteEvent = (OnsiteEvent) event;
                    if (onsiteEvent.getLocation().equals(location)) {
                        count++;
                    }
                }
            }
            if (count < minCount) {
                minCount = count;
                leastUsed = location;
            }
        }
        if (leastUsed != null) {
            System.out.println("Least used location: " + leastUsed.getName() + " (" + minCount + " events)");
        }
        else {
            System.out.println("No events found.");
        }
    }
    public boolean checkOverlap(Event event) {
        for (int i = 0; i < this.events.length; i++) {
            Event otherEvent = this.events[i];
            if (otherEvent instanceof OnsiteEvent) {
                OnsiteEvent otherOnsiteEvent = (OnsiteEvent) otherEvent;

                    if (event.getLocation().equals(otherOnsiteEvent.getLocation()) && event.getStart().equals(otherOnsiteEvent.getStart()) ) {
                            return true;
                        }
                    }
                }

        return false;
    }

}
