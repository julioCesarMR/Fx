/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.system.jcmr.dao;

import java.util.List;

/**
 *
 * @author Julio Cesar Meza Rios
 * @param <T>
 */
public interface BaseDao<T> {
    void insert(T object);
    void delete(T object);
    void update(T object);
}
