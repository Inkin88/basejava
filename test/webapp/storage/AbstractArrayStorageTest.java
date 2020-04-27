package webapp.storage;

import org.junit.Assert;
import org.junit.Test;
import webapp.exception.StorageException;
import webapp.model.Resume;

public class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Vasya"));
            }
        } catch (StorageException e) {
            Assert.fail("Storage overflow");
        }
        storage.save(new Resume("Vasya"));
    }
}
