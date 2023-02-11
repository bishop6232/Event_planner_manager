
import java.time.LocalDate;

enum Unit {
    HOUR,
    DAY,
    MONTH
}
 public class  Event {
    private int id;
    private String title;
    private Location location;
    private LocalDate start;
    private int length;
    private Unit unit;
    private String[] participants;

    // constructor
    public Event(int id, String title, Location location, LocalDate start, int length, Unit unit, String[] participants ){
        this.id = id;
        this.title = title;
        this.location = location;
        this.start = start;
        this.length = length;
        this.unit = unit;
        this.participants = participants;

    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setStart(LocalDate start) {

        this.start = start;
    }

    public LocalDate getStart() {

        return start;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setUnit(Unit unit) {

        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

     public int getNumParticipants() {
         return this.participants.length;
     }
     public String getParticipant(int index) {
         return this.participants[index];
     }

     public void addParticipant(String participant) {
         int numParticipants = this.getNumParticipants();
         String[] newParticipants = new String[numParticipants+1];
         for (int i = 0; i < numParticipants; i++) {
             newParticipants[i] = this.participants[i];
         }
         newParticipants[numParticipants] = participant;
         this.participants = newParticipants;
     }
     public void removeParticipant(String participant) {
         int numParticipants = this.getNumParticipants();
         int participantIndex = -1;
         for (int i = 0; i < numParticipants; i++) {
             if (this.participants[i].equals(participant)) {
                 participantIndex = i;
                 break;
             }
         }
         if (participantIndex != -1) {
             String[] newParticipants = new String[numParticipants - 1];
             int newIndex = 0;
             for (int i = 0; i < numParticipants; i++) {
                 if (i != participantIndex) {
                     newParticipants[newIndex] = this.participants[i];
                     newIndex++;
                 }
             }
             this.participants = newParticipants;
         }
     }
 }