package edu.eci.dosw.reto2;

public interface Builder {

    Builder addBread();

    Builder addMeat();

    Builder addCheese();

    Builder addLettuce();

    Builder addTomato();

    Builder addBacon();

    Builder addBBQSauce();

    Hamburger build();
}
