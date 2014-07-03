package com.dropwizard.helloworld;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName(){
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> helloWorldConfigurationBootstrap) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(helloWorldConfiguration.getTemplate(),
                helloWorldConfiguration.getDefaultName());
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(helloWorldConfiguration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
