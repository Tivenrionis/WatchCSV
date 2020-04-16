package com.tivenstudio.utilities;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michał Wesołowski
 * @version 1.0 <br>
 * Date: 14.04.2020 <br>
 * Time: 11:36 <br>
 * Class name: Watchdog<br>
 * Description: This class is used to initialize Java WatchService,
 * register given directory.
 */
public class Watchdog {
    private final WatchService watchService;
    private final Map<WatchKey, Path> keyMap;

    /**
     * Constructor of a class, it is taking directory path as a parameter,
     * after that, it is initializing WatchService, the Map consisting of
     * WatchKey and Path object, and registering given directory.
     *
     * @param directory - Path object containing path to the directory to register
     * @throws IOException - If and I/O occurs during registering or initializing WatchService
     */
    public Watchdog(Path directory) throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.keyMap = new HashMap<>();
        registerDirectory(directory);

    }

    /**
     * This method registers given directory path as a WatchKey object,
     * with ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE events.
     * Then it puts WatchKey object as a key and given directory path
     * as a value to the (WatchKey,Path) map.
     *
     * @param dir - Path object containing path to the directory to register
     * @throws IOException - If I/O occurs during registering
     */
    public void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(this.watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
        keyMap.put(key, dir);
    }

    /**
     * Returns initialized WatchService object
     * of current Watchdog class instance.
     *
     * @return Initialized WatchService object.
     */
    public WatchService getWatchService() {
        return watchService;
    }

    /**
     * Returns (WatchKey,Path) map
     * of current Watchdog class instance.
     *
     * @return Initialized object of Map(WatchKey,Path)
     */
    public Map<WatchKey, Path> getKeyMap() {
        return keyMap;
    }
}
