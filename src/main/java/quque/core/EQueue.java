package quque.core;

import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.DelayQueue;

/** 
 * @author Author : jianqing.liu
 * @version version : created time：2019年3月8日 上午11:51:55 
 * @since since from 2019年3月8日 上午11:51:55 to now.
 * @description class description
 */
public class EQueue {
	
	private int dataType;
	
	private Queue<Elememt> queue = new DelayQueue<>();

	/**
	 * @return the dataType
	 */
	public int getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the queue
	 */
	public Queue<Elememt> getQueue() {
		return queue;
	}
	
	/**
	 * @return the queue
	 */
	public void addElememt(Elememt e) {
		 queue.add(e);
	}
	
	/**
	 * @return the queue
	 */
	public Elememt takeElememt() {
		Elememt e = queue.poll();
		if(e == null && !queue.isEmpty()) {
//			queue.peek()
			Elememt e2 = queue.peek();
			queue.remove(e2);
			return e2;
		}
		return e;
	}
	
	public int takeOrder(Elememt e) {
		Elememt[] elements = queue.toArray(new Elememt[0]);
		List<Elememt> list = new CopyOnWriteArrayList<>(elements);
		Collections.sort(list);
		int index = 0;
		for(Elememt e1 : list) {
			if(e == e1) {
				return index;
			}
			index ++;
		}
		return 0;
	}

}
