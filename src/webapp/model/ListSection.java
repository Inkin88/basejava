package webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private static final long serialVersionUID = 1L;

    private List<String> stringList;

    public ListSection(List<String> stringList) {
        Objects.requireNonNull(stringList);
        this.stringList = stringList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(stringList, that.stringList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : stringList) {
            sb.append(str).append("\n");
        }
        return sb.toString();
    }
}
