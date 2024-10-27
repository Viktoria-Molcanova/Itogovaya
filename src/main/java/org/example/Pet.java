package org.example;


class Pet extends Animal {
    public Pet(int id, String name, String type) {
        super(id, name,type);
    }
    @Override
    public String getType() {
        return "Домашнее животное";
    }
}
