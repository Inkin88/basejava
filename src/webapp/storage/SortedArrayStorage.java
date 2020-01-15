package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchResume = new Resume();
        searchResume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, countResumes, searchResume);
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        int absIndex = Math.abs(index);
        System.out.println(index);
        if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exist.");
        } else if (countResumes >= storage.length) {
            System.out.println("Storage is full");
        } else {
            System.arraycopy(storage, absIndex - 1, storage, absIndex, storage.length - absIndex);
            storage[absIndex - 1] = resume;
            countResumes++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist.");
        } else {
            System.arraycopy(storage, index + 1, storage, index, storage.length - index - 1);
            countResumes--;
        }
    }
}

