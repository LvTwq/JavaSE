package com.example.multithreading.immutable;

/**
 * @author 吕茂陈
 * @date 2021/12/21 09:01
 */
public class Main4 {


}

final class MutablePerson {
    private String name;
    private String address;

    public MutablePerson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public MutablePerson(ImmutablePerson person) {
        this.name = person.getName();
        this.address = person.getAddress();
    }

    public synchronized void setPerson(String newName, String newAddress) {
        name = newName;
        address = newAddress;
    }

    public synchronized ImmutablePerson getImmutablePerson() {
        return new ImmutablePerson(this);
    }

    String getName() {
        return name;
    }

    String getAddress() {
        return address;
    }

    @Override
    public synchronized String toString() {
        return "MutablePerson{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

final class ImmutablePerson {
    private final String name;
    private final String address;

    public ImmutablePerson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public ImmutablePerson(MutablePerson person) {
        this.name = person.getName();
        this.address = person.getAddress();
    }

    public MutablePerson getMutablePerson() {
        return new MutablePerson(this);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "ImmutablePerson{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
