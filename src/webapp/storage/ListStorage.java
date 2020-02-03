package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    protected void doSave(Resume resume, Integer key) {
        resumeList.add(resume);
    }

    @Override
    protected void doDelete(Integer key) {
        resumeList.remove(key.intValue());
    }

    @Override
    protected Resume doGet(Integer key) {
        return resumeList.get(key);
    }

    @Override
    protected void doUpdate(Resume resume, Integer key) {
        resumeList.set(key, resume);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(resumeList);
    }

    @Override
    protected boolean isResumeExist(Integer key) {
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
