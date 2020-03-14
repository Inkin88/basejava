package webapp.SerializeStrategy;

import webapp.exception.StorageException;
import webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamStrategy implements Strategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactsType, String> contacts = r.getContacts();
            writeWithException(dos, contacts.entrySet(), contactsTypeStringEntry -> {
                dos.writeUTF(contactsTypeStringEntry.getKey().name());
                dos.writeUTF(contactsTypeStringEntry.getValue());
            });
            Map<SectionType, Section> sections = r.getSections();
            writeWithException(dos, sections.entrySet(), sectionTypeSectionEntry -> {
                SectionType sectionType = sectionTypeSectionEntry.getKey();
                Section section = sectionTypeSectionEntry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((TextSection) section).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        writeWithException(dos, ((ListSection) section).getStringList(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeWithException(dos, ((OrganizationListSection) section).getOrganizationList(), org -> {
                            dos.writeUTF(org.getUrl());
                            dos.writeUTF(org.getName());
                            writeWithException(dos, org.getPositions(), position -> {
                                writeLocalDate(dos, position.getStartDate());
                                writeLocalDate(dos, position.getEndDate());
                                dos.writeUTF(position.getPosition());
                                dos.writeUTF(position.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    private void writeLocalDate(DataOutputStream dataOutputStream, LocalDate localDate) throws IOException {
        dataOutputStream.writeInt(localDate.getYear());
        dataOutputStream.writeInt(localDate.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dataInputStream) throws IOException {
        return LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), 1);
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactsType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int size2 = dis.readInt();
            for (int k = 0; k < size2; k++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                resume.addSection(type, getSection(dis, type));
            }
            return resume;
        }
    }

    private Section getSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATION:
                List<String> strings = new ArrayList<>();
                int size = dis.readInt();
                for (int i = 0; i < size; i++) {
                    strings.add(dis.readUTF());
                }
                return new ListSection(strings);
            case EXPERIENCE:
            case EDUCATION:
                List<Organization> organizationList = new ArrayList<>();
                int organListSize = dis.readInt();
                for (int i = 0; i < organListSize; i++) {
                    List<Organization.Position> positions = new ArrayList<>();
                    String url = dis.readUTF();
                    String name = dis.readUTF();
                    int posSize = dis.readInt();
                    for (int k = 0; k < posSize; k++) {
                        positions.add(new Organization.Position(readLocalDate(dis), readLocalDate(dis),
                                dis.readUTF(), dis.readUTF()));
                    }
                    organizationList.add(new Organization(url, name, positions));
                }
                return new OrganizationListSection(organizationList);
            default:
                throw new StorageException("Что-то случилось", null);
        }
    }

    @FunctionalInterface
    private interface writeElement<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(DataOutputStream dataOutputStream, Collection<T> collection, writeElement<T> elementWrite) throws IOException {
        dataOutputStream.writeInt(collection.size());
        for (T t : collection) {
            elementWrite.write(t);
        }
    }
}
