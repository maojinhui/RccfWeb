package com.rccf.util.table;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerationStrategy;

import java.util.UUID;

public class Uuid32Generator implements UUIDGenerationStrategy {
    @Override
    public int getGeneratedVersion() {
        return 1;
    }

    @Override
    public UUID generateUUID(SessionImplementor session) {
        UUID uuid = UUID.randomUUID();

        return null;
    }
}
