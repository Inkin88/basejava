package webapp.storage;

import org.junit.Assert;
import org.junit.Test;
import webapp.exception.StorageException;
import webapp.model.Resume;

public class SortedArrayStorageTest extends AbstractStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
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
}