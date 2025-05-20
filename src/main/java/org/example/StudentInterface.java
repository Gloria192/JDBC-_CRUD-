package org.example;

import java.util.List;


import java.util.List;

public interface StudentInterface<T> {
    List<T> findAll();
    void create(T students);
    T read(int id); // Corrected method to read by ID
    void update(T students);
    void delete(int id); // Changed delete to use ID
}
