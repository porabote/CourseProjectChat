package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ServerTest {


    @Test
    public void testServerSocketGetsCreated() throws IOException {
        assertNotNull(Server.getInstance().createServerSocket());
    }
}
