package dataCollect;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import entity.Film;
import entity.Media;
import entity.SerieTv;
import entity.WebSerie;
import util.BatchUtil;
import util.LoggerUtil;

public class DataCollectBatch {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		ArrayList<Media> medias = new ArrayList<Media>();
		LoggerUtil logger = new LoggerUtil();
		BatchUtil util = new BatchUtil();

		logger.WriteInfoLogInFile("---------- Start Import" + LocalDateTime.now() + "---------- ");
		medias = (ArrayList<Media>) util.getAllFilmInParis(logger);

		for (Media media : medias) {
			if (media instanceof Film) {
				Film film = (Film) media;
				System.out.println(film.getNomTournage());
				System.out.println(film.getNote());
			}

			if (media instanceof SerieTv) {
				SerieTv film = (SerieTv) media;
				System.out.println(film.getNomTournage());
			}

			if (media instanceof WebSerie) {
				WebSerie film = (WebSerie) media;
				System.out.println(film.getNomTournage());
			}
		}

		logger.WriteInfoLogInFile("---------- End Import" + LocalDateTime.now() + "---------- ");
	}

}
