package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String uuid) {
        Resume searchResume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, countResumes, searchResume);
    }

    @Override
    protected void addResume(Resume resume, int index) {
        int addIndex = -index - 1;
        System.arraycopy(storage, addIndex, storage, addIndex + 1, countResumes - addIndex);
        storage[addIndex] = resume;
    }

    @Override
    protected void fillDeletedResumes(int index) {
        if ((countResumes - index - 1) > 0) {
            System.arraycopy(storage, index + 1, storage, index, countResumes - index - 1);
        }
    }
}

