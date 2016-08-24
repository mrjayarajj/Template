package java_oo.io.character_stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class FileWR {

	public static void main(String[] args) throws Exception {

		Reader fr = new FileReader(new File("resource-examples/io/character_stream/FileA.txt"));
		Writer fw = new FileWriter(new File("resource-examples/io/character_stream/FileB.txt"));

		BufferedReader bin = new BufferedReader(fr);
		BufferedWriter bo = new BufferedWriter(fw);

		for (String line = ""; line != null; line = bin.readLine()) {

			bo.write(line);
			bo.write('\n');

			if (line.startsWith("1100")) {

				bo.write(line.replaceFirst("1100", "4567"));
				bo.write('\n');

			}

			fw.flush();
		}

	}
}
