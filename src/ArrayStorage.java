/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int resumeCount = 0;

    public void clear() {
        for (Resume resume : storage) {
            resume = null;
        }
        resumeCount = 0;
    }

    public void save(Resume r) {
        storage[resumeCount] = r;
        resumeCount++;
    }

    public Resume get(String uuid) {
        Resume result = new Resume();
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                result = storage[i];
            }
        }
        return result;
    }

    public void delete(String uuid) {
        for (int i = 0; i < resumeCount; ++i) {
            if (storage[i].getUuid().equals(uuid)) {
                for (int k = i + 1; k < resumeCount; k++){
                    storage[k-1] = storage[k];
                }
                storage[resumeCount-1] = null;
                break;
            }
        }
        resumeCount--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll () {
        Resume[] result = new Resume[resumeCount];
        for (int i = 0; i < result.length; i++) {
            result[i] = storage[i];
        }
        return result;
    }

    public int size () {
        return resumeCount;
    }
}

