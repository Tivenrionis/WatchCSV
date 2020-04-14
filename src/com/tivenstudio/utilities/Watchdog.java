package com.tivenstudio.utilities;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michał Wesołowski
 * @version 1.0
 * Date: 14.04.2020
 * Time: 11:36
 * Class name: Watchdog
 * Description:
 */
public class Watchdog {
    private final WatchService watchService;
    private final Map<WatchKey, Path> keyMap;

    public Watchdog(Path directory) throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.keyMap = new HashMap<>();

    }
}
