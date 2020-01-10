package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int resumeCount = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumeCount, null);
        resumeCount = 0;
    }

    public void update(Resume resume) {
        if (!isProvide(resume)) {
            System.out.println("Resume is not founded.");
        } else {
            for (int i = 0; i < resumeCount; i++) {
                if (storage[i].equals(resume)) {
                    storage[i].setUuid("uuid100");
                }
            }
        }
    }

    public void save(Resume resume) {
        if (!isProvide(resume)) {
            storage[resumeCount] = resume;
            resumeCount++;
        } else {
            System.out.println("Resume is allready exist.");
        }
    }

    public Resume get(String uuid) {
        if (!checkUuid(uuid)) {
            System.out.println("Resume is not founded.");
        }
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (!checkUuid(uuid)) {
            System.out.println("Resume is not founded.");
        }
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                for (int k = i; k < resumeCount - 1; k++) {
                    storage[k] = storage[k + 1];
                }
                storage[resumeCount - 1] = null;
                resumeCount--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = Arrays.copyOfRange(storage, 0, resumeCount);
        return resumes;
    }

    public int size() {
        return resumeCount;
    }

    private boolean isProvide(Resume resume) {
        boolean result = false;
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].equals(resume)) {
                result = true;
            }
        }
        return result;
    }

    private boolean checkUuid(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}

