package edu.hw3.Task6;

import java.util.PriorityQueue;

public class Market implements StockMarket {
    private final PriorityQueue<Stock> queue;

    public Market() {
        queue = new PriorityQueue<>();
    }

    @Override
    public void add(Stock stock) {
        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        queue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return queue.peek();
    }
}
