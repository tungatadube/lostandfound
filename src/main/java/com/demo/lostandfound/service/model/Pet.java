package com.demo.lostandfound.service.model;



import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Pet {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name="pet_name")
	private String petName;

	private String type;

	private int missing;
	private int reunited;
	private int located;

	@Column(name="entry_date")
	@CreationTimestamp
	private LocalDateTime entryDate;

	@Lob
	@Basic(fetch=FetchType.LAZY)
	@OneToOne(mappedBy="id", optional=false, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Media media;

	@ManyToOne( optional=false, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Person owner;



	public Pet() {

	}

	public int getMissing() {
		return missing;
	}

	public void setMissing(int missing) {
		this.missing = missing;
	}

	public int getReunited() {
		return reunited;
	}

	public void setReunited(int reunited) {
		this.reunited = reunited;
	}

	public int isLocated() {
		return located;
	}

	public void setLocated(int located) {
		this.located = located;
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
}
