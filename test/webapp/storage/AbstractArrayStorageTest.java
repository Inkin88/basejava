package webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

public abstract class AbstractArrayStorageTest {

    private static String uuid_1 = "uuid_1";
    private static String uuid_2 = "uuid_2";
    private static String uuid_3 = "uuid_3";
    private static String uuid_4 = "uuid_4";
    private static String uuid_5 = "uuid_5";
    private static String uuid_6 = "uuid_6";

    private Storage storage;

    private static final Resume RESUME1 = new Resume(uuid_1);
    private static final Resume RESUME2 = new Resume(uuid_2);
    private static final Resume RESUME3 = new Resume(uuid_3);
    private static final Resume RESUME4 = new Resume(uuid_4);
    private static final Resume RESUME5 = new Resume(uuid_5);
    private static final Resume RESUME6 = new Resume(uuid_6);

    protected AbstractArrayStorageTest(Storage storage) {
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
        Resume resume7 = new Resume("uuid_9");
        storage.save(resume7);
        Assert.assertEquals(7, storage.size());
        Assert.assertEquals(resume7, storage.get("uuid_9"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(uuid_1));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Storage overflow");
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(uuid_2);
        Assert.assertEquals(5, storage.size());
        storage.get(uuid_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid_99");
    }

    @Test
    public void update() {
        Resume resume = new Resume(uuid_2);
        storage.update(resume);
        Assert.assertSame(resume, storage.get(uuid_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("uuid_99"));
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME5, storage.get(uuid_5));
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
        Resume[] resumes = new Resume[]{RESUME1, RESUME2, RESUME3, RESUME4, RESUME5, RESUME6};
        Assert.assertArrayEquals(resumes, storage.getAll());
        Assert.assertEquals(6, storage.getAll().length);
    }
}