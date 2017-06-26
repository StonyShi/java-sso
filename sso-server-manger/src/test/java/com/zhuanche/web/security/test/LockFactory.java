package com.zhuanche.web.security.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockFactory {
	//
	public static void main(String[] args) throws Exception {
		Storage<Integer> storage = new Storage<Integer>(100);
		final Random random = new Random();

		AbstractProducer<Integer> producer = new AbstractProducer<Integer>(storage){
			public Integer doProduce(){
				return random.nextInt(99999);
			}
		};
		AbstractConsumer<Integer> consumer = new AbstractConsumer<Integer>(storage) {
			@Override
			public void doConsume(Integer value) {
//				System.out.println(Thread.currentThread().getName() + " consume = " + value);
			}
		};
		for(int i = 0; i < 3; i++){
			new Thread(producer,"Producer_"+i).start();
		}
		for(int i = 0; i < 3; i++){
			new Thread(consumer,"Consumer_"+i).start();
		}
		Thread.sleep(1000);
		producer.shutdown();
		consumer.shutdown();
	}
	static abstract class AbstractFace {
		volatile boolean shutdown = false;
		public void shutdown(){
			this.shutdown = true;
		}
	}
	static abstract class AbstractProducer<T> extends AbstractFace implements Runnable {
		Storage<T> storage;
		public AbstractProducer(Storage<T> storage) {
			this.storage = storage;
		}
		@Override
		public void run(){
			while(!shutdown) {
				storage.put(doProduce());
			}
		}
		public abstract T doProduce();
	}
	static abstract class AbstractConsumer<T> extends AbstractFace  implements Runnable {
		Storage<T> storage;
		public AbstractConsumer(Storage<T> storage) {
			this.storage = storage;
		}
		@Override
		public void run(){
			while(!shutdown){
				doConsume(storage.take());
			}
		}
		public abstract void doConsume(T value);
	}
	static class Storage<T> {
		int maxSize;
		final Lock lock;
		final Condition full;
		final Condition empty;
		final Queue<T> storage;
		public Storage(int maxSize){
			this.maxSize = maxSize;
			storage = new LinkedList();
			lock = new ReentrantLock();
			full = lock.newCondition();
			empty = lock.newCondition();
		}
		public void put(T value){
			lock.lock();
			try {
				while(isFull()){
					System.out.println(Thread.currentThread().getName() + " | The storage is Full >>> wait.");
					full.await();
				}
				storage.add(value);
				empty.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		public T take() {
			lock.lock();
			try {
				while(isEmpty()){
					System.out.println(Thread.currentThread().getName() + " | The storage is Empty >>> wait.");
					empty.await();
				}
				T t = storage.poll();
				full.signalAll();
				return t;
			} catch (InterruptedException e){
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			return null;
		}

		public boolean isFull(){
			return storage.size() == maxSize;
		}
		public boolean isEmpty(){
			return storage.size() == 0;
		}
	}
}