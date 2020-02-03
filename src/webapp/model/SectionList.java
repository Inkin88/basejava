package webapp.model;

import java.util.List;
import java.util.Objects;

public class SectionList extends Section {

    private List<String> stringList;

    public SectionList(List<String> stringList) {
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
        SectionList that = (SectionList) o;
        return Objects.equals(stringList, that.stringList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringList);
    }

    @Override
    public String toString() {
        return "SectionList{" +
                "stringList=" + stringList +
                '}';
    }
}
