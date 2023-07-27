import org.jspecify.annotations.NullMarked;

/**
 * Defines one class, {@link dev.mccue.microhttp.systemlogger.SystemLogger},
 * which implements {@link org.microhttp.Logger} and delegates to a
 * {@link java.lang.System.Logger}
 */
@NullMarked
module dev.mccue.microhttp.systemlogger {
    requires transitive org.microhttp;
    requires static org.jspecify;

    exports dev.mccue.microhttp.systemlogger;
}