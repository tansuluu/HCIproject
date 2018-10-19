package com.example.HCI.service;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    public static final Path rootApp = Paths.get("upload-dir");
    public static final Path avatar = Paths.get("up-avatar");

    public void store(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.rootApp.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL! Such file already exist");
        }
    }
    public void init() {
        try {
            Files.createDirectory(rootApp);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
    public void saveAvatar(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.avatar.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL! Such file already exist");
        }
    }
    public Resource loadAvatar(String filename) {
        try {
            Path file = avatar.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                throw new RuntimeException("cannot load such avatar!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }
    public Resource loadFile(String filename) {
        try {
            Path file = rootApp.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                throw new RuntimeException("No such file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }
}
