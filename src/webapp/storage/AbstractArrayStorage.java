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

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exist.");
        } else if (countResumes >= storage.length) {
            System.out.println("Storage is full");
        } else {
            addResume(resume, index);
            countResumes++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist.");
        } else {
            fillDeletedResumes(index);
            storage[countResumes - 1] = null;
            countResumes--;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " not exist.");
        } else {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
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
    protected abstract void addResume(Resume resume, int index);
    protected abstract void fillDeletedResumes(int index);
}

