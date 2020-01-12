package com.glinsoft.guardedsuspension;

import java.util.concurrent.Callable;

/**
 * @author 高广林
 * @version 1.0
 * @date 2020/1/12 16:09
 * @since JDK1.8
 **/
public abstract class GuardedAction<V> implements Callable<V> {


    protected final Predicate guard;

    public GuardedAction(Predicate guard){
        this.guard = guard;
    }

}