package com.tebr.webapp.storage;

import com.tebr.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;

    }

    public void save(Resume r) {
        if (isExist(r.getUuid()) != -1) {//если есть такой элемент
            System.out.println("already exist " + r.getUuid());
        } else if (size == storage.length) {
            System.out.println("storage overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }


    public void update(Resume r) {
        int isExist = isExist(r.getUuid());
        if (isExist != -1) {//если есть такой элемент
            storage[isExist] = r;
            System.out.println("update " + r.getUuid());
        } else {
            System.out.println("not update resume " + r.getUuid() + " not found");
        }
    }



    public Resume get(String uuid) {
        int isExist = isExist(uuid);
        if (isExist != -1) {//если есть такой элемент
            return storage[isExist];
        }
        System.out.println("resume " + uuid + " not found");
        return null;
    }

    public void delete(String uuid) {
        int isExist = isExist(uuid);
        if (isExist != -1) {//если есть такой элемент
            for (int k = isExist; k < size; k++) {
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
    private int isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}