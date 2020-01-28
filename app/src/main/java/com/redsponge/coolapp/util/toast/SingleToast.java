package com.redsponge.coolapp.util.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SingleToast {

    private static final int LONG_DELAY = 3500; // 3.5 seconds
    private static final int SHORT_DELAY = 2000; // 2 seconds

    private static final String TAG = "SingleToast";

    private Queue<ToastConfig> pendingToasts;
    private Toast activeToast;
    private ToastConfig activeConfig;
    private boolean isToastActive;

    // like integer hashmap but more efficient. uses ids as keys
    private SparseArray<Runnable> toastEndHandlers;

    public SingleToast() {
        pendingToasts = new LinkedList<>();
        toastEndHandlers = new SparseArray<>();
    }

    public void setOnToastEnd(int id, Runnable onEnd) {
        toastEndHandlers.append(id, onEnd);
    }

    public void addConfig(ToastConfig tc) {
        pendingToasts.add(tc);
        Log.i(TAG, "Added Config " + tc);
        update();
    }

    public void update() {
        if(pendingToasts.isEmpty()) return;
        ToastConfig willBe = pendingToasts.element();
        boolean canGo = true;
        if(isToastActive) {
            Log.i(TAG, "update called when toast is active! checking if " + activeConfig.id + " is " + willBe.id);
            if(willBe.id != activeConfig.id) {
                canGo = false;
            }
        }
        if(!canGo) return;
        pendingToasts.poll();

        if(activeToast != null) activeToast.cancel();

        final boolean hasOldId;
        final int oldId;
        if(activeConfig != null) {
            oldId = activeConfig.id;
            hasOldId = true;
        } else {
            hasOldId = false;
            oldId = -1;
        }

        activeConfig = willBe;
        activeToast = Toast.makeText(activeConfig.ctx, activeConfig.text, activeConfig.dur);
        isToastActive = true;

        int delayTime = activeConfig.dur == Toast.LENGTH_LONG ? LONG_DELAY : SHORT_DELAY;

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            isToastActive = false;
            Log.i(TAG, "update: finished toast!");
            update();
            if(hasOldId) {
                toastEndHandlers.get(oldId).run();
            }
        }, delayTime);
        activeToast.show();
    }

    private final static SingleToast instance = new SingleToast();

    public static SingleToast getInstance() {
        return instance;
    }

    public static class ToastConfig {
        private final Context ctx;
        private final String text;
        private final int dur;
        private final int id;

        private static int defId = 0;
        public ToastConfig(Context ctx, String text, int dur) {
            this(ctx, text, dur, --defId);
        }

        // pass positive ids to make sure there's no override
        public ToastConfig(Context ctx, String text, int dur, int id) {
            this.ctx = ctx;
            this.text = text;
            this.dur = dur;
            this.id = id;
            if (dur != Toast.LENGTH_LONG && dur != Toast.LENGTH_SHORT) throw new AssertionError();
        }

        public String getText() {
            return text;
        }

        public int getDur() {
            return dur;
        }

        @Override
        public String toString() {
            return "Toast[Display \"" + text + "\" for " + (dur == Toast.LENGTH_LONG ? "Long" : "Short") + ". ID:" + id + "]";
        }
    }
}
