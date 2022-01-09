package z9devs.dao;

import java.util.List;

import javax.ejb.Local;

public interface DAO 
{	
	// CRUD METHODS
	
	// C - Create
	public void create(Object o);
	
	// R - Read
	public List<Object> getAll();
	public Object getById(int id);
	
	// U - Update
	public void update(Object o);
	
	// D - Delete
	public boolean deleteById(int id);
}