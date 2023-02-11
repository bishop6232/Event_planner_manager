import java.time.LocalDate;
public class OnlineEvent extends Event {

    public OnlineEvent(int id, String title, LocalDate start, int length, Unit unit) {
        super(id, title, new Location("online", 0), start, length, unit, new String[0]);
    }
}
