package be.intecbrussel.jpa.apps;

import be.intecbrussel.jpa.data.BeerDaoListImp;
import be.intecbrussel.jpa.model.Beer;

public class JavaBeersCafeApp {

    public static void main(String[] args) {

        Beer beer = new Beer("primero supremo", 11.1, 1.5, 72);
        Beer beer1 = new Beer("primero supremo", 11.1, 32, 72);


        BeerDaoListImp CafeApp = new BeerDaoListImp();

        //CafeApp.createBeer(beer);
        //CafeApp.createBeer(beer1);
        System.out.println(CafeApp.readBeer("primero supremo"));
        System.out.println(CafeApp.readBeer(6));
        Beer beer2 = CafeApp.readBeer(6);
        System.out.println(beer2);
        beer2.setStock(200);
        beer2.setPrice(4);
        CafeApp.updateBeer(beer2);
        CafeApp.deleteBeer(beer2);
        System.out.println(CafeApp.readBeer(beer2.getId()));
    }
}
