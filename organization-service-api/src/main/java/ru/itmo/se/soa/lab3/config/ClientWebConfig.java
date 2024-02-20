package ru.itmo.se.soa.lab3.config;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.itmo.se.soa.lab3.service.OrganizationService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Collections;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"ru.itmo.se.soa.lab3.controller", "ru.itmo.se.soa.lab3.service"})
public class ClientWebConfig implements WebMvcConfigurer {
    private static final String DEFAULT_SERVER_HOST = "http://localhost:8080";
    @PostConstruct
    private void init() {
        ConsulClient client = new ConsulClient("localhost");

        NewService newService = new NewService();
        newService.setId("organization_01");
        newService.setTags(Collections.singletonList("EU-East"));
        newService.setName("organization-service");
        newService.setPort(8080);

        client.agentServiceRegister(newService);
    }

    private String getProviderURl() {
        final String serverHost = System.getProperty("server.host");
        return "remote+" + (serverHost != null ? serverHost : DEFAULT_SERVER_HOST);
    }

    private Properties jndiProperties() {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, getProviderURl());
        return jndiProperties;
    }

    @Bean
    @Lazy
    public OrganizationService organizationService() {
        try {
            final Context context = new InitialContext(jndiProperties());
            final String jndiTemplate = "ejb:/organization-service-ejb-jar-with-dependencies/OrganizationServiceEJB!ru.itmo.se.soa.lab3.service.OrganizationService";
            return (OrganizationService) context.lookup(jndiTemplate);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
