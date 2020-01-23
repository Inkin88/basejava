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
    protected void doSave(Resume resume, int index) {
        resumeList.add(resume);
    }

    @Override
    protected void doDelete(int index) {
        resumeList.remove(index);
    }

    @Override
    protected Resume doGet(int index) {
        return resumeList.get(index);
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        resumeList.set(index, resume);
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
    protected boolean checkResumeExist(String uuid) {
        return getIndex(uuid) != null;
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
