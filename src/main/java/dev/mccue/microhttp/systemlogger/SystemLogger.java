package dev.mccue.microhttp.systemlogger;

import org.microhttp.LogEntry;
import org.microhttp.Logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of {@link Logger} that delegates to a {@link System.Logger}.
 *
 * <p>
 *     Defaults to log all messages at {@link System.Logger.Level#TRACE}.
 * </p>
 */
public final class SystemLogger implements Logger {
    private final System.Logger logger;
    private final System.Logger.Level level;

    /**
     * Creates a {@link SystemLogger} that logs at {@link System.Logger.Level#TRACE}.
     * and with a {@link System.Logger} that has the name of this class.
     */
    public SystemLogger() {
        this(System.Logger.Level.TRACE);
    }

    /**
     * Creates a {@link SystemLogger} that logs at {@link System.Logger.Level#TRACE}.
     * and delegates to the provided {@link System.Logger}.
     */
    public SystemLogger(System.Logger logger) {
        this(logger, System.Logger.Level.TRACE);
    }

    /**
     * Creates a {@link SystemLogger} that logs at the provided {@link System.Logger.Level}
     * and with a {@link System.Logger} that has the name of this class.
     * @param level The level to log at.
     */
    public SystemLogger(System.Logger.Level level) {
        this(System.getLogger(SystemLogger.class.toString()), level);
    }

    /**
     * Creates a {@link SystemLogger} that logs at the provided {@link System.Logger.Level}
     * and delegates to the provided {@link System.Logger}.
     * @param logger The logger to delegate to.
     * @param level The level to log at.
     */
    public SystemLogger(System.Logger logger, System.Logger.Level level) {
        this.logger = Objects.requireNonNull(logger);
        this.level = Objects.requireNonNull(level);
    }

    @Override
    public boolean enabled() {
        return true;
    }

    @Override
    public void log(LogEntry... entries) {
        this.logger.log(
                this.level,
                () -> Arrays.stream(entries)
                        .map(entry -> entry.key() + "=" + entry.value())
                        .collect(Collectors.joining(", "))
        );
    }

    @Override
    public void log(Exception e, LogEntry... entries) {
        this.logger.log(
                this.level,
                () -> Arrays.stream(entries)
                        .map(entry -> entry.key() + "=" + entry.value())
                        .collect(Collectors.joining(", ")),
                e
        );
    }
}
