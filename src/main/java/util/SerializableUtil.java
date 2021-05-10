package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Media;

public class SerializableUtil {

	private static final String filepath = "./src/main/java/util/serializableOject";

	private static SerializableUtil instance;

	public SerializableUtil() {

	}

	public static SerializableUtil getInstance() {
		if (instance == null) {
			instance = new SerializableUtil();
		}
		return instance;
	}

	public void WriteObjectToFile(ArrayList<Media> serObj) throws Exception {
		LoggerUtil logger = LoggerUtil.getInstance();

		logger.WriteInfoLogInFile("---------- Start serialization " + LocalDateTime.now() + "---------- ");

		FileOutputStream fileOut = new FileOutputStream(filepath);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(serObj);

		objectOut.close();
		fileOut.close();

		logger.WriteInfoLogInFile("---------- End serialization " + LocalDateTime.now() + "---------- ");
	}

	public ArrayList<Media> ReadObjectToFile() throws Exception {
		LoggerUtil logger = LoggerUtil.getInstance();
		logger.WriteInfoLogInFile("---------- Start deserialization " + LocalDateTime.now() + "---------- ");
		ArrayList<Media> medias = new ArrayList<Media>();

		File f = new File(filepath);
		if (f.exists() && !f.isDirectory()) {
			FileInputStream fileIn = new FileInputStream(filepath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			medias = (ArrayList<Media>) objectIn.readObject();

			objectIn.close();
			fileIn.close();
		}

		logger.WriteInfoLogInFile("---------- End deserialization " + LocalDateTime.now() + "---------- ");
		return medias;
	}
}
