package webapp.storage;

import webapp.serializeStrategy.JsonStreamStrategy;

public class JsonPathStorageTest extends AbstractStorageTest{

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamStrategy()));
    }
}