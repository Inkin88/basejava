package webapp.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {

    private final String url;
    private final String name;
    private List<Position> positions;

    public Organization(String url, String name, Position... positions) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(positions);
        this.url = url;
        this.name = name;
        this.positions = Arrays.asList(positions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(url, that.url) &&
                name.equals(that.name) &&
                positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, name, positions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(url).append(" ").append(name).append("\n");
        for (Position p : positions) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }

    public static class Position {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String position;
        private final String description;

        public Position(LocalDate startDate, LocalDate endDate, String position, String description) {
            Objects.requireNonNull(startDate);
            Objects.requireNonNull(endDate);
            Objects.requireNonNull(position);
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position1 = (Position) o;
            return startDate.equals(position1.startDate) &&
                    endDate.equals(position1.endDate) &&
                    position.equals(position1.position) &&
                    Objects.equals(description, position1.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, position, description);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(startDate).append(" - ").append(endDate).append(" ").append(position);
            if (description != null) {
                sb.append(" ").append(description);
            }
            return sb.toString();
        }
    }
}
