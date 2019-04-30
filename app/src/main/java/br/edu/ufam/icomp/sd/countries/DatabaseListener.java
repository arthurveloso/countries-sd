package br.edu.ufam.icomp.sd.countries;

import java.util.List;

public interface DatabaseListener {
	<T> void onPopulated(List<T> data);
}
