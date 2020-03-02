package webapp.storage;

import webapp.SerializeStrategy.SerializeResume;

public class ObjectFileStorageTest extends AbstractStorageTest{

    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new SerializeResume()));
    }
}