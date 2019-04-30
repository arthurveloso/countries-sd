package br.edu.ufam.icomp.sd.countries.services;

import android.content.Context;
import android.os.AsyncTask;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.ref.WeakReference;

public abstract class RESTService<T> extends AsyncTask<Void, Void, ResponseEntity<T>> implements RESTListener<T> {
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private		String							endpoint;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private 	Class<T>						reference;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private 	Exception 						exception;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PROTECTED)
	private 	T 								data;

	public RESTService(String endpoint, Class<T> reference) {
		setEndpoint(endpoint);
		setReference(reference);
	}

	@Override
	protected ResponseEntity<T> doInBackground(Void... voids) {
		try {
			RestTemplate 						template		= new RestTemplate();
			MappingJackson2HttpMessageConverter converter		= new MappingJackson2HttpMessageConverter();

			converter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			template.getMessageConverters().add(converter);
			return template.getForEntity(getEndpoint(), getReference());
		} catch (Exception exception) {
			setException(exception);
			return null;
		}
	}

	@Override
	protected void onPostExecute(ResponseEntity<T> data) {
		if (getException() == null) {
			onSuccess(data != null? data.getBody() : null);
		} else {
			onFailure(getException());
		}
	}
}
