package hill.hotel.util;

public final class Log {
	public static void e(String msg) {
		System.out.println("[Error] " + msg);
	}
	public static void w(String msg) {
		System.out.println("[Warning] " + msg);
	}
	public static void i(String msg) {
		System.out.println("[Info] " + msg);
	}
}
