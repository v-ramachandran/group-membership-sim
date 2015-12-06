package edu.utexas.cs.systems.membership.simulator.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Master {

    /**
     * Start the Muse application.
     * 
     * @param args The command line arguments necessary for the application.
     */
    public static void main(final String[] args) throws Exception{
        
        try(final ClassPathXmlApplicationContext context = 
            new ClassPathXmlApplicationContext(
            "META-INF/spring/integration/application.xml");) {
            
            
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }
}
