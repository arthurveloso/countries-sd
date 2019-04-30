package br.edu.ufam.icomp.sd.countries.services;

import android.content.Context;
import android.util.Log;
import br.edu.ufam.icomp.sd.countries.DatabaseListener;
import br.edu.ufam.icomp.sd.countries.dao.CDatabase;
import br.edu.ufam.icomp.sd.countries.models.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.ref.WeakReference;
import java.util.List;

@SuppressWarnings("unchecked")
public class Download extends RESTService<List<Country>> {
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private			CDatabase					database;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private 		DatabaseListener 			listener;

	public Download(String endpoint, WeakReference<Context> reference, DatabaseListener listener) {
		super(endpoint, (Class<List<Country>>) new TypeToken<List<Country>>(){}.getRawType());
		setDatabase(CDatabase.getInstance(reference.get()));
		setListener(listener);
		execute();
	}

	@Override
	public void onSuccess(List<Country> data) {
		ObjectMapper			mapper			= new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		getDatabase().countryDAO().clear();
		getDatabase().countryDAO().save((List<Country>) mapper.convertValue(data, new TypeReference<List<Country>>(){}));
		getListener().onPopulated(getDatabase().countryDAO().fetch());
	}

	@Override
	public void onFailure(Exception error) {
		Log.e("DOWNLOAD", error.getMessage());
	}
}
