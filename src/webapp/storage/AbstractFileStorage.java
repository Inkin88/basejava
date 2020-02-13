package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory.");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
    }

    @Override
    public int size() {
        int size = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            size = files.length;
        }
        return size;
    }

    @Override
    protected boolean isResumeExist(File file) {
        return file.exists();
    }

    @Override
    protected File getKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        doWrite(resume, file);
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (file.isFile()) {
            file.delete();
        }
    }

    @Override
    protected Resume doGet(File file) {
        return doRead(file);
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> result = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File f : files) {
                result.add(doRead(f));
            }
        }
        return result;
    }

    protected abstract void doWrite(Resume resume, File file);

    protected abstract Resume doRead(File file);
}
