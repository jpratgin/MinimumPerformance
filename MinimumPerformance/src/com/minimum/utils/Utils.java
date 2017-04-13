package com.minimum.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Utils {

	/**
	 * get a number of words and save in a file.
	 * 
	 * @param numWords
	 * @return
	 * @throws IOException
	 */
	public String saveRandomWords(int numWords) throws IOException {

		// create a temporal folder
		String folderName = "c:\\temporal\\";
		File folder = new File(folderName);
		if (folder.exists()) {
			folder.delete();
		}
		folder.mkdirs();

		// create new file
		String nameFile = folderName + "randomWords.txt";
		File file = new File(nameFile);
		file.createNewFile();
		FileOutputStream fout = new FileOutputStream(file);
		
		Random random = new Random();

		for (int i = 0; i < numWords; i++) {

			
			// char array with a random length;
			char[] word = new char[random.nextInt(10) + 2];
			for (int j = 0; j < word.length; j++) {
				// generate a random character
				word[j] = (char) ('a' + random.nextInt(26));
			}

			// convert char array to string.
			String words = new String(word);

			byte[] buffer = new byte[1024];

			// convert string to byte array.
			buffer = words.getBytes();

			// write into FileOutputStream.
			fout.write(buffer);

		}
		// close FileOutputStream
		fout.close();
		
		return nameFile;
	}

	/**
	 * copy content to number files
	 * 
	 * @param nameFile
	 * @param numFiles
	 * @return String[];
	 * @throws IOException
	 */
	public String[] copyContent(String nameFile, int numFiles) throws IOException {

		// get folder name.
		String nameFolder = nameFile.substring(0, nameFile.lastIndexOf("\\"));
		
		// String which it will contain file names.
		String[] files = new String[numFiles];
		
		for (int i = 0; i < numFiles; i++) {
		
			//input file.
			File fileIn = new File(nameFile);
			FileInputStream fin = new FileInputStream(fileIn);
			
			
			byte[] buffer = new byte[1024];
			
			//output file
			String nameOutput = nameFolder + "\\backup_" + i + ".txt";
			File fileOut = new File(nameOutput);
			FileOutputStream fout = new FileOutputStream(fileOut);

			// read from input file and write into output file.
			while (fin.read(buffer) != -1) {
				fout.write(buffer);

			}
			
			// close both files.
			fout.close();
			fin.close();
			
			// save output filename.
			files[i] = nameOutput;
		}
		return files;

	}

	/**
	 * compress files content into zip file.
	 * 
	 * @param files
	 * @param nameFile
	 * @throws IOException
	 */
	public void compressContent(String[] files, String nameFile) throws IOException {

		String nameFolder = nameFile.substring(0, nameFile.lastIndexOf("\\"));
		
		// zip file.
		String nameZipFile = nameFolder + "\\content.zip";
		File file = new File(nameZipFile);
		FileOutputStream fout = new FileOutputStream(file);
		ZipOutputStream zipFile = new ZipOutputStream(fout);

		// read input files.
		for (int i = 0; i < files.length; i++) {

			File fileIn = new File(files[i]);
			FileInputStream fin = new FileInputStream(fileIn);
			zipFile.putNextEntry(new ZipEntry(files[i].toString()));

			byte[] buffer = new byte[1024];
			
			
			while (fin.read(buffer) > 0) {
				zipFile.write(buffer);

			}
			
			fin.close();

		}

		zipFile.close();

	}

}
