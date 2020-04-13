package org.acme;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@RequestScoped
@Path("/hello")
public class HelloResource {

    @Inject
    HelloService service;

    @GET
    @Counted(name = "helloChecks", description = "How many hellos.")
    @Timed(name = "helloTimer", description = "Time measurements about the /hello endpoint.", unit = MetricUnits.MILLISECONDS)
    @Produces(MediaType.APPLICATION_JSON)
    public Message hello() {
        return this.service.message();
    }
}