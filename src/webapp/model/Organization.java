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
        Objects.requireNonNull(description);
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
                Objects.equals(name, that.name) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(position, that.position) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, name, startDate, endDate, position, description);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
