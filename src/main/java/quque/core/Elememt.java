package quque.core;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.FastDateFormat;

/** 
 * @author Author : jianqing.liu
 * @version version : created time：2019年3月8日 上午11:48:02 
 * @since since from 2019年3月8日 上午11:48:02 to now.
 * @description class description
 */
public class Elememt implements Delayed {
	
	private String code;
	
	private int type;
	
	private int dataType;
	
	private long expire;
	
	private FastDateFormat fastFormat = FastDateFormat.getInstance();
	
	public Elememt(String code, int type, int dataType, Date time) {
		this.code = code;
		this.type = type;
		this.dataType = dataType;
		this.expire = time.getTime();
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	
	@Override
	public int compareTo(Delayed o) {
		int n = (int) (this.getDelay(TimeUnit.MINUTES) - o.getDelay(TimeUnit.MINUTES));
		if(n == 0) {
			Elememt other = (Elememt)o;
			int et = this.type - other.getType();
			if(et == 0) {
				String code1 = this.code.substring(1);
				String code2 = other.getCode().substring(1);
				return Integer.parseInt(code1) - Integer.parseInt(code2);
			}
			return et;
		}
		return n;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the expire
	 */
	public long getExpire() {
		return expire;
	}

	/**
	 * @param expire the expire to set
	 */
	public void setExpire(long expire) {
		this.expire = expire;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "办理人 [办理号=" + code + ", 取号类型（0/网；1/现）=" + type + ", 业务类型=" + dataType + ", 预约办理时间=" + fastFormat.format(expire) + "]";
	}
	
}
