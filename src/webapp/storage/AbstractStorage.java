package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

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
