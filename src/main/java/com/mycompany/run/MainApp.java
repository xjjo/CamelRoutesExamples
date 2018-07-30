package com.mycompany.run;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import com.mycompany.routes.DirectRoutBuilder;
import com.mycompany.routes.DirectVmToRoutBuilder;
import com.mycompany.routes.ExceptionRoutBuilder;
import com.mycompany.routes.HttpProxyRouteBuilder;
import com.mycompany.routes.HttpRoutBuilder;
import com.mycompany.routes.RecipientListRoutBuilder;
import com.mycompany.routes.SedaRoutBuilder;

/**
 * A Camel Application
 */
public class MainApp {
	
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        //main.addRouteBuilder(new SimpleRestRouteBuilder());
        //main.addRouteBuilder(new TimerRoutBuilder()); //Retornando valores desde un Bean
        //main.addRouteBuilder(new SayHelloRoutBuilder());//Retornando valores desde un Bean
        //main.addRouteBuilder(new SayHelloRoutBuilder());
        //main.addRouteBuilder(new BeanValidationRoutBuilder()); // entrada de datos file:json - ¡como capturamos y manejamos las excepciones????
        //main.addRouteBuilder(new ExceptionRoutBuilder());//manejando excepciones y flujos
        //main.addRouteBuilder( new DirectRoutBuilder() );// Direct component y seteo de un string en el body 
        //main.addRouteBuilder( new HttpRoutBuilder() );//http component - llamamos a un servicio soap
        //main.addRouteBuilder( new DirectVmToRoutBuilder() ); // igual que direct component pero pueden estar en distintos contextos en una misma jvm
        //main.addRouteBuilder(new SedaRoutBuilder()); // SEDA component allows to join routes together using a simple in memory queue.
        //main.addRouteBuilder(new HttpProxyRouteBuilder());
        main.addRouteBuilder(new RecipientListRoutBuilder());
        main.run(args);
    }

	

}

/**
como parametros de la vm si corre en versiones > 8 
--add-modules java.activation
--add-modules java.xml.bind
 */