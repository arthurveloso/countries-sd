package br.edu.ufam.icomp.sd.countries.services;

public interface RESTListener<T> {
	void onSuccess(T data);
	void onFailure(Exception error);
}
