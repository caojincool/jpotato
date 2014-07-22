package com.lemsun.channel;

/**
 * 创建一个固定大小的字节数组.
 * User: Xudong
 * Date: 12-9-15
 * Time: 下午3:36
 */
public class ByteFIFO {
	private byte[] queue;
	private int capacity;
	private int head;

	public ByteFIFO(int cap) {
		capacity = ( cap > 0 ) ? cap : 1; // at least 1
		queue = new byte[capacity];
		head = 0;
	}

	/**
	 * 返回当前数据内容的大小
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}

	public synchronized void add(byte b) {

		queue[head] = b;
		head = ( head + 1 ) % capacity;

	}


	public boolean compare(byte[] target) {

		if(target.length == queue.length) {

			for(int i = 0; i<target.length; i++){
				if(queue[(head+i)%capacity] != target[i])
					return false;
			}

			return true;
		}

		return false;
	}

}
