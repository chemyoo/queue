package quque.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** 
 * @author Author : jianqing.liu
 * @version version : created time：2019年3月8日 下午12:41:17 
 * @since since from 2019年3月8日 下午12:41:17 to now.
 * @description class description
 */
public class QueueMap {
	
	private QueueMap() {}
	
	private static final Map<Integer, EQueue> MAP = new ConcurrentHashMap<>();
	
	public static Elememt findElement(Integer type) {
		return MAP.get(type).takeElememt();
	}
	
	public static int takeOrder(Integer type, Elememt e) {
		return MAP.get(type).takeOrder(e);
	}
	
	public static void putElement(Elememt e) {
		int dataType = e.getDataType();
		if(MAP.containsKey(e.getDataType())) {
			MAP.get(e.getDataType()).addElememt(e);
		} else {
			EQueue q = new EQueue();
			q.setDataType(dataType);
			q.addElememt(e);
			MAP.put(dataType, q);
		}
	}
	
	public static boolean isEmpty() {
		for(Map.Entry<Integer, EQueue> e : MAP.entrySet()) {
			if(!e.getValue().getQueue().isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
