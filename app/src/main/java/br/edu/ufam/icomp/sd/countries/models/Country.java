package br.edu.ufam.icomp.sd.countries.models;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "countries")
public class Country {
	@Getter
	@Setter
	@PrimaryKey(autoGenerate = true)
	private 		long			id;

	@Getter
	@Setter
	@Nullable
	private 		String			name;

	@Getter
	@Setter
	@Nullable
	private 		String			alpha2Code;

	@Getter
	@Setter
	@Nullable
	private 		String			alpha3Code;

	@Getter
	@Setter
	@Nullable
	private 		String			capital;

	@Getter
	@Setter
	@Nullable
	private 		String			region;

	@Getter
	@Setter
	@Nullable
	private 		String			subRegion;

	@Getter
	@Setter
	@Nullable
	private 		Long			population;

	@Getter
	@Setter
	@Nullable
	private 		String			demonym;

	@Getter
	@Setter
	@Nullable
	private 		Double			area;

	@Getter
	@Setter
	@Nullable
	private 		Double			gini;
}
