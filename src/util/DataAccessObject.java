package util;

public interface DataAccessObject<T> {

    T getById();
    boolean insert();
    boolean update();
    boolean delete();

}
