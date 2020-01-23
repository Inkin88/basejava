package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResumes = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    @Override
    protected void doSave(Resume resume, int index) {
        if (countResumes >= storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            addResume(resume, index);
            countResumes++;
        }
    }

    @Override
    protected void doDelete(int index) {
        fillDeletedResumes(index);
        storage[countResumes - 1] = null;
        countResumes--;
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected Resume doGet(int index) {
        return storage[index];
    }

    @Override
    public int size() {
        return countResumes;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResumes);
    }

    @Override
    protected boolean checkResumeExist(String uuid) {
        return getIndex(uuid) >= 0;
    }

    protected abstract void addResume(Resume resume, int index);

    protected abstract void fillDeletedResumes(int index);
}

