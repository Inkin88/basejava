package webapp.storage;

import webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void addResume(Resume resume, int index) {
        storage[countResumes] = resume;
    }

    protected void fillDeletedResumes(int index) {
        storage[index] = storage[countResumes - 1];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

