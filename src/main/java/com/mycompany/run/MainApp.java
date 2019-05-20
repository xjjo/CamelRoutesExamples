package com.mycompany.run;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import com.mycompany.routes.DirectRoutBuilder;
import com.mycompany.routes.DirectVmToRoutBuilder;
import com.mycompany.routes.ExceptionRoutBuilder;
import com.mycompany.routes.HttpRoutBuilder;
import com.mycompany.routes.RestToRestRouteBuilder;
import com.mycompany.routes.SedaRoutBuilder;
import com.mycompany.routes.SimpleRestRouteBuilder;
import com.mycompany.routes.TimerRoutBuilder;

import com.mycompany.routes.jetty.JettyHelloWorldRouteBuilder;
import com.mycompany.routes.jetty.JettyHttpServletRequestRouteBuilder;
import com.mycompany.routes.jetty.JettyHttpSessionRouteBuilder;
import com.mycompany.routes.jetty.JettyRequestParametersRouteBuilder;
import com.mycompany.routes.jetty.JettyTestRouteBuilder;

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
        // main.addRouteBuilder(new SayHelloRoutBuilder());//Retornando valores desde un Bean
        // main.addRouteBuilder(new SayHelloRoutBuilder()); // Using a timer - using a bean
        //main.addRouteBuilder(new BeanValidationRoutBuilder()); // entrada de datos file:json - ¡como capturamos y manejamos las excepciones????
        //main.addRouteBuilder(new ExceptionRoutBuilder());//manejando excepciones y flujos
       // main.addRouteBuilder( new DirectRoutBuilder() );// Direct component y seteo de un string en el body 
      // main.addRouteBuilder( new HttpRoutBuilder() );//http component - llamamos a un servicio soap
       // main.addRouteBuilder( new DirectVmToRoutBuilder() ); // igual que direct component pero pueden estar en distintos contextos en una misma jvm
       // main.addRouteBuilder(new SedaRoutBuilder()); // SEDA component allows to join routes together using a simple in memory queue.
       // main.addRouteBuilder(new RestToRestRouteBuilder()); // Proxy a Rest WebService añadiendo un wait al final que puede o no causar timeouts
        
        //Jetty consumer routes
        //main.addRouteBuilder(new JettyHelloWorldRouteBuilder()); // retornando hola mundo en el out
        //main.addRouteBuilder(new JettyHttpSessionRouteBuilder()); //Estableciendo una session
        //main.addRouteBuilder(new JettyHttpServletRequestRouteBuilder()); // obtencion de headers de la peticion html -> user-agent
        //main.addRouteBuilder(new JettyRequestParametersRouteBuilder()); // obtencion de parametros de la url 
        main.addRouteBuilder(new JettyTestRouteBuilder()); //intentando loguear el body de un request y un response.
        main.run(args);
    }

	

}

/**
como parametros de la vm si corre en versiones > 8 
--add-modules java.activation
--add-modules java.xml.bind
 */