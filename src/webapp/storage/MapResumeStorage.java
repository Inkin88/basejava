package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected boolean isResumeExist(Resume key) {
        return key != null;
    }

    @Override
    protected Resume getKey(String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Resume key) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Resume key) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume key) {
        resumeMap.remove(key.getUuid());
    }

    @Override
    protected Resume doGet(Resume key) {
        return key;
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public List<Resume> getList() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
