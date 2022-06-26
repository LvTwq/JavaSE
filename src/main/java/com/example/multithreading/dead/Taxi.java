package com.example.multithreading.dead;

import com.example.multithreading.singlethreadedexecution.Point;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 吕茂陈
 * @date 2022/02/16 21:49
 */
public class Taxi {

    private Point location;
    private Point destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination)) {

        }
    }
}

class Dispatcher {

    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        this.taxis = new HashSet<>();
        this.availableTaxis = new HashSet<>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public synchronized Image getImage() {
        Image image = new Image();
        for (Taxi t : taxis) {
            image.drawMarker(t.getLocation());
        }
        return image;
    }
}

class Image {

    public void drawMarker(Point location) {

    }
}