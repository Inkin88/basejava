package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected boolean isResumeExist(Object key) {
        return resumeMap.containsKey((String) key);
    }

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        resumeMap.put((String) key, resume);
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        resumeMap.put((String) key, resume);
    }

    @Override
    protected void doDelete(Object key) {
        resumeMap.remove((String) key);
    }

    @Override
    protected Resume doGet(Object key) {
        return resumeMap.get((String) key);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
