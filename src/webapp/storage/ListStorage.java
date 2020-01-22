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
    public void doSave(Resume resume) {
        resumeList.add(resume);
    }

    @Override
    public void doDelete(Resume resume) {
        resumeList.remove(resume);
    }

    @Override
    public Resume doGet(String uuid) {
        return resumeList.get(getIndex(uuid));
    }

    @Override
    public void doUpdate(Resume resume) {
        int index = getIndex(resume.getUuid());
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
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
