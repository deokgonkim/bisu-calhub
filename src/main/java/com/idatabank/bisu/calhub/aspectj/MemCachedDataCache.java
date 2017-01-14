package com.idatabank.bisu.calhub.aspectj;

import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;

import com.whalin.MemCached.MemCachedClient;

public class MemCachedDataCache {
    private static final Logger LOG = Logger.getLogger("calhub");

    private MemCachedClient mcc;

    public void setMemCachedClient(MemCachedClient mcc) {
        this.mcc = mcc;
    }

    public Object methodCache(ProceedingJoinPoint joinPoint) throws Throwable {
        //printStats();
        StringBuffer key = new StringBuffer();
        key.append(joinPoint.getTarget().getClass().getName());
        key.append("$").append(joinPoint.toShortString());
        key.append("[");
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object arg : args) {
                if (arg != null)
                    key.append(arg);
            }
        }
        key.append("]");
        LOG.warning(key.toString());

        Object o = null;
        if (mcc.keyExists(key.toString())) {
            Date s = new Date();
            o = mcc.get(key.toString());
            Date e = new Date();
            long ret = e.getTime() - s.getTime();
            LOG.warning("cache hit [" + ret + "ms]");
        }
        else {
            Date s = new Date();
            o = joinPoint.proceed();
            if (o != null) {
                if ( !mcc.add(key.toString(), o, new Date(System.currentTimeMillis() + 1800000L)) ) {
                    LOG.severe("data set error");
                }
            }
            Date e = new Date();
            long ret = e.getTime() - s.getTime();
            LOG.warning("method execution [" + ret + "ms]");
        }
        return o;
    }

    @SuppressWarnings("unchecked")
    public void printStats() {
        Map stats = mcc.stats();
        System.out.println("[STATS]");
        System.out.println(stats);
        System.out.println();
    }
}