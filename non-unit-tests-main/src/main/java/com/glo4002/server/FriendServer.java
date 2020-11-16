package com.glo4002.server;

import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import com.glo4002.ui.CatchInvalidRequestMapper;

public class FriendServer extends Server {

    public FriendServer(int port, List<Object> resources) {
        super(port);

        ServletContextHandler contextHandler = new ServletContextHandler(this, "/");
        ResourceConfig packageConfig = createResourceConfig(resources);
        ServletContainer container = new ServletContainer(packageConfig);
        ServletHolder servletHolder = new ServletHolder(container);

        contextHandler.addServlet(servletHolder, "/*");
    }

    private ResourceConfig createResourceConfig(List<Object> resources) {
        ResourceConfig packageConfig = new ResourceConfig();
        resources.forEach(packageConfig::register);
        packageConfig.register(new CatchInvalidRequestMapper());
        packageConfig.property(ServerProperties.LOCATION_HEADER_RELATIVE_URI_RESOLUTION_DISABLED, true);
        return packageConfig;
    }
}
