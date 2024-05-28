package com.thevoidblock.syncac.config;

public abstract class AutoClickerConfig {

        public long startTime = 0;
        public long syncInterval;

        public abstract boolean isEnabled();
        public abstract int getInterval();
        public abstract boolean isSync();

        public abstract void run();
}
