package com.thevoidblock.syncac.config;

public abstract class AutoClickerConfig {

        public int timeElapsed = 0;
        public int syncInterval;

        public abstract boolean isEnabled();
        public abstract int getInterval();
        public abstract boolean isSync();

        public abstract void run();
}
