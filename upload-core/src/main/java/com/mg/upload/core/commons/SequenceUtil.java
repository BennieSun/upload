package com.mg.upload.core.commons;

import com.mg.util.core.GlobalHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class SequenceUtil {

	private static final Logger logger = LoggerFactory.getLogger(SequenceUtil.class);
	private static final String BASE_TIME;
	private static final String PREFIX_RANDOM;
	private static final AtomicLong ATOMIC_LONG = new AtomicLong(0L);
	private static final String[] BASE_CHARS = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	private SequenceUtil() {
	}

	static {
		long tempTime = System.nanoTime();
		int baseLength = 0;
		if (tempTime <= 0L) {
			tempTime = System.currentTimeMillis();
			baseLength = 9;
		} else {
			baseLength = 10;
		}
		BASE_TIME = loadSerialNo(TentoN(tempTime, 36), baseLength);
		int max = ((int) Math.pow(36, 16 - baseLength)) - 1;
		logger.info("Sequence max:" + max);
		System.out.println("Sequence max:" + max);

		int random = new Random().nextInt(max) + 1;
		logger.info("Sequence random+:" + random);
		System.out.println("Sequence random+:" + random);

		if (random <= ((int) Math.pow(36, 15 - baseLength)) - 1) {
			random += ((int) Math.pow(36, 15 - baseLength)) - 1;
		}

		logger.info("Sequence random:" + random);
		System.out.println("Sequence random:" + random);

		PREFIX_RANDOM = loadSerialNo(TentoN(random, 36), 16 - baseLength);

		logger.info("Sequence base time:" + BASE_TIME + " length:" + baseLength);
		System.out.println("Sequence base time:" + BASE_TIME + " length:"
				+ baseLength);
		logger.info("Sequence random:" + PREFIX_RANDOM + " length:"
				+ (16 - baseLength));
		System.out.println("Sequence random:" + PREFIX_RANDOM + " length:"
				+ (16 - baseLength));
	}

	/**
	 * 加载固定位数的字符串
	 * @param strBuilder
	 * @param fixed
	 * @return
	 */
	public static final StringBuilder loadSerialNo(StringBuilder strBuilder, int fixed) {
		if (strBuilder == null) {
			strBuilder = new StringBuilder();
		}
		while (strBuilder.length() < fixed) {
			strBuilder.insert(0, "0");
		}
		while (strBuilder.length() > fixed) {
			strBuilder.deleteCharAt(0);
		}
		return strBuilder;
	}

	/**
	 * 加载固定位数的字符串
	 *
	 * @param base
	 * @param fixed
	 * @return
	 */
	public static final String loadSerialNo(String base, int fixed) {
		return loadSerialNo(new StringBuilder(base), fixed).toString();
	}

	/**
	 * 加载固定位数的字符串
	 *
	 * @param fixed
	 * @return
	 */
	public static String loadRandomString(int fixed) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(BASE_TIME).append(TentoN(ATOMIC_LONG.getAndIncrement(), 36));
		while (strBuilder.length() < fixed) {
			strBuilder.insert(0, "0");
		}
		while (strBuilder.length() > fixed) {
			strBuilder.deleteCharAt(0);
		}
		return strBuilder.toString();
	}

	/**
	 * 加载固定位数的字符串
	 *
	 * @param fixed
	 * @return
	 */
	public static String loadRandomPrefixString(int fixed) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(PREFIX_RANDOM).append(BASE_TIME).append(TentoN(ATOMIC_LONG.getAndIncrement(), 36));
		while (strBuilder.length() < fixed) {
			strBuilder.insert(0, "F");
		}
		while (strBuilder.length() > fixed) {
			strBuilder.deleteCharAt(0);
		}
		return strBuilder.toString();
	}

	/**
	 * 2-36进制转换
	 *
	 * @param value
	 * @param number
	 * @return
	 */
	public static String TentoN(long value, int number) {
		// 负数处理
		if (value < 0) {
			StringBuilder strBuilder = new StringBuilder();
			TentoN(strBuilder, 0 - value, number);
			return strBuilder.insert(0, "-").toString();
		} else {
			return TentoN(new StringBuilder(), value, number).toString();
		}
	}

	/**
	 * 2-36进制转换
	 *
	 * @param value
	 * @param number
	 * @return
	 */
	public static StringBuilder TentoN(StringBuilder strBuilder, long value,
									   int number) {
		if (number <= 1 || number > BASE_CHARS.length) {
			throw new RuntimeException("Failed");
		}
		if (strBuilder == null) {
			strBuilder = new StringBuilder();
		}
		// 负数处理
		if (value < 0) {
			TentoN(strBuilder, 0 - value, number);
			return strBuilder.insert(0, "-");
		}
		if (value < number) {
			return strBuilder.insert(0, BASE_CHARS[(int) value]);
		} else {
			long n = value % (long) number;
			strBuilder.insert(0, BASE_CHARS[(int) n]);
			return TentoN(strBuilder, value / number, number);
		}
	}

	public static void main(String[] args) {
		/*System.out.println("curr:"+System.currentTimeMillis());
		System.out.println("nano:"+System.nanoTime());
//		System.out.println(SequenceUtil.loadRandomString(20));
		String gameCode = "twllytios";
		System.out.println(gameCode.length());
		int length = gameCode.length();
//		length -= gameCode.length();
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(BASE_TIME).append(TentoN(ATOMIC_LONG.getAndIncrement(), 36));
		System.out.println("BASE_TIME=" + BASE_TIME);
		System.out.println("ATOMIC_LONG=" + TentoN(ATOMIC_LONG.getAndIncrement(), 36));*/

		int now = GlobalHelper.currentTimestamp();

		System.out.println("current:"+GlobalHelper.timestampFormat(now,"yyyy-MM-dd HH:mm:ss"));


		String strTime = "2015-01-01T18:18:19";
		Date date = GlobalHelper.DataHelper.stringToDateTime(strTime.replace("T"," "),"yyyy-MM-dd HH:mm:ss");
		int dateTime = (int) (date.getTime()/1000);

		System.out.println(dateTime);

		System.out.println("date:"+GlobalHelper.timestampFormat(dateTime,"yyyy-MM-dd HH:mm:ss"));






		/*System.out.println("current time:"+now);

		String gameCode = "twl";
		int length = gameCode.length();

		//for (int i=0;i<20000000;i++) {
		String efunOrderId = gameCode.toUpperCase() + SequenceUtil.loadRandomString(20 - length);
		System.out.println(efunOrderId+":"+efunOrderId.length());

		String free = SequenceUtil.loadRandomPrefixString(18);
			System.out.println(free+":"+free.length());
		//}
		int c = 0 ;*/
//		for (; c < 1000; c++) {
//			efunOrderId = gameCode.toUpperCase() + SequenceUtil.loadRandomString(20-length);
//			System.out.println(efunOrderId);
//		}
	}

}
