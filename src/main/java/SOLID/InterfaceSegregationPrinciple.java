package SOLID;


public class InterfaceSegregationPrinciple {
}


interface Worker {
    abstract public void canWork();



    // дале идут 500 методов
}

interface EatableAndSleep {
    abstract public void canEat();
    void canSleep();
}

class Human implements Worker, EatableAndSleep {
    @Override
    public void canWork() {

    }

    @Override
    public void canEat() {

    }

    @Override
    public void canSleep() {

    }
}

class Robot implements Worker{
    @Override
    public void canWork() {

    }
}


