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
        if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exist.");
        } else if (countResumes >= storage.length) {
            System.out.println("Storage is full");
        } else if (absIndex > countResumes) {
            storage[countResumes] = resume;
            countResumes++;
        } else {
            int i = countResumes - 1;
            while (i >= absIndex - 1) {
                storage[i + 1] = storage[i];
                i--;
            }
            storage[absIndex - 1] = resume;
            countResumes++;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume " + resume.getUuid() + " not exist.");
        } else {
            storage[Math.abs(index)] = resume;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        int absIndex = Math.abs(index);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist.");
        } else {
            storage[absIndex] = null;
            for (int i = absIndex; i < countResumes; i++) {
                Resume buffer = storage[i + 1];
                storage[i] = storage[i + 1];
                storage[i] = buffer;
            }
            countResumes--;
        }
    }
}

