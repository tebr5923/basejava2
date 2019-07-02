package com.tebr.webapp.storage;

import com.tebr.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> listStorage = new ArrayList<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        listStorage.add(r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
//        listStorage.add((Integer) searchKey, r);
        listStorage.set((Integer) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        int index = (Integer) searchKey;
        listStorage.remove(index);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        int index = (Integer) searchKey;
        return listStorage.get(index);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
//        Resume searchKey = new Resume(uuid);
//        return listStorage.indexOf(searchKey);
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[listStorage.size()]);
    }

    @Override
    public int size() {
        return listStorage.size();
    }
}
