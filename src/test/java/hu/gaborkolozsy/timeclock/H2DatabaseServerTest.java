/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock;

import org.h2.tools.Server;
import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * The test for the {@link hu.gaborkolozsy.timeclock.H2DatabaseServer} class'
 * private methods.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see Server
 * @see After
 * @see AfterClass
 * @see org.junit.Assert
 * @see Before
 * @see Test
 * @see RunWith
 * @see SpringJUnit4ClassRunner
 * @see ReflectionTestUtils
 */
public class H2DatabaseServerTest {
    
    private final H2DatabaseServer h2DbServer;
    private static Server tcpServer;
    private static Server webServer;
    
    public H2DatabaseServerTest() {
        this.h2DbServer = new H2DatabaseServer();
    }
    
    /**
     * If all test done.
     */
    @AfterClass
    public static void afterClass() {
        tcpServer.shutdown();
        webServer.shutdown();
    }

    /**
     * Set up before test.
     */
    @Before
    public void setUp() {
        ReflectionTestUtils.invokeMethod(h2DbServer, "start");
        tcpServer = (Server) ReflectionTestUtils.getField(h2DbServer, "tcpServer");
        webServer = (Server) ReflectionTestUtils.getField(h2DbServer, "webServer");
    }

    /**
     * After test.
     */
    @After
    public void tearDown() {
        tcpServer.stop();
        webServer.stop();
    }

    @Test
    public void testStart() {
        assertTrue(tcpServer.isRunning(false));
        assertTrue(tcpServer.getStatus().startsWith("TCP server running at tcp://"));
        assertTrue(webServer.getStatus().startsWith("Web Console server running at http://"));
    }
    
    @Test
    public void testStop() {
        assertTrue(tcpServer.isRunning(false));
        ReflectionTestUtils.invokeMethod(h2DbServer, "stop");
        assertFalse(tcpServer.isRunning(false));
        assertTrue(tcpServer.getStatus().equals("Not started"));
        assertTrue(webServer.getStatus().equals("Not started"));
    }

}