package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator<Resume> SORT_BY_ALL_FIELDS = Comparator.comparing(Resume::getFullName).
            thenComparing(Resume::getUuid);

    @Override
    public void update(Resume resume) {
        LOG.info("Update" + resume);
        doUpdate(resume, getNotExistedKey(resume.getUuid()));
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save" + resume);
        doSave(resume, getExistedKey(resume.getUuid()));
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get" + uuid);
        return doGet(getNotExistedKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete" + uuid);
        doDelete(getNotExistedKey(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> sortedList = getList();
        sortedList.sort(SORT_BY_ALL_FIELDS);
        return sortedList;
    }

    private SK getExistedKey(String uuid) {
        SK key = getKey(uuid);
        if (isResumeExist(key)) {
            LOG.warning("Resume " + uuid + " already exist.");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private SK getNotExistedKey(String uuid) {
        SK key = getKey(uuid);
        if (!isResumeExist(key)) {
            LOG.warning("Resume " + uuid + " not exist.");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    protected abstract boolean isResumeExist(SK key);

    protected abstract SK getKey(String uuid);

    protected abstract void doUpdate(Resume resume, SK key);

    protected abstract void doSave(Resume resume, SK key);

    protected abstract void doDelete(SK key);

    protected abstract Resume doGet(SK key);

    protected abstract List<Resume> getList();

}
