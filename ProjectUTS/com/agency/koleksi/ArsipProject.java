package com.agency.koleksi;

import java.util.ArrayList;

// Generic: Menggunakan simbol <T> agar fleksibel
public class ArsipProject<T> {
    // Collection: Menggunakan ArrayList untuk menampung banyak data
    private ArrayList<T> daftarProject = new ArrayList<>();

    public void tambahProject(T project) {
        daftarProject.add(project);
    }

    public ArrayList<T> getSemuaProject() {
        return daftarProject;
    }
}