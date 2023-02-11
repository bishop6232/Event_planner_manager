public class Location {
    private String name;
    private int Capacity;

    public Location(String name, int capacity) {
        this.name = name;
        this.Capacity = capacity;
    }

    public void setName(String name){

        this.name = name;
    }

    public void setCapacity(int capacity){

        this.Capacity = capacity;
    }

    public String getName() {

        return this.name;
    }

    public int getCapacity() {

        return this.Capacity;
    }



}
