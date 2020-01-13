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
        int index = checkResume(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume " + resume.getUuid() + " not exist.");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (checkResume(resume.getUuid()) != -1) {
            System.out.println("Resume " + resume.getUuid() + " already exist.");
        } else if (countResumes >= storage.length) {
            System.out.println("Storage is full");
        } else {
            storage[countResumes] = resume;
            countResumes++;
        }
    }

    public Resume get(String uuid) {
        int index = checkResume(uuid);
        if ( index == -1) {
            System.out.println("Resume " + uuid + " not exist.");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = checkResume(uuid);
        if (index == -1 ) {
            System.out.println("Resume " + uuid + " not exist.");
        } else {
            storage[index] = storage[countResumes - 1];
            storage[countResumes - 1] = null;
            countResumes--;
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

    private int checkResume(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

