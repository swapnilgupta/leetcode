package parsing;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class ParseCaptionJson {

	public static void main(String[] args) {
		// Path to the captions.json file
		String path = "C:\\Users\\swapngup\\Downloads\\zt\\captions.json";
		try {
			String fileName = readCaptionJson(new File(path));
			System.out.println("Original File Name: " + fileName);

			// Correcting the corrupted file name
			String correctedFileName = correctFileName(fileName);
			System.out.println("Corrected File Name: " + correctedFileName);
		} catch (Exception e) {
			System.err.println("Error reading captions.json: " + e.getMessage());
		}
	}

	public static String readCaptionJson(File file) throws Exception {
		String captionFileName = "";
		try {
			Path path = Paths.get(file.getPath());
			String jsonContent = new String(Files.readAllBytes(file.toPath()), Charset.forName("Windows-1252"));
			JSONObject jsonObject = new JSONObject(jsonContent);
			JSONObject filesObject = jsonObject.getJSONObject("files");
			if(filesObject.has("")) {
				//Prioritize asr caption vtt
				return filesObject.getString("");
			}
			String[] fileNames = JSONObject.getNames(filesObject);
			if (fileNames != null && fileNames.length > 0) {
				String lastFileName = fileNames[fileNames.length - 1];
				String lastFileValue = filesObject.getString(lastFileName);
				return lastFileValue;
			}
		} catch (Exception e) {
			throw new Exception("Error reading captions.json: " + e.getMessage());
		}
		// Return null if no files found
		return null;
	}



	private static String extractFileName(String jsonData) {
		// Simple regex to find the first VTT file name in the JSON string
		Pattern pattern = Pattern.compile("\"files\"\\s*:\\s*\\{\\s*\".*\"\\s*:\\s*\"([^\"]+)\"");
		Matcher matcher = pattern.matcher(jsonData);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	private static String correctFileName(String fileName) {
		// Detecting and replacing the corrupted character
		if (fileName != null && fileName.contains("?")) {
			return fileName.replace("?", "–"); // Replace with correct character
		}
		return fileName;
	}
}

