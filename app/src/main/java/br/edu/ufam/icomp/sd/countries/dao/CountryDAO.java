package br.edu.ufam.icomp.sd.countries.dao;

import androidx.room.Dao;
import androidx.room.Query;
import br.edu.ufam.icomp.sd.countries.models.Country;

import java.util.List;

@Dao
public interface CountryDAO extends BaseDAO<Country, Long> {
	@Query("select * from countries order by name asc")
	List<Country> fetch();

	@Query("select * from countries where id = :id")
	Country fecth(long id);

	@Query("select count(*) from countries")
	long count();

	@Query("delete from countries")
	void clear();
}
