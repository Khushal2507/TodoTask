package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public class trueConfiguration extends Configuration {
    @NotNull
    @Valid
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")

    public DataSourceFactory getDatabase() {
        return database;
    }
    // TODO: implement service configuration
}
