package com.company.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<T> {

    public void add(T object) {
        try {
            File file = new File(getFileName());
            FileOutputStream f;
            ObjectOutputStream o;

            if (!file.exists()) {
                f = new FileOutputStream(getFileName());
                o = new ObjectOutputStream(f);
            } else {
                f = new FileOutputStream(getFileName(), true);
                o = new ObjectOutputStream(f) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }

            o.writeObject(object);

            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public List<T> findAll() {
        List<T> objects = new ArrayList<>();

        try {
            try (FileInputStream fi = new FileInputStream(getFileName()); ObjectInputStream oi = new ObjectInputStream(fi)) {
                while (true) {
                    objects.add((T) oi.readObject());
                }
            } catch (EOFException ignored) {
                // as expected
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }

    protected abstract String getFileName();
}
