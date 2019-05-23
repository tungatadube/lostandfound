package com.demo.lostandfound.service.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Document implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name="document_type")
	private String documentType;


	@Column(name="entry_date")
	@CreationTimestamp
	private LocalDateTime entryDate;

	@Lob
	@Basic(fetch=FetchType.LAZY)
	@OneToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id")
	private Media documentImage;

	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	private Person owner;



	public Document() {

	}

	public Long getId(){
		return id;
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


	public Media getDocumentImage() {
		return documentImage;
	}

	public void setDocumentImage(Media documentImage) {
		this.documentImage = documentImage;
	}



	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
}
