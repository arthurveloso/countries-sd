package br.edu.ufam.icomp.sd.countries.dao;

import android.content.Context;
import android.os.Environment;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import br.edu.ufam.icomp.sd.countries.models.Country;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Database(entities = {Country.class}, version = 1, exportSchema = false)
public abstract class CDatabase extends RoomDatabase {
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private		static		CDatabase			database;

	public static CDatabase getInstance(Context context) {
		if (getDatabase() == null) {
			setDatabase(Room.databaseBuilder(context, CDatabase.class, "countries.db").allowMainThreadQueries().fallbackToDestructiveMigration().build());
		}
		return getDatabase();
	}

	public abstract CountryDAO countryDAO();
}
