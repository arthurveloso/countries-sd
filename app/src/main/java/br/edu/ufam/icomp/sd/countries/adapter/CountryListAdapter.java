package br.edu.ufam.icomp.sd.countries.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.edu.ufam.icomp.sd.countries.R;
import br.edu.ufam.icomp.sd.countries.models.Country;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CountryListAdapter extends BaseAdapter {
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private			List<Country>			countries;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private 		Activity				activity;

	public CountryListAdapter(List<Country> countries, Activity activity) {
		setCountries(countries);
		setActivity(activity);
	}

	@Override
	public int getCount() {
		return getCountries().size();
	}

	@Override
	public Object getItem(int position) {
		return getCountries().get(position);
	}

	@Override
	public long getItemId(int position) {
		return getCountries().get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View			view			= getActivity().getLayoutInflater().inflate(R.layout.item_list, parent, false);
		Country			country			= getCountries().get(position);
		TextView		countryName		= view.findViewById(R.id.country_name);
		TextView		countryCapital	= view.findViewById(R.id.capital_name);

		countryName.setText(country.getName());
		countryCapital.setText(country.getCapital());

		return view;
	}
}
