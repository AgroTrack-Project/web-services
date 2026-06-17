package org.example.agrotrack.shared.infrastructure.persistence.jpa.configuration.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

public class SnakeCaseWithPluralizeTablePhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return toSnakeCase(toPlural(identifier));
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return toSnakeCase(identifier);
    }

    private Identifier toSnakeCase(final Identifier identifier) {
        if (identifier == null) return null;
        final String newName = identifier.getText()
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
        return Identifier.toIdentifier(newName);
    }

    private Identifier toPlural(final Identifier identifier) {
        return Identifier.toIdentifier(pluralize(identifier.getText()));
    }
}
