package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SparqlUtil {

	private String SPARQL_URL = "http://localhost:3030/film/update?update";

	private static SparqlUtil instance;

	public SparqlUtil() {

	}

	public static SparqlUtil getInstance() throws SecurityException, IOException {
		if (instance == null) {
			instance = new SparqlUtil();
		}
		return instance;
	}

	public void executeInsert(String query) throws UnsupportedEncodingException, Exception {
		LoggerUtil logger = LoggerUtil.getInstance();

		System.out.println(query);
		URL url = new URL(SPARQL_URL + "=" + URLEncoder.encode(query, "UTF-8"));
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setConnectTimeout(30000);
		urlConnection.setReadTimeout(30000);
		urlConnection.setDoOutput(true);
		urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
		OutputStream outputStream = urlConnection.getOutputStream();
		outputStream.flush();
		outputStream.close();

		InputStream inputStream = urlConnection.getInputStream();
		byte[] data = new byte[1024];
		StringBuilder sb = new StringBuilder();

		while (inputStream.read(data) != -1) {
			String s = new String(data, Charset.forName("utf-8"));
			sb.append(s);
		}

		if (sb.toString().contains("Success")) {
			System.out.println("insert succesful");
		}
	}

	public String createUpdateQuery(ArrayList<String> statements) {

		String query = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX : <http://www.semanticweb.org/cassia/ontologies/2016/FilmsToulouse> INSERT DATA { ";

		for (String statement : statements) {
			query += statement;
		}

		query += " }";
		return query;
	}
}
