package util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entity.Film;
import entity.Media;
import entity.SerieTv;
import entity.WebSerie;

public class BatchUtil {

	public BatchUtil() {
	}

	/**
	 * Read the dataset json for film in paris
	 * 
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public List<Media> getAllFilmInParis(LoggerUtil logger) throws IOException, InterruptedException, ParseException {

		logger.WriteInfoLogInFile("Get all film in Paris at " + LocalDateTime.now());
		List<Media> mediaList = new ArrayList<Media>();

		List<String> filmAlreadyNote = new ArrayList<String>();

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("./src/main/java/util/lieux-de-tournage-a-paris.json"));

		JSONArray jsonObject = (JSONArray) obj;

		Iterator<JSONObject> iterator = jsonObject.iterator();

		while (iterator.hasNext()) {

			JSONObject entity = iterator.next();
			JSONObject field = (JSONObject) entity.get("fields");

			String typeTournage = (String) field.get("type_tournage");

			String anneeTournage = (String) field.get("annee_tournage");
			String debutTournage = (String) field.get("date_debut");
			String finTournage = (String) field.get("date_fin");
			String adresseTournage = (String) field.get("adresse_lieu");
			String nomTournage = (String) field.get("nom_tournage");
			String nomProducteur = (String) field.get("nom_producteur");
			Double coordx = (Double) field.get("coord_x");
			Double coordy = (Double) field.get("coord_y");

			if (typeTournage.equals("Long métrage") && !filmAlreadyNote.contains(nomTournage)) {

				filmAlreadyNote.add(nomTournage);
				mediaList.add(new Film(anneeTournage, debutTournage, finTournage, typeTournage, adresseTournage,
						nomTournage, nomProducteur, coordx, coordy, getFilmByName(nomTournage, logger)));

				logger.WriteInfoLogInFile("Add Long métrage at " + LocalDateTime.now());

			} else if (typeTournage.equals("Série TV")) {
				mediaList.add(new SerieTv(anneeTournage, debutTournage, finTournage, typeTournage, adresseTournage,
						nomTournage, nomProducteur, coordx, coordy));

				logger.WriteInfoLogInFile("Add Série TV " + nomTournage + " at " + LocalDateTime.now());

			} else if (typeTournage.equals("Série Web")) {
				mediaList.add(new WebSerie(anneeTournage, debutTournage, finTournage, typeTournage, adresseTournage,
						nomTournage, nomProducteur, coordx, coordy));

				logger.WriteInfoLogInFile("Add Série Web " + nomTournage + " at " + LocalDateTime.now());

			}

		}

		return mediaList;

	}

	public String getFilmByName(String filmName, LoggerUtil logger)
			throws IOException, InterruptedException, ParseException {

		logger.WriteInfoLogInFile("Get Film notation by " + filmName + " note at " + LocalDateTime.now());

		String note = "";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://imdb-internet-movie-database-unofficial.p.rapidapi.com/film/"
						+ filmName.replace(" ", "").replace("%", "%25")))
				.header("x-rapidapi-key", "131c4bfbf9mshc19880780de26f2p1b60c0jsn3b3c48f0815c")
				.header("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		if (!response.body().contains("404")) {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(response.body());
			JSONObject jsonObject = (JSONObject) obj;
			note = (String) jsonObject.get("rating");
		}

		return note != "" ? note : "0";
	}
}
