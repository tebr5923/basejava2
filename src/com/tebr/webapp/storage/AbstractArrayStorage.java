package com.tebr.webapp.storage;

import com.tebr.webapp.exceptions.StorageException;
import com.tebr.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("storage overflow", r.getUuid());
        } else {
            insertElement(r, (Integer) searchKey); //storage[size] = r;
            size++;
        }
    }

/*    public void save(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index >= 0) {//если есть такой элемент if (getSearchKey(r.getUuid()) != -1)
            throw new ExistStorageException(r.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("storage overflow", r.getUuid());
        } else {
            insertElement(r, index); //storage[size] = r;
            size++;
        }
    }
*/

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

/*    public void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index >= 0) {//если есть такой элемент if (index != -1)
            storage[index] = r;
            System.out.println("update " + r.getUuid());
        } else {
            throw new ExistStorageException(r.getUuid());
        }
    }
*/

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

/*    public Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (index >= 0) {//если есть такой элемент if (index != -1)
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }
*/

    @Override
    protected void doDelete(Object searchKey) {
        fillDeletedElement((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }
/*    public void delete(String uuid) {
        int index = getSearchKey(uuid);
        if (index >= 0) {//если есть такой элемент if (index != -1)
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
*/

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    // проверяем наличие элемента в массиве
    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

}