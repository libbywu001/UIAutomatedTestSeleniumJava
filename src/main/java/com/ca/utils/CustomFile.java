package com.ca.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ca.logger.LoggerControler;

/**
 * @author libby.wu
 * @date 2023/10/03 Description:
 */
public class CustomFile {
	final static LoggerControler log = LoggerControler.getLoggerControler(CustomFile.class);

	// Determine whether the file exists
	public static boolean fileExist(String filePath) {
		return new File(filePath).exists();
	}

	// create the file
	public static void createFile1(String filePath) {
		if (!CustomFile.fileExist(filePath)) {
			File file = new File(filePath);
			file.mkdir();
			log.info("new folder successful");
		}
	}

	// Delete the directory and all files and folders under the directory
	public static void deleteDirectory(String directoryPath) {
		File file = new File(directoryPath);
		// a folder
		if (file.isDirectory()) {
			File file1 = null;
			// Get subfiles and folders in this directory
			String[] childsFile = file.list();
			for (String s : childsFile) {
				// Check if foldPath ends with "\"
				if (directoryPath.endsWith((File.separator))) {
					file1 = new File(directoryPath + s);
				} else {
					file1 = new File(directoryPath + File.separator + s);
				}
				// Determine whether it is a file
				if (file1 != null && file1.isFile()) {
					file1.delete();
					file1.getAbsolutePath();
				} else if (file1 != null && file1.isDirectory()) { // 是文件夹
					deleteDirectory(file1.getAbsolutePath());
				}
			}
			file.delete();
		} else if (file.isFile()) {
			file.delete();
		}
	}

	/**
	 * Get all file names in a folder
	 *
	 * @param filePath
	 * @return
	 */
	public static List getFileName(String filePath) {
		ArrayList list = new ArrayList();
		File f = new File(filePath);
		if (!f.exists()) {
			System.out.println(filePath + " not exists");
		}

		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			list.add(fs.getName());
		}

		return list;
	}
}
