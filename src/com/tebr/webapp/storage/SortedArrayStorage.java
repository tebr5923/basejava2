package com.tebr.webapp.storage;

import com.tebr.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstactArrayStorage {

    @Override
    public void save(Resume r) {
        if (size != 0) {
            int index = getIndex(r.getUuid());
            System.out.println("index after getIndex = " + index);
            if (index >= 0) {//если есть такой элемент if (getIndex(r.getUuid()) != -1)
                System.out.println("already exist " + r.getUuid());
            } else if (size == STORAGE_LIMIT) {
                System.out.println("storage overflow");
            } else {
                index = index * (-1) - 1; //index after getIndex больше на 1 чем индекс ячейки куда надо сохранить
                if (index == size) { // просто записываем самый большой элемент
                    storage[index] = r; //storage[size] = r;
                    System.out.println("index=size=" + index);
                    size++;
                } else { // сдвигаем все от index вправо
                    for (int k = size-1; k >= index; k--) { // k = size-1 так как size это размер а массив нач-тся с 0
                        storage[k + 1] = storage[k];
                    }
                    storage[index] = r; // записываем в index
                    System.out.println("index=" + index);
                    size++;
                }
            }
        } else {
            storage[0] = r;
            size++;
        }

    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
