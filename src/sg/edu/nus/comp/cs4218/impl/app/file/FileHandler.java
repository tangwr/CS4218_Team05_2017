package sg.edu.nus.comp.cs4218.impl.app.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.GrepException;

public class FileHandler {
	/**
	 * Checks if a file is readable.
	 * 
	 * @param filePath
	 *            The path to the file
	 * @return True if the file is readable.
	 * @throws CatException
	 *             If the file is not readable
	 * @throws GrepException
	 */

	public boolean checkIfFileIsReadable(Path filePath) throws Exception {

		if (Files.isDirectory(filePath)) {
			throw new Exception("This is a directory");
		}
		if (Files.exists(filePath) && Files.isReadable(filePath)) {
			return true;
		} else {
			throw new Exception("Could not read file");
		}
	}

	public List<String> readAllLines(String filePath) {
		Path path = Paths.get(filePath);
		String[] output;

		try {
			return Files.readAllLines(path);
		} catch (IOException e) {
			return null;
		}
	}

	public List<Path> getValidFilePathsFromString(String[] strFilePaths, int start, int end) {
		List<Path> validFilePaths = new ArrayList<>();
		Path filePath;

		for (int i = start; i <= end; i++) {

			try {
				filePath = Paths.get(strFilePaths[i]);
			} catch (InvalidPathException e) {
				continue;
			}

			try {
				if (checkIfFileIsReadable(filePath)) {
					validFilePaths.add(filePath);
				}
			} catch (Exception e) {
				continue;
			}
		}

		return validFilePaths;
	}
}
