package com.project.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.util.Objects;

@Entity
public class PdfFile {
    private @Id @GeneratedValue Long id;
    private String name;
    @JsonIgnore
    private String checksum;
    @Lob
    @JsonIgnore
    private byte[] file;

    public PdfFile() {};

    public PdfFile(String name, String checksum, byte[] file) {
        this.name = name;
        this.checksum = checksum;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PdfFile pdfFile = (PdfFile) o;
        return Objects.equals(name, pdfFile.name) && Objects.equals(checksum, pdfFile.checksum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, checksum);
    }

    @Override
    public String toString() {
        return "PdfFile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
