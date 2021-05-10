package dataCollect;

import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Media;
import util.BatchUtil;
import util.LoggerUtil;
import util.SerializableUtil;

public class DataCollectBatch {

	public static void main(String[] args) throws Exception {
		LoggerUtil logger = LoggerUtil.getInstance();
		BatchUtil util = BatchUtil.getInstance();
		SerializableUtil utilSerializable = SerializableUtil.getInstance();
		ArrayList<Media> medias = utilSerializable.ReadObjectToFile();

		logger.WriteInfoLogInFile("---------- Start Import" + LocalDateTime.now() + "---------- ");

		// If serialization file dont have data
		if (medias.size() == 0) {

			medias = (ArrayList<Media>) util.getAllFilmInParis();
			utilSerializable.WriteObjectToFile(medias);

		} else {
			// if serializable file exist
			logger.WriteInfoLogInFile("Nombre de media " + medias.size() + "");
		}

		logger.WriteInfoLogInFile("---------- End Import" + LocalDateTime.now() + "---------- ");

	}

}
