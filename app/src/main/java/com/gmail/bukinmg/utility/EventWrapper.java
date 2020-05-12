package com.gmail.bukinmg.utility;

public class EventWrapper<T> {

    private T myContent;
    private boolean hasBeenHandled = false;

    public EventWrapper(T content) {
        if (content == null) {
            throw new IllegalArgumentException();
        }
        myContent = content;
    }

    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return myContent;
        }
    }
    }
