package com.example.gifsearcher.models;

import java.util.ArrayList;

/**
 * Created by Andrei on 21.06.2018.
 */

public interface SimpleCallback<T> {
    void process(T response);
}
