package by.training.demothreads.ex15resourcepool;

import by.training.demothreads.ex15resourcepool.exception.ResourсeException;

public class Client extends Thread {
    private boolean reading = false;
    private ChannelPool<AudioChannel> pool;
    public Client (ChannelPool<AudioChannel> pool) {
        this.pool = pool;
    }
    public void run() {
        AudioChannel channel = null;
        try {
            channel = pool.getResource(500); // изменить на 100
            reading = true;
            System.out.println("Channel Client #" + this.getId()
                    + " took channel #" + channel.getСhannellId());
            channel.using();
        } catch (ResourсeException e) {
            System.out.println("Client #" + this.getId() + " lost ->"
                    + e.getMessage());
        } finally {
            if (channel != null) {
                reading = false;
                System.out.println("Channel Client #" + this.getId() + " : "
                        + channel.getСhannellId() + " channel released");
                pool.returnResource(channel);
            }
        }
    }
    public boolean isReading() {
        return reading;
    }
}

