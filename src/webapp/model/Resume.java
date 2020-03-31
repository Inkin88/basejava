package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private String fullName;
    private final Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);
    private final Map<SectionType, Section> section = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Map<SectionType, Section> getSections() {
        return section;
    }

    public Map<ContactsType, String> getContacts() {
        return contacts;
    }

    public Section getSection(SectionType type) {
        return section.get(type);
    }

    public String getContacts(ContactsType type) {
        return contacts.get(type);
    }

    public void addSection(SectionType type, Section section) {
        this.section.put(type, section);
    }

    public void addContact(ContactsType contactsType, String contact) {
        contacts.put(contactsType, contact);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(section, resume.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, section);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append("\n");
        for (Map.Entry<ContactsType, String> entry : contacts.entrySet()) {
            builder.append(entry.getKey().getContact()).append(": ").append(entry.getValue()).append("\n");
        }
        for (Map.Entry<SectionType, Section> entry : section.entrySet()) {
            builder.append(entry.getKey().getTitle()).append("\n");
            builder.append(entry.getValue());
        }
        return builder.toString();
    }
}
