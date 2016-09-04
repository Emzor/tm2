package core.util;

import java.util.HashMap;

public class Clipboard {
	private static Clipboard instance = null;
	private static HashMap<String, String> clipboard = new HashMap<>();

	private Clipboard() {}

	public static Clipboard getInstance() {
		if(instance == null) {
			instance = new Clipboard();
			return instance;
		} else return instance;
	}

	public void put(String key, String val) {
		clipboard.put(key, val);
	}

	public String get(String key) {
		return clipboard.get(key);
	}

}
