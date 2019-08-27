package stream.entity;

public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trader{");
        sb.append("name='").append(name).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getCity() {
        return city;
    }

    public String getName() {

        return name;
    }
}
