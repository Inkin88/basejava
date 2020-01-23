package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        doUpdate(resume, checkForNotExistException(resume.getUuid()));
    }

    @Override
    public void save(Resume resume) {
        doSave(resume, checkForExistException(resume.getUuid()));
    }

    @Override
    public Resume get(String uuid) {
        return doGet(checkForNotExistException(uuid));
    }

    @Override
    public void delete(String uuid) {
        doDelete(checkForNotExistException(uuid));
    }

    private Integer checkForExistException(String uuid) {
        if (checkResumeExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
        return getIndex(uuid);
    }

    private Integer checkForNotExistException(String uuid) {
        if (!checkResumeExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return getIndex(uuid);
    }

    protected abstract boolean checkResumeExist(String uuid);

    protected abstract Integer getIndex(String uuid);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(Resume resume, int index);

    protected abstract void doDelete(int index);

    protected abstract Resume doGet(int index);

}
