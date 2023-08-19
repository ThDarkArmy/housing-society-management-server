package com.cdac.org.housingsocietymanagement.dto;

import org.springframework.web.multipart.MultipartFile;

public class BuildingDto {
    private String name;
    private Long numberOfFlats;
    private String address;

    private MultipartFile image;

    public BuildingDto() {
    }

    public BuildingDto(String name, Long numberOfFlats, String address, MultipartFile image) {
        this.name = name;
        this.numberOfFlats = numberOfFlats;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfFlats() {
        return numberOfFlats;
    }

    public void setNumberOfFlats(Long numberOfFlats) {
        this.numberOfFlats = numberOfFlats;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BuildingDto{" +
                "name='" + name + '\'' +
                ", numberOfFlats=" + numberOfFlats +
                ", address='" + address + '\'' +
                ", image=" + image +
                '}';
    }
}
