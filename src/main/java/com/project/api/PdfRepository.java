package com.project.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfRepository extends JpaRepository<PdfFile, Long> {
    PdfFile findByName(String fileName);
}
