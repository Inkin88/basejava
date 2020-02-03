package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected boolean isResumeExist(String key) {
        return resumeMap.containsKey(key);
    }

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume resume, String key) {
        resumeMap.put(key, resume);
    }

    @Override
    protected void doSave(Resume resume, String key) {
        resumeMap.put(key, resume);
    }

    @Override
    protected void doDelete(String key) {
        resumeMap.remove(key);
    }

    @Override
    protected Resume doGet(String key) {
        return resumeMap.get(key);
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
