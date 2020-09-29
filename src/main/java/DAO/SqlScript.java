package DAO;

import java.util.List;

/**
 * Methoden zum Arbeiten mit Daten werden in Form dieser interface entworfen.
 *
 * @author Meysam Aminzadeh on 8/6/2020.
 */
public interface SqlScript<E> {
    public List<E> select();
    public List<E> selectById(int id);
    public List<E> selectByColor(String color);
    public int insert(E e);
}
