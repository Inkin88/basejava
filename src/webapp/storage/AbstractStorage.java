package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        checkForNotExistException(resume.getUuid());
        doUpdate(resume);
    }

    @Override
    public void save(Resume resume) {
        checkForExistException(resume.getUuid());
        doSave(resume);
    }

    @Override
    public Resume get(String uuid) {
        checkForNotExistException(uuid);
        return doGet(uuid);
    }

    @Override
    public void delete(String uuid) {
        checkForNotExistException(uuid);
        doDelete(get(uuid));
    }

    private void checkForExistException(String uuid) {
        if (checkResumeExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
    }

    private void checkForNotExistException(String uuid) {
        if (!checkResumeExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
    }

    private boolean checkResumeExist(String uuid) {
        boolean result = false;
        if (getIndex(uuid) >= 0) {
            result = true;
        }
        return result;
    }

    protected abstract Integer getIndex(String uuid);

    protected abstract void doUpdate(Resume resume);

    protected abstract void doSave(Resume resume);

    protected abstract void doDelete(Resume resume);

    protected abstract Resume doGet(String uuid);

}
