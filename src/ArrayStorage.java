import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {

        if (r.uuid != null) {
            if (size == 0) {
                storage[size] = r;
                size++;
            } else {
                if (isExist(r.uuid) == -1) {//если нет такого элемента
                    storage[size] = r;
                    size++;
                } else {
                    System.out.println("такой элемент уже существует");
                }
            }
        }
    }


    void update (Resume r) {
        int isExist = isExist(r.uuid);
        if (r.uuid != null) {
            if (size == 0) {
                System.out.println("update не удался, нет такого элемента");

            } else {
                if (isExist != -1) {//если есть такой элемент
                    storage[isExist] = r;
                    System.out.println("update "+r.uuid);
                } else {
                    System.out.println("update не удался, нет такого элемента");
                }
            }
        }
    }

    Resume get(String uuid) {
        int isExist = isExist(uuid);
        if (isExist != -1) {//если есть такой элемент
            return storage[isExist];
        }
        System.out.println("нет такого элемента");
        return null;
    }

    void delete(String uuid) {
        int isExist = isExist(uuid);
        if (isExist != -1) {//если есть такой элемент
            for (int k = isExist; k < size; k++) {
                storage[k] = storage[k + 1];
            }
            size--;
        } else {
            System.out.println("не удалось удалить, нет такого элемента");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }

    // проверяем наличие элемента в массиве
    private int isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
