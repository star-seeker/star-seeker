package com.book.concurrency.deadlock;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * 通过开放调用来避免在相互协作的对象之间产生死锁
 * @author zhangyoubao
 * @version 2021/6/8
 */
public class OpenCall {

    class Taxi {
        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public void setLocation(Point location) {
            boolean reachDestination;
            synchronized (this) {
                this.location = location;
                reachDestination = location.equals(destination);
            }
            if (reachDestination) {
                dispatcher.notifyAvailable(this);
            }
        }
    }

    class Dispatcher {
        private final Set<Taxi> taxis;
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<>();
            availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

    }
}
