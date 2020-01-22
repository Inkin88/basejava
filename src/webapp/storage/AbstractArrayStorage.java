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
    public void doSave(Resume resume) {
        if (countResumes >= storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            addResume(resume, getIndex(resume.getUuid()));
            countResumes++;
        }
    }

    @Override
    public void doDelete(Resume resume) {
        fillDeletedResumes(getIndex(resume.getUuid()));
        storage[countResumes - 1] = null;
        countResumes--;
    }
    @Override
    public void doUpdate(Resume resume) {
        storage[getIndex(resume.getUuid())] = resume;
    }

    @Override
    public Resume doGet(String uuid) {
        int index = getIndex(uuid);
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

    protected abstract Integer getIndex(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void fillDeletedResumes(int index);
}

