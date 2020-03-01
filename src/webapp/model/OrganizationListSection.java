package webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationListSection extends Section {

    private static final long serialVersionUID = 1L;

    private List<Organization> organizationList;

    public OrganizationListSection(List<Organization> organizationList) {
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
        OrganizationListSection that = (OrganizationListSection) o;
        return Objects.equals(organizationList, that.organizationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Organization org : organizationList) {
            sb.append(org).append("\n");
        }
        return sb.toString();
    }
}
