package z9devs.dao;

import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;

import z9devs.annotations.GenreQualifier;

@GenreQualifier
public class DAOGenre implements DAO {

	@Override
	public void create(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
