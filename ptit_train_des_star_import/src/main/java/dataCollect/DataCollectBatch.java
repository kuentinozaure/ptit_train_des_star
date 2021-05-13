package dataCollect;

import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Film;
import entity.Media;
import entity.SerieTv;
import entity.WebSerie;
import util.BatchUtil;
import util.LoggerUtil;
import util.SerializableUtil;
import util.SparqlUtil;

public class DataCollectBatch {

	public static void main(String[] args) throws Exception {
		LoggerUtil logger = LoggerUtil.getInstance();
		BatchUtil util = BatchUtil.getInstance();
		SerializableUtil utilSerializable = SerializableUtil.getInstance();
		SparqlUtil sparqlUtil = SparqlUtil.getInstance();
		ArrayList<Media> medias = utilSerializable.ReadObjectToFile();

		logger.WriteInfoLogInFile("---------- Start Import" + LocalDateTime.now() + "---------- ");

		// If serialization file dont have data
		if (medias.size() == 0) {

			medias = (ArrayList<Media>) util.getAllFilmInParis();
			utilSerializable.WriteObjectToFile(medias);

		} else {
			// if serializable file exist
			logger.WriteInfoLogInFile("Insertion started " + LocalDateTime.now() + "");

			String query = "";
			Integer i = 0;

			for (Media media : medias) {
				if (media instanceof Film) {
					Film film = (Film) media;

					ArrayList<String> statements = new ArrayList<String>();

					statements.add(" :" + film.stringFormat(film.getNomTournage()) + "_" + i + "  :a_pour_note \""
							+ film.getNote() + "\" ^^xsd:double. ");

					statements.add(" :" + film.stringFormat(film.getNomTournage()) + "_" + i
							+ "  :a_ete_tournee_a_la_position \"" + film.getCoordy() + ";" + film.getCoordx()
							+ "\" ^^xsd:string. ");

					statements.add(" :" + film.stringFormat(film.getNomTournage()) + "_" + i
							+ "  :a_ete_tournee_dans_le_lieux \"" + film.getAdresseTournage() + "\" ^^xsd:string. ");

					statements.add(" :" + film.stringFormat(film.getNomTournage()) + "_" + i + "  :a_pour_nom \""
							+ film.getNomTournage() + "\" ^^xsd:string. ");

					statements.add(" :" + film.stringFormat(film.getNomTournage()) + "_" + i + "  rdfs:label \""
							+ film.getNomTournage() + "\"@fr. ");

					statements.add(" :" + film.stringFormat(film.getNomTournage()) + "_" + i + " :a_ete_tourne_en \""
							+ film.getAnneeTournage() + "\"^^xsd:string. ");

					statements.add(" :" + film.stringFormat(film.getNomTournage()) + "_" + i + " rdfs:film :film. ");

					query = sparqlUtil.createUpdateQuery(statements);

					sparqlUtil.executeInsert(query);
					i++;

				} else if (media instanceof WebSerie) {
					WebSerie webSerie = (WebSerie) media;

					ArrayList<String> statements = new ArrayList<String>();

					statements.add(" :" + webSerie.stringFormat(webSerie.getNomTournage()) + "_" + i + "  rdfs:label \""
							+ webSerie.getNomTournage() + "\"@fr. ");

					statements.add(" :" + webSerie.stringFormat(webSerie.getNomTournage()) + "_" + i
							+ " :a_ete_tourne_en \"" + webSerie.getAnneeTournage() + "\"^^xsd:string. ");

					statements.add(" :" + webSerie.stringFormat(webSerie.getNomTournage()) + "_" + i
							+ "  :a_ete_tournee_a_la_position \"" + webSerie.getCoordy() + ";" + webSerie.getCoordx()
							+ "\" ^^xsd:string. ");

					statements.add(" :" + webSerie.stringFormat(webSerie.getNomTournage()) + "_" + i
							+ "  :a_ete_tournee_dans_le_lieux \"" + webSerie.getAdresseTournage()
							+ "\" ^^xsd:string. ");

					statements.add(" :" + webSerie.stringFormat(webSerie.getNomTournage()) + "_" + i
							+ "  :a_pour_nom \"" + webSerie.getNomTournage() + "\" ^^xsd:string. ");

					statements.add(" :" + webSerie.stringFormat(webSerie.getNomTournage()) + "_" + i
							+ " rdfs:serieWeb :serieWeb. ");

					query = sparqlUtil.createUpdateQuery(statements);

					sparqlUtil.executeInsert(query);
					i++;

				} else if (media instanceof SerieTv) {

					SerieTv serieTV = (SerieTv) media;
					ArrayList<String> statements = new ArrayList<String>();

					statements.add(":" + serieTV.stringFormat(serieTV.getNomTournage()) + "_" + i + " rdfs:label \""
							+ serieTV.getNomTournage() + "\"@fr.");

					statements.add(" :" + serieTV.stringFormat(serieTV.getNomTournage()) + "_" + i
							+ " :a_ete_tourne_en \"" + serieTV.getAnneeTournage() + "\"^^xsd:string. ");

					statements.add(" :" + serieTV.stringFormat(serieTV.getNomTournage()) + "_" + i
							+ "  :a_ete_tournee_a_la_position \"" + serieTV.getCoordy() + ";" + serieTV.getCoordx()
							+ "\" ^^xsd:string. ");

					statements.add(" :" + serieTV.stringFormat(serieTV.getNomTournage()) + "_" + i + "  :a_pour_nom \""
							+ serieTV.getNomTournage() + "\" ^^xsd:string. ");

					statements.add(" :" + serieTV.stringFormat(serieTV.getNomTournage()) + "_" + i
							+ "  :a_ete_tournee_dans_le_lieux \"" + serieTV.getAdresseTournage() + "\" ^^xsd:string. ");

					statements.add(" :" + serieTV.stringFormat(serieTV.getNomTournage()) + "_" + i
							+ " rdfs:serieTV :serieTV. ");

					query = sparqlUtil.createUpdateQuery(statements);

					sparqlUtil.executeInsert(query);
					i++;
				}
			}

		}

		logger.WriteInfoLogInFile("---------- End Import" + LocalDateTime.now() + "---------- ");

	}

}
