package ru.itmo.employee.service;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/")
@ApplicationScoped
public class EmployeeserviceRestApplication extends Application {
}
