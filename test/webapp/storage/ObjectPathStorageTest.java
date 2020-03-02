package webapp.storage;

import webapp.SerializeStrategy.SerializeResume;

public class ObjectPathStorageTest extends AbstractStorageTest{

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new SerializeResume()));
    }
}