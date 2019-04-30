package br.edu.ufam.icomp.sd.countries.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.Collection;
import java.util.List;

public interface BaseDAO<T, ID> {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	ID		save(T model);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	List<ID> save(List<T> models);

	@Update(onConflict = OnConflictStrategy.REPLACE)
	int		update(T model);

	@Update(onConflict = OnConflictStrategy.REPLACE)
	int		update(List<T> models);

	@Delete
	int		delete(List<T> models);

	@Delete
	int		delete(T model);
}
