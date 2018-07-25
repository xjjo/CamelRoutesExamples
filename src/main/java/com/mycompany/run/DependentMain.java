package com.mycompany.run;

import org.apache.camel.main.Main;

import com.mycompany.routes.DirectVmFromRoutBuilder;
import com.mycompany.routes.HttpRoutBuilder;

public class DependentMain {


		
 public static void main( String... args) throws Exception {
       Main main = new Main(); 
       main.addRouteBuilder( new DirectVmFromRoutBuilder() );
        main.run(args);
    }


}
