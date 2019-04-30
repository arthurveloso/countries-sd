package br.edu.ufam.icomp.sd.countries;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import br.edu.ufam.icomp.sd.countries.adapter.CountryListAdapter;
import br.edu.ufam.icomp.sd.countries.dao.CDatabase;
import br.edu.ufam.icomp.sd.countries.services.Download;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatabaseListener {
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private			CDatabase			database;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private			ListView			countryList;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private 		ProgressBar 		progressBar;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private			BaseAdapter			adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setDatabase(CDatabase.getInstance(getApplicationContext()));
		setCountryList((ListView) findViewById(R.id.country_list));
		setProgressBar((ProgressBar) findViewById(R.id.progress_circular));
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (getDatabase().countryDAO().count() == 0) {
			new Download(getResources().getString(R.string.countries_end_point), new WeakReference<>(getApplicationContext()), this);
		} else {
			setAdapter(new CountryListAdapter(getDatabase().countryDAO().fetch(), this));
			getCountryList().setAdapter(getAdapter());
			getProgressBar().setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public <T> void onPopulated(List<T> data) {
		setAdapter(new CountryListAdapter(getDatabase().countryDAO().fetch(), this));
		getCountryList().setAdapter(getAdapter());
		getCountryList().deferNotifyDataSetChanged();
		getProgressBar().setVisibility(View.INVISIBLE);
	}
}
