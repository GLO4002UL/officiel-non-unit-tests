package com.glo4002;

public class FriendMain implements Runnable {

    private FriendContext friendContext;

    public static void main(String[] args) {
        FriendMain main = new FriendMain();
        main.run();
    }

    @Override
    public void run() {
        try {
            friendContext = new FriendContext();
            friendContext.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
