package webapp.storage;

import webapp.serializeStrategy.ObjectStreamStrategy;

public class ObjectPathStorageTest extends AbstractStorageTest{

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStrategy()));
    }
}