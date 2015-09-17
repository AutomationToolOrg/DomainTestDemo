package log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class logger {
	private static File file = null;

	// TODO Create a log file
	private static void CreateLogFile() {
		file = new File("log\\TraceData "
				+ (new Date().toString().replace(':', '-')) + ".txt");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdir();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TODO Write message into log file
	public static void Message(String message) {
		// TODO Create one log file if "file" is null
		if (file == null) {
			CreateLogFile();
		}

		// TODO Judge the file can be written, and output the message in it.
		if (!file.canWrite()) {
			System.out.println("This log file can't write!");
		} else {
			try {
				FileWriter out = new FileWriter(file, true);
				out.write(System.getProperty("line.separator"));
				out.write(new Date().toString() + ": ");
				out.write(message);
				out.close();
			} catch (Exception e) {
				System.out.print("Write file error!");
				e.printStackTrace();
			}
		}
	}

	public static void ErrorMessage(String path, String message) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static void SuccessfulMessage(String path, String message) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
