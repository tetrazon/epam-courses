package by.training.demothreads.ex17threadauction;

import java.util.Random;

public class AuctionRunner {
    public static void main(String[ ] args) {
        Auction auction = new Auction();
        int startPrice = new Random().nextInt(100);
        for (int i = 0; i < 5; i++) {
            Bid thread = new Bid(i, startPrice, auction.getBarrier());
            auction.add(thread);
            thread.start();
        }
    }
}
