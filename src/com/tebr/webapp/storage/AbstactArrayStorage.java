package com.tebr.webapp.storage;

import com.tebr.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstactArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {//если есть такой элемент if (index != -1)
            storage[index] = r;
            System.out.println("update " + r.getUuid());
        } else {
            System.out.println("not update resume " + r.getUuid() + " not found");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {//если есть такой элемент if (index != -1)
            return storage[index];
        }
        System.out.println("resume " + uuid + " not found");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {//если есть такой элемент if (index != -1)
            for (int k = index; k < size; k++) {
                storage[k] = storage[k + 1];
            }
            size--;
        } else {
            System.out.println("not delete resume " + uuid + " not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    // проверяем наличие элемента в массиве
    protected abstract int getIndex(String uuid);

}