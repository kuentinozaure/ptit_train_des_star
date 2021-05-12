package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import entity.Media;

public class SparqlUtil {

	private String SPARQL_URL = "http://localhost:3030/FilmParis/update?update";

	private static SparqlUtil instance;

	public SparqlUtil() {

	}

	public static SparqlUtil getInstance() throws SecurityException, IOException {
		if (instance == null) {
			instance = new SparqlUtil();
		}
		return instance;
	}

	public void executeInsert(Media mediaToInsert) throws UnsupportedEncodingException, Exception {
		LoggerUtil logger = LoggerUtil.getInstance();

		String query = "PREFIX :<http://www.semanticweb.org/cassia/ontologies/2016/FilmsToulouse#>"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "INSERT DATA {"
				+ "  :Dora :a_pour_note \"7.5\"^^xsd:double."
				+ "  :Dora rdfs:label \"Dora\"@fr"
				+ "}";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(SPARQL_URL))
				.header("Content-Type", "application/x-www-form-urlencoded").header("update", query)
				.method("POST", HttpRequest.BodyPublishers.noBody()).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.body().contains("Success")) {
			System.out.println("insert successful");
		} else {
			System.out.println("insert has error");
		}

	}

}
