package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResumes = 0;

    public void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist.");
            return null;
        }
        return storage[index];
    }

    public int size() {
        return countResumes;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResumes);
    }

    protected abstract int getIndex(String uuid);
}

