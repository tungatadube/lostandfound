package com.demo.lostandfound.service.utils.mapping.pojos;



import java.io.Serializable;
import java.time.LocalDateTime;

public class UIDocument implements Serializable {

    private static final long serialVersionUID = -7238569543870437197L;

    private Long documentId;
    private String imageName;
    private String imageType;
    private byte [] imageData;
    private String documentName;
    private String documentType;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private String ownerProvince;
    private String ownerAddress;
    private LocalDateTime entryDate;


    public UIDocument() {
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getPetId() {
        return documentId;
    }

    public void setPetId(Long documentId) {
        this.documentId = documentId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }



    public String getPetName() {
        return documentName;
    }

    public void setPetName(String documentName) {
        this.documentName = documentName;
    }

    public String getPetType() {
        return documentType;
    }

    public void setPetType(String documentType) {
        this.documentType = documentType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwnerProvince() {
        return ownerProvince;
    }

    public void setOwnerProvince(String ownerProvince) {
        this.ownerProvince = ownerProvince;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }
}
