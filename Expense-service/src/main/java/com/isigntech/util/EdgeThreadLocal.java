package com.isigntech.util;


import java.util.HashMap;
import java.util.Map;

public class EdgeThreadLocal {
	public static ThreadLocal<Map<String, String>> edgeThreadLocalholder = ThreadLocal.withInitial(HashMap::new);
}
