package java_oo.gc;

import java.util.ArrayList;
import java.util.List;

public class TestGC {

	static class Country {

		private String code;

		public Country(String code) {
			this.code = code;
		}

		public int hashCode() {
			return this.code.hashCode();
		}

		public boolean equals(Object o) {

			if (o == null) {
				return false;
			}

			if (!(o instanceof Country)) {
				return false;
			}

			return this.code.equals(((Country) o).code);
		}

		public Country setCode(String code) {
			return new Country(code);
		}

		public String toString() {
			return this.code;
		}

		public int countryCodeLength() {
			return this.code.length();
		}

	}

	public static void main(String args[]) {

		List<Country> gym = new ArrayList<Country>();

		while (true) {
			gym.add(new Country("IN"));
			gym.addAll(gym);
			System.out.println(gym.size());
		}

		// 18290928
		// 26,84,35,454
	}
}
