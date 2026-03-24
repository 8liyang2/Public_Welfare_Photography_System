package com.example.pwps.service;

import java.util.List;

public interface ImageCleanupService {
    List<String> findUnusedImages();
    int deleteUnusedImages(List<String> images);
}
