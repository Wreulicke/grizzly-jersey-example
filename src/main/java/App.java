import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.wreulicke.jaxrs.DiscoveryFeature;


public class App {

    private static final URI BASE_URI = URI.create("http://localhost:8080/");

    public static void main(String[] args) throws IOException {
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, create());
        server.getServerConfiguration().addHttpHandler(new StaticHttpHandler(),"/assets");
        
        System.in.read();
        server.shutdownNow();
    }

    public static ResourceConfig create() throws IOException {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(JacksonFeature.class);
        resourceConfig.register(DiscoveryFeature.class);
        resourceConfig.packages("com.wreulicke.jaxrs");
        return resourceConfig;
    }

}