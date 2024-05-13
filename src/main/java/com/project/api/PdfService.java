package com.project.api;

import com.project.api.exceptions.DuplicatesException;
import com.project.api.exceptions.PdfNotFoundException;
import com.project.api.exceptions.WrongTypeException;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PdfService {
    final PdfRepository repository;

    public PdfService(PdfRepository repository) {
        this.repository = repository;
    }

    public List<PdfFile> findAll() {
        return repository.findAll();
    }

    public PdfFile uploadPdf(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        if (file == null || file.isEmpty()) {
            throw new PdfNotFoundException();
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (!checkFileType(file)) {
            System.out.println("This is not a Pdf file, this api only supports files with type application/pdf");
            throw new WrongTypeException(fileName);
        }

        byte[] fileContent = file.getBytes();
        String checksum = getChecksum(fileContent);

        PdfFile pdfToUpload = new PdfFile(fileName, checksum, fileContent);

        List<PdfFile> allPdfs = repository.findAll();

        for (PdfFile pdf : allPdfs) {
            if (pdfToUpload.equals(pdf)) {
                System.out.println("This pdf is already uploaded");
                throw new DuplicatesException(fileName);
            }
        }

        return repository.save(pdfToUpload);
    }

    private String getChecksum(byte[] fileContent) throws NoSuchAlgorithmException {
        byte[] hash = MessageDigest.getInstance("SHA-256").digest(fileContent);
        String checksum = new BigInteger(1, hash).toString(16);
        return checksum;
    }
    private boolean checkFileType(MultipartFile file) throws IOException {
        Tika tika = new Tika();
        String detectedType = tika.detect(file.getBytes());

        if (detectedType.equals("application/pdf")) {
            return true;
        }
        return false;
    }

    public void downloadPdf(String fileName, String filePath) {
        PdfFile pdf = repository.findByName(fileName);

        if (pdf == null) {
            throw new PdfNotFoundException(fileName);
        }

        byte[] content = pdf.getFile();

        String absolutePathOfFile = filePath + fileName;
        File file = new File(absolutePathOfFile);

        try {
            OutputStream os = new FileOutputStream(file);
            os.write(content);
            os.close();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
