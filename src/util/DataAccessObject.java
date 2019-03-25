package util;

public interface DataAccessObject<T> {

    T getById();
    boolean insert(String id);
    boolean update();
    boolean delete();

}
