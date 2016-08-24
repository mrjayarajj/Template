package com.baseframework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Assert;
import org.junit.Test;

public class TestPath {

	@Test
	public void testDotPath() {
		try {
			Process p = Runtime.getRuntime().exec("cmd /C dir");
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			Assert.assertNotNull(p);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		URLClassLoader classLoader = (URLClassLoader) TestPath.class.getClassLoader();
		URL[] url = classLoader.getURLs();
		for (int i = 0; i < url.length; i++) {
			System.out.println(url[i]);
		}
	}

}
