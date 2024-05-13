package com.project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class PdfController {
    private final PdfService service;

    public PdfController(PdfService service) {
        this.service = service;
    }

    @GetMapping("/pdfs")
    public ResponseEntity<List<PdfFile>> all() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<PdfFile> uploadPdf(@RequestParam("file") MultipartFile file) throws IOException, NoSuchAlgorithmException {
        PdfFile pdf = service.uploadPdf(file);
        return new ResponseEntity<>(pdf, HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<PdfFile> downloadPdf(@RequestParam("fileName") String fileName, @RequestParam("filePath") String filePath) {
        service.downloadPdf(fileName, filePath);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
