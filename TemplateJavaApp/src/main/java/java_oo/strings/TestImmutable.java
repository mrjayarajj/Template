package java_oo.strings;

import java.util.HashMap;
import java.util.Map;

public class TestImmutable {

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
		Map<Country, Integer> gym = new HashMap<Country, Integer>();
		Country US = new Country("US");
		gym.put(US, 100);
		gym.put(new Country("IN"), 10);
		US.setCode("IN");
		System.out.println(gym);
	}

}