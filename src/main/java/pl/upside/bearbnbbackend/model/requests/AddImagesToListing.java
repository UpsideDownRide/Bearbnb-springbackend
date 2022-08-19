package pl.upside.bearbnbbackend.model.requests;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record AddImagesToListing(List<MultipartFile> files) {};
