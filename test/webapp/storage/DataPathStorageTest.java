package webapp.storage;

import webapp.serializeStrategy.DataStreamStrategy;

public class DataPathStorageTest extends AbstractStorageTest{

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamStrategy()));
    }
}