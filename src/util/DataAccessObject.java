package util;

public interface DataAccessObject<T> {

    T get();
    boolean insert();
    boolean update();
    boolean delete();


}
