package webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private final String url;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String position;
    private final String description;

    public Organization(String url, String name, LocalDate startDate, LocalDate endDate, String position, String description) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        Objects.requireNonNull(position);
        this.url = url;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(url, that.url) &&
                name.equals(that.name) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                position.equals(that.position) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, name, startDate, endDate, position, description);
    }

    @Override
    public String toString() {
        return url + " " + name + "\n" +
                startDate + " - " + endDate + " " + position + "\n" +
                description;
    }
}
