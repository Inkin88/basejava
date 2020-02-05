package webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {

    private final Map<SectionType, Section> section = new EnumMap<>(SectionType.class);
    private final Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);
    private final String uuid;
    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = Objects.requireNonNull(uuid);
        this.fullName = Objects.requireNonNull(fullName);
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
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
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
