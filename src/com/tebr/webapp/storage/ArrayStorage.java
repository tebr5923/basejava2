package com.tebr.webapp.storage;

import com.tebr.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstactArrayStorage {

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {//если есть такой элемент if (getIndex(r.getUuid()) != -1)
            System.out.println("already exist " + r.getUuid());
        } else if (size == STORAGE_LIMIT) {
            System.out.println("storage overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    // проверяем наличие элемента в массиве
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}