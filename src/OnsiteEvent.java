
import java.time.LocalDate;

public class OnsiteEvent extends Event {
    public OnsiteEvent(int id, String title, Location location, LocalDate start, int length, Unit unit, String[] participants) {
        super(id, title, location, start, length, unit, participants);
        if (this.getNumParticipants() > this.getLocation().getCapacity()) {
            System.out.println("\nERROR: Number of participants exceeds location capacity\n");
            System.exit(0);
        }
    }
}
