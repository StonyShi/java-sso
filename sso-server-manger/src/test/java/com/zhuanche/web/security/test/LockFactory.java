public class LockFactory {

	public static void main(String[] args) throws Exception {
		Storage<Integer> storage = new Storage(100);
		final Random random = new Random();
		AbstractProducer<Integer> producer = new AbstractProducer(){
			public Integer doProduce(){
				return random.nextInt(99999);
			}
		}
		AbstractConsumer consumer = new AbstractConsumer(){
			public void doConsume(Integer value) {
				System.out.println(Thread.currentThread().getName+ " consume = " + value);
			}
		}
		for(int i = 0; i < 3; i++){
			new Thread(producer,"Producer_"+i).start();
		}
		for(int i = 0; i < 3; i++){
			new Thread(consumer,"Consumer_"+i).start();
		}
		Thread.sleep(99999999);
	}
	abstract class AbstractProducer<T> impements Runnable {
		Storage<T> storage;
		public Producer(Storage<T> storage) {
			this.storage = storage;
		}
		@Override
		public void run(){
			while(true) {
				storage.put(doProduce());
			}
		}
		public abstract T doProduce();
	}
	abstract class AbstractConsumer<T> impements Runnable {
		Storage<T> storage;
		public Producer(Storage<T> storage) {
			this.storage = storage;
		}
		@Override
		public void run(){
			while(true){
				doConsume(storage.take());
			}
		}
		public abstract void doConsume(T value);
	}
	class Storage<T> {
		int maxSize;
		final Lock lock;
		final Condition full;
		final Condition empty;
		final List<T> storage;
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
					System.out.println(Thread.currentThread().getName+ " is Full >>> wait.");
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
					System.out.println(Thread.currentThread().getName + " is Empty >>> wait.");
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
		}

		public boolean isFull(){
			return storage.size() == maxSize;
		}
		public boolean isEmpty(){
			return storage.size() == 0;
		}
	}
}