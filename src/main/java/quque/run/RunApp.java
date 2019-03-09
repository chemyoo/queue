package quque.run;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import quque.core.Elememt;
import quque.core.QueueMap;

/** 
 * @author Author : jianqing.liu
 * @version version : created time：2019年3月8日 下午12:54:57 
 * @since since from 2019年3月8日 下午12:54:57 to now.
 * @description 办理号没有顺序，问题：如何改动是办理号有顺序？
 */
public class RunApp {
	
	private static Calendar cal = Calendar.getInstance();
	
	private static Random random = new Random();
	
	private static int seed = 9;

	public static void main(String[] args) {
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Elememt takeOrderEle = null;
		// 取号触发
		for(int i = 1; i <= 500; i ++) {
			Elememt e = new Elememt(getCode(i, i % 2), i % 2, i % 6, getDate());
			QueueMap.putElement(e);
			if(i == 399) {
				takeOrderEle = e;
				System.err.println("查询排队人员【" + takeOrderEle + "】");
			}
		}
		int m = 1;
		while(true) {
			int order = QueueMap.takeOrder(takeOrderEle.getDataType(), takeOrderEle);
			if(order > 0) {
				System.err.println(takeOrderEle.getCode() + "前面还有" + order + "位客户在等待...\n");
			}
			if(order < 10 && order > 3) {
				// 模拟连续插队
				Elememt e = new Elememt(getCode(500 + m, 0), 0, takeOrderEle.getDataType(), getDate());
				if(e.getExpire() < System.currentTimeMillis()) {
					System.err.println("预约号已过期，不能插队-------->" + e);
				} else {
					System.err.println("进行插队-------->" + e);
					QueueMap.putElement(e);
					m ++;
				}
			} 
			int r = random.nextInt(6);
			System.err.print("业务类型" + r + "开始叫号---->");
			Elememt ele = QueueMap.findElement(r);
			if(ele != null) {
				System.err.println(ele);
			} else {
				System.err.println("没有号了");
			}
			if(QueueMap.isEmpty()) {
				break;
			}
		}
	}
	
	public static Date getDate() {
		int r = random.nextInt(seed);
		switch(r) {
		case 0: cal.set(Calendar.HOUR_OF_DAY, 9); break;
		case 1: cal.set(Calendar.HOUR_OF_DAY, 10); break;
		case 2: cal.set(Calendar.HOUR_OF_DAY, 11); break;
		case 3: cal.set(Calendar.HOUR_OF_DAY, 12); break;
		case 4: cal.set(Calendar.HOUR_OF_DAY, 13); break;
		case 5: cal.set(Calendar.HOUR_OF_DAY, 14); break;
		case 6: cal.set(Calendar.HOUR_OF_DAY, 15); break;
		case 7: cal.set(Calendar.HOUR_OF_DAY, 16); break;
		default: cal.set(Calendar.HOUR_OF_DAY, 18);
		}
		return cal.getTime();
	}
	
	public static String getCode(int index, int model) {
		String first = null;
		if(model == 0) {
			first = "A";
		} else {
			first = "M";
		}
		String code = first + "000000";
		String sindex = Integer.toString(index);
		return code.substring(0, code.length() - sindex.length()) + sindex;
	}
	
}
