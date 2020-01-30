package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

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
    protected void doSave(Resume resume, Object key) {
        if (countResumes >= storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            addResume(resume, (Integer) key);
            countResumes++;
        }
    }

    @Override
    protected void doDelete(Object key) {
        fillDeletedResumes((Integer) key);
        storage[countResumes - 1] = null;
        countResumes--;
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        storage[(Integer) key] = resume;
    }

    @Override
    protected Resume doGet(Object key) {
        return storage[(Integer) key];
    }

    @Override
    public int size() {
        return countResumes;
    }

    @Override
    protected List<Resume> getList() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, countResumes));
    }

    @Override
    protected boolean isResumeExist(Object key) {
        return (Integer) key >= 0;
    }

    protected abstract void addResume(Resume resume, int index);

    protected abstract void fillDeletedResumes(int index);
}

