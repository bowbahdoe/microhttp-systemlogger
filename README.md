## What

This module provides an implementation of [microhttp]()'s `Logger`
interface that delegates to the built-in JDK `System.Logger`.

## Usage

```xml
<dependency>
    <groupId>dev.mccue</groupId>
    <artifactId>microhttp-systemlogger</artifactId>
    <version>0.0.1</version>
</dependency>
```

```java
import org.microhttp.EventLoop;
import org.microhttp.Options;
import org.microhttp.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.lang.System.Logger.Level;

public class Main {
    public static void main(String[] args)
            throws IOException, InterruptedException {
        // These messages are noisy, so you likely want to
        // leave this at the default level of TRACE.
        var logger = new SystemLogger(Level.INFO);

        var options = new Options()
                .withHost("0.0.0.0")
                .withPort(1234);
        var eventLoop = new EventLoop(
                options,
                logger,
                (request, callback) ->
                        callback.accept(new Response(
                                200,
                                "OK",
                                List.of(),
                                "Hello, World".getBytes(StandardCharsets.UTF_8)
                        ))
        );
        eventLoop.start();
        eventLoop.join();
    }
}
```

