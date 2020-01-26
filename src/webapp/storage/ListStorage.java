package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        resumeList.add(resume);
    }

    @Override
    protected void doDelete(Object key) {
        resumeList.remove(((Integer) key).intValue());
    }

    @Override
    protected Resume doGet(Object key) {
        return resumeList.get((Integer) key);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        resumeList.set((Integer) key, resume);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    protected boolean isResumeExist(Object key) {
        return key != null;
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
