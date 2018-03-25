package org.rawdoughnuts.server;

import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ThreadPoolRouter implements Router {
    protected final ExecutorService pool;
    protected final Container container;

    public ThreadPoolRouter(final ExecutorService e, final Container c) {
        if (e==null) {throw new NullPointerException("ExecutorService is required");}
        if (c==null) {throw new NullPointerException("Container is required");}
        pool = e;
        container = c;
    }

    @Override
    public void route(final Socket s) {
        ResourceRequester request = container.accept(s);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    request.fulfill();
                }finally {

                }
           }
        });
    }
}