package webapp.storage;

import webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getProps().getProperty("db.url"), Config.get().getProps().getProperty("db.user"),
                Config.get().getProps().getProperty("db.password")));
    }
}
