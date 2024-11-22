
package edu.poo.interfaz;

import java.util.List;


public interface DAO<T> {
     public boolean insertInto(T miObject, String laRuta, String rutaDest);
    public List<T> selectFrom();
    public int getSerial();
    public int numRows();
    public boolean deleteFrom(int principalKey);
    public boolean updateSet(int principalKey, T miObject, String laRuta);  

    public T getOne(int principalKey);
}
