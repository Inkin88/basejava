import webapp.model.Resume;
import webapp.storage.SortedArrayStorage;
import webapp.storage.Storage;

/**
 * Test for your webapp.storage.ArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid5");
        Resume r2 = new Resume();
        r2.setUuid("uuid3");
        Resume r3 = new Resume();
        r3.setUuid("uuid7");
        Resume r4 = new Resume();
        r4.setUuid("uuid1");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        ARRAY_STORAGE.update(r4);
        ARRAY_STORAGE.update(r3);
        System.out.println("Get r3: " + ARRAY_STORAGE.get(r3.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size()); // 3

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
