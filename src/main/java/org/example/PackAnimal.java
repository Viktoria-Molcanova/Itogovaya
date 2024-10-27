package org.example;

class PackAnimal extends Animal {
    public PackAnimal(int id, String name, String type) {
        super(id, name,type);
    }

    @Override
    public String getType() {
        return "Вьючное животное";
    }
}
