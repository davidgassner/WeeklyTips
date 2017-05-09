package com.example.android.weeklytips;

class StateManager {
    private static final StateManager ourInstance = new StateManager();

    private String name;

    static StateManager getInstance() {
        return ourInstance;
    }

    private StateManager() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
