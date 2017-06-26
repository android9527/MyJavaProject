package com.test;

public class RefTestObj {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + "[id=" + this.id + "]";
    }

    @Override
    protected void finalize() {
        try {
            super.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}