package com.example.pawpaw.global.util.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;

public record UploadFile(String s3FileName, byte[] fileBytes, ObjectMetadata metadata) {
}
