package quque.run;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import quque.core.Elememt;
import quque.core.QueueMap;

/** 
 * @author Author : jianqing.liu
 * @version version : created time��2019��3��8�� ����12:54:57 
 * @since since from 2019��3��8�� ����12:54:57 to now.
 * @description �����û��˳�����⣺��θĶ��ǰ������˳��
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
		// ȡ�Ŵ���
		for(int i = 1; i <= 500; i ++) {
			Elememt e = new Elememt(getCode(i, i % 2), i % 2, i % 6, getDate());
			QueueMap.putElement(e);
			if(i == 399) {
				takeOrderEle = e;
				System.err.println("��ѯ�Ŷ���Ա��" + takeOrderEle + "��");
			}
		}
		int m = 1;
		while(true) {
			int order = QueueMap.takeOrder(takeOrderEle.getDataType(), takeOrderEle);
			if(order > 0) {
				System.err.println(takeOrderEle.getCode() + "ǰ�滹��" + order + "λ�ͻ��ڵȴ�...\n");
			}
			if(order < 10 && order > 3) {
				// ģ���������
				Elememt e = new Elememt(getCode(500 + m, 0), 0, takeOrderEle.getDataType(), getDate());
				if(e.getExpire() < System.currentTimeMillis()) {
					System.err.println("ԤԼ���ѹ��ڣ����ܲ��-------->" + e);
				} else {
					System.err.println("���в��-------->" + e);
					QueueMap.putElement(e);
					m ++;
				}
			} 
			int r = random.nextInt(6);
			System.err.print("ҵ������" + r + "��ʼ�к�---->");
			Elememt ele = QueueMap.findElement(r);
			if(ele != null) {
				System.err.println(ele);
			} else {
				System.err.println("û�к���");
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
