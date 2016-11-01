package com.mycompany.app;

import java.util.List;

public interface DAO<T> {
	public T insert(T t);
	public boolean delete(T t);
	public boolean update(T t);
	public T getById(int numT);
	public List<T> 	findAll();
}
