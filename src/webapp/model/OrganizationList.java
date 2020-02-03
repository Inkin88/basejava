package webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationList extends Section {

    private List<Organization> organizationList;

    public OrganizationList(List<Organization> organizationList) {
        Objects.requireNonNull(organizationList);
        this.organizationList = organizationList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationList that = (OrganizationList) o;
        return Objects.equals(organizationList, that.organizationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationList);
    }

    @Override
    public String toString() {
        return "OrganizationList{" +
                "organizationList=" + organizationList +
                '}';
    }
}
