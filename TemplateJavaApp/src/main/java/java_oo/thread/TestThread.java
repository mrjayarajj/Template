package java_oo.thread;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

class BMCache {
	static SEOVo getVersionedCache() {
		return new SEOVo();
	}
}

public class TestThread {

	public static void main(String[] args) {

		while (true) {

			SEOVo vo = BMCache.getVersionedCache();
			new RefreshThreads(vo);
			new Scanner(System.in).nextLine();
			new UtilizingThreads(vo);

		}
		
	}

	static void displayCache(SEOVo vo) {
		System.out.print(vo.getSeoInfo().size());
	}

	static void constructCache(SEOVo vo) {
		for (int i = 0; i < 100; i++) {
			vo.getSeoInfo().put(i + "", i + "");
		}
	}

}

class SEOVo {
	Map<String, String> seoInfo = new ConcurrentHashMap<String, String>();

	public Map<String, String> getSeoInfo() {
		return seoInfo;
	}
}

class RefreshThreads implements Runnable {

	SEOVo vo = null;

	RefreshThreads(SEOVo vo) {
		this.vo = vo;
		for (int i = 0; i < 100; i++) {
			new Thread(this).start();
		}
	}

	public void run() {
		TestThread.constructCache(vo);
	}

}

class UtilizingThreads implements Runnable {

	SEOVo vo = null;

	UtilizingThreads(SEOVo vo) {
		this.vo = vo;
		for (int i = 0; i < 100; i++) {
			new Thread(this).start();
		}
	}

	public void run() {
		TestThread.displayCache(vo);
	}

}
