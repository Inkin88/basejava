/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int resumeCount = 0;

    public void clear() {
        if (resumeCount != 0) {
            for (int i = 0; i < resumeCount; i++) {
                storage[i] = null;
            }
        }
        resumeCount = 0;
    }

    public void save(Resume r) {
        storage[resumeCount] = r;
        resumeCount++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
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
        Resume[] resumes = new Resume[resumeCount];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return resumeCount;
    }
}

