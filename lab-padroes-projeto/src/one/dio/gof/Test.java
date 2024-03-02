package one.dio.gof;

import one.dio.gof.facade.Facade;
import one.dio.gof.singleton.SingletonEager;
import one.dio.gof.singleton.SingletonLazy;
import one.dio.gof.singleton.SingletonLazyHolder;
import one.dio.gof.strategy.*;

public class Test {

    public static void main(String[] args) {

        // Singleton

        SingletonLazy lazy = SingletonLazy.getInstancia();
        SingletonEager eager = SingletonEager.getInstancia();
        SingletonLazyHolder lazyHolder = SingletonLazyHolder.getInstancia();

        System.out.println(lazy);
        System.out.println(eager);
        System.out.println(lazyHolder);

        lazy = SingletonLazy.getInstancia();
        eager = SingletonEager.getInstancia();
        lazyHolder = SingletonLazyHolder.getInstancia();

        System.out.println(lazy);
        System.out.println(eager);
        System.out.println(lazyHolder);

        // Strategy

        Comportamento comportamentoNormal = new ComportamentoNormal();
        Comportamento comportamentoAgressivo = new ComportamentoAgressivo();
        Comportamento comportamentoDefensivo = new ComportamentoDefensivo();

        Robo robo = new Robo();

        robo.setComportamento(comportamentoNormal);

        robo.mover();

        robo.setComportamento(comportamentoDefensivo);

        robo.mover();

        robo.setComportamento(comportamentoAgressivo);

        robo.mover();
        robo.mover();

        // Facade

        Facade facade = new Facade();
        facade.migrarCliente("Gabriel", "14825000");

    }

}
