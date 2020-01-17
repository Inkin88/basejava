package webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

public abstract class AbstractArrayStorageTest {

    private Storage storage;

    private static final Resume RESUME1 = new Resume("uuid_1");
    private static final Resume RESUME2 = new Resume("uuid_2");
    private static final Resume RESUME3 = new Resume("uuid_3");
    private static final Resume RESUME4 = new Resume("uuid_4");
    private static final Resume RESUME5 = new Resume("uuid_5");
    private static final Resume RESUME6 = new Resume("uuid_6");

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }


    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME6);
        storage.save(RESUME5);
        storage.save(RESUME4);
        storage.save(RESUME3);
        storage.save(RESUME2);
        storage.save(RESUME1);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid_9");
        storage.save(resume);
        Assert.assertEquals(7, storage.size());
    }

    @Test (expected = ExistStorageException.class)
    public void saveAlreadyExistResume() {
        storage.save(new Resume("uuid_1"));
    }

    @Test (expected = StorageException.class)
    public void saveWhenStorageIsFull() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test
    public void delete() {
        storage.delete("uuid_2");
        Assert.assertEquals(5, storage.size());
    }

    @Test (expected = NotExistStorageException.class)
    public void whenDeleteNotExistResume() {
        storage.delete("uuid_99");
    }

    @Test
    public void update() {
        storage.update(new Resume("uuid_2"));
        boolean result = RESUME2.getUuid().equals("uuid_2");
        Assert.assertTrue(result);
    }

    @Test (expected = NotExistStorageException.class)
    public void updateResumeWhenNotExist() {
        storage.update(new Resume("uuid_99"));
    }

    @Test
    public void get() {
        storage.get("uuid_5");
        Assert.assertEquals(RESUME5.getUuid(), "uuid_5");
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
    public void getAll() {
        Resume[] resumes = storage.getAll();
        boolean result = true;
        for (Resume resume : resumes) {
            if (resume == null) {
                result = false;
            }
        }
        Assert.assertTrue(result);
    }
}