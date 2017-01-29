package com.thread.producerConsumer.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable {

	private final BlockingQueue<Integer> sharedQueue;

	public Producer(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			try {
				System.out.println("Produced : " + i);
				// put/produce into sharedQueue.
				sharedQueue.put(i);
			} catch (InterruptedException ex) {

			}
		}
	}

}

class Consumer implements Runnable {

	private BlockingQueue<Integer> sharedQueue;

	public Consumer(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// take/consume from sharedQueue.
				System.out.println("CONSUMED : " + sharedQueue.take());
			} catch (InterruptedException ex) {

			}
		}
	}

}

public class ProducerConsumerBlockingQueue {

	public static void main(String[] args) {

		BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();

		Producer producer = new Producer(sharedQueue);
		Consumer consumer = new Consumer(sharedQueue);

		Thread producerThread = new Thread(producer, "ProducerThread");
		Thread consumerThread = new Thread(consumer, "ConsumerThread");
		producerThread.start();
		consumerThread.start();

	}
}
