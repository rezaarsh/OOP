package com.agency.utama;

// Import package agar bisa digunakan
import com.agency.entitas.ProjectKatalog;
import com.agency.entitas.ProjectWeb;
import com.agency.koleksi.ArsipProject;

public class MainAgency {
    public static void main(String[] args) {
        // Object: Membuat wujud nyata dari cetakan class
        ProjectKatalog projectSatu = new ProjectKatalog("Nanadd", 2500000, "Claynaa Lab");

        // Menggunakan Generic & Collection untuk menyimpan data
        ArsipProject<ProjectWeb> arsipKita = new ArsipProject<>();
        arsipKita.tambahProject(projectSatu);

        System.out.println("=== ARSIP PROJECT WEB AGENCY ===");
        
        // Looping data collection
        for (ProjectWeb p : arsipKita.getSemuaProject()) {
            p.tampilkanDetail(); // Memanggil Polimorfisme
            p.cetakInvoice(); // Memanggil Interface
        }
    }
}