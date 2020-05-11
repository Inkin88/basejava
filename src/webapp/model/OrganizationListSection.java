package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationListSection extends Section {

    private static final long serialVersionUID = 1L;

    private List<Organization> organizationList;

    public OrganizationListSection() {
    }

    public OrganizationListSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

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
