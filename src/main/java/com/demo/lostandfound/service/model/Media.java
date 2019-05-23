package com.demo.lostandfound.service.model;


import javax.persistence.*;

@Entity
public class Media {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name="file_name")
	private String fileName;

	private String fileType;

	@Lob
	private byte [] data;

	public Media() {
	}

	public Media(String fileName) {
		this.fileName = fileName;
	}

	public Media(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public  Long getId(){
		return id;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
