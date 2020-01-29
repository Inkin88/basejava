package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected boolean isResumeExist(Object key) {
        return resumeMap.containsValue((Resume) key);
    }

    @Override
    protected Resume getKey(String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object key) {
        Resume resume = (Resume) key;
        resumeMap.remove(((Resume) key).getUuid());
    }

    @Override
    protected Resume doGet(Object key) {
        return (Resume) key;
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>(resumeMap.values());
        resumes.sort(sortByAllFields);
        return resumes;
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
