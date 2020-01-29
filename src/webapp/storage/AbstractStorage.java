package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> sortByAllFields = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            int rs = o1.getFullName().compareTo(o2.getFullName());
            return rs == 0 ? o1.getUuid().compareTo(o2.getUuid()) : rs;
        }
    };

    @Override
    public void update(Resume resume) {
        doUpdate(resume, getNotExistedKey(resume.getUuid()));
    }

    @Override
    public void save(Resume resume) {
        doSave(resume, getExistedKey(resume.getUuid()));
    }

    @Override
    public Resume get(String uuid) {
        return doGet(getNotExistedKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        doDelete(getNotExistedKey(uuid));
    }

    private Object getExistedKey(String uuid) {
        Object key = getKey(uuid);
        if (isResumeExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private Object getNotExistedKey(String uuid) {
        Object key = getKey(uuid);
        if (!isResumeExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    protected abstract boolean isResumeExist(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract void doDelete(Object key);

    protected abstract Resume doGet(Object key);

}
