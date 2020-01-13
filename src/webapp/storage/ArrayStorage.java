package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int countResumes = 0;

    public void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    public void update(Resume resume) {
        if (checkResume(resume.getUuid())) {
            for (int i = 0; i < countResumes; i++) {
                if (storage[i].equals(resume)) {
                    storage[i].setUuid("uuid100");
                }
            }
        }
    }

    public void save(Resume resume) {
        if (countResumes == 10_000) {
            System.out.println("Storage is full.");
        }
        if (checkResume(resume.getUuid())) {
            System.out.println("Resume is already exist.");
        } else {
            storage[countResumes] = resume;
            countResumes++;
        }
    }

    public Resume get(String uuid) {
        if (checkResume(uuid)) {
            for (int i = 0; i < countResumes; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkResume(uuid)) {
            for (int i = 0; i < countResumes; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    int k = i;
                    while (k < countResumes - 1) {
                        storage[k] = storage[k + 1];
                        k++;
                    }
                    storage[countResumes - 1] = null;
                    countResumes--;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResumes);
    }

    public int size() {
        return countResumes;
    }

    private boolean checkResume(String uuid) {
        boolean result = false;
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                result = true;
            }
        }
        if (!result) {
            System.out.println("Resume is not found.");
        }
        return result;
    }
}
