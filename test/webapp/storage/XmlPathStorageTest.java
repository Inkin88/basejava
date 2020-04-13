package webapp.storage;

import webapp.serializeStrategy.XmlStreamStrategy;

public class XmlPathStorageTest extends AbstractStorageTest{

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamStrategy()));
    }
}