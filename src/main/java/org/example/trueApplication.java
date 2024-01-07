package org.example;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.hibernate.HibernateBundle;
import org.example.core.Task;
import org.example.db.TodoDAO;
import org.example.resources.TodoResource;


public class trueApplication extends Application<trueConfiguration> {

    private final HibernateBundle<trueConfiguration> hibernate = new HibernateBundle<trueConfiguration>(Task.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(trueConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    public static void main(final String[] args) throws Exception {
        new trueApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<trueConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(new MultiPartBundle());
//        bootstrap.addBundle(new MigrationsBundle<trueConfiguration>() {
//            @Override
//            public DataSourceFactory getDataSourceFactory(trueConfiguration configuration) {
//                return configuration.getDatabase();
//            }
//        });
        bootstrap.addBundle(hibernate);

    }

    @Override
    public void run(final trueConfiguration configuration,
                    final Environment environment) {
//        final JdbiFactory jdbiFactory = new JdbiFactory();
//        final Jdbi jdbi = jdbiFactory.build(environment, configuration.getDatabase(), "sql");

//        jdbi.installPlugin(new SqlObjectPlugin());
//        environment.jersey().register(new UserResource(jdbi));
//        environment.lifecycle().manage((Managed) jdbi);

        // Register resources
//        final TodoDAO todoDAO = jdbi.onDemand(TodoDAO.class);

        final TodoDAO todoDAO = new TodoDAO(hibernate.getSessionFactory());
        final TodoResource todoResource = new TodoResource(todoDAO);
        environment.jersey().register(todoResource);
    }

}
