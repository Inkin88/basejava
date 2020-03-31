package webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.Config;
import webapp.ResumeTest;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.ContactsType;
import webapp.model.Resume;
import webapp.model.SectionType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";
    private static final String UUID_4 = "uuid_4";
    private static final String UUID_5 = "uuid_5";
    private static final String UUID_6 = "uuid_6";
    private static final String FULLNAME_1 = "Ivanov Ivan";
    private static final String FULLNAME_2 = "Petrov Petr";
    private static final String FULLNAME_3 = "Sidorov Sidr";
    private static final String FULLNAME_4 = "Kuznetcsov Aleksandr";
    private static final String FULLNAME_5 = "Ivanov Dmitriy";
    private static final String FULLNAME_6 = "Sidorov Petr";

    protected Storage storage;

    private static final Resume RESUME1 = ResumeTest.getResume();
    private static final Resume RESUME2 = new Resume(UUID_2, FULLNAME_2);
    private static final Resume RESUME3 = new Resume(UUID_3, FULLNAME_3);
    private static final Resume RESUME4 = new Resume(UUID_4, FULLNAME_4);
    private static final Resume RESUME5 = new Resume(UUID_5, FULLNAME_5);
    private static final Resume RESUME6 = new Resume(UUID_6, FULLNAME_6);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
        storage.save(RESUME4);
        storage.save(RESUME5);
        storage.save(RESUME6);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        Resume resume7 = new Resume("uuid_9", "Muzafarov Airat");
        storage.save(resume7);
        Assert.assertEquals(7, storage.size());
        Assert.assertEquals(resume7, storage.get("uuid_9"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_2, FULLNAME_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(5, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid_99");
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_2, FULLNAME_2);
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("uuid_99"));
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME5, storage.get(UUID_5));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void size() {
        Assert.assertEquals(6, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedResume = storage.getAllSorted();
        Assert.assertEquals(expectedResume, new ArrayList<>(Arrays.asList(RESUME5, RESUME1, RESUME4, RESUME2, RESUME6, RESUME3)));
        Assert.assertEquals(6, expectedResume.size());
    }

    @Test
    public void getContact() {
        Assert.assertEquals(RESUME1.getContacts(ContactsType.TELEPHONE), "+7(921) 855-0482");
    }

    @Test
    public void getSection() {
        Assert.assertEquals(RESUME1.getSection(SectionType.OBJECTIVE).toString(), "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");
    }
}