package com.demo.agent.service;

import com.demo.lostandfound.service.LostAndFound;
import com.demo.lostandfound.service.model.Media;
import com.demo.lostandfound.service.model.Person;
import com.demo.lostandfound.service.model.Pet;
import com.demo.lostandfound.service.service.MediaService;
import com.demo.lostandfound.service.service.PetService;
import com.demo.lostandfound.service.utils.mapping.pojos.UIPet;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LostAndFound.class)
public class PetToUIConverterTests{

	public static final Logger LOGGER = LoggerFactory.getLogger(PetToUIConverterTests.class);


	@Autowired
	private DozerBeanMapper mapper;
	@Autowired
	private MediaService mediaService;
	@Autowired
	private PetService petService;



	@Test
	public void shouldReturnCorrectlyMappedPetObjectFromAUIPet() {

		Long testLong = 4L;
		UIPet uiPet = new UIPet();
		LOGGER.info(">>> This is the UIPet before population of the fields {}", uiPet);



		uiPet.setMediaId(testLong);
		uiPet.setImageName("Rex");
		uiPet.setImageType("png/image");
		uiPet.setPetName("Rex Mafitsho");
		uiPet.setFirstName("Kembo");
		uiPet.setSurname("Dube");
		uiPet.setPhoneNumber("0712325313");
		uiPet.setOwnerProvince("Mat South");
		uiPet.setOwnerAddress("Longfield Primary school");
		uiPet.setEntryDate(LocalDateTime.now());
		LOGGER.info(">>> This is the UIPet after population of the fields {}", uiPet);


		Pet pet = mapper.map(uiPet, Pet.class);
		final Media media = mediaService.findById(uiPet.getMediaId()).orElse(new Media());
		LOGGER.info(">>> Looking up media by Id from the mediaRepository{}", media);
		final Person owner = new Person(uiPet.getFirstName(), uiPet.getSurname(), uiPet.getPhoneNumber(),
				uiPet.getOwnerProvince(), uiPet.getOwnerAddress());

		LOGGER.info(">>> setting a media object onto the pet Entity {} ", media);
		pet.setMedia(media);
		pet.setOwner(owner);
		pet.setLocated(0);
		pet.setMissing(1);
		pet.setReunited(0);

		LOGGER.info(">>>>>Attempting to save a pet objects with all the qualified foreign key relationships in place " +
						"{} {}",pet, owner);
		petService.addPet(pet);
		LOGGER.info(">>>>>> Succesfully saved a pet object.");




		assertEquals("Rex Mafitsho", pet.getPetName());
		assertNotNull(owner);
		assertNotNull(media);



	}

	@Test
	public void shouldBeAbleToUpdateMediaIfAlreadyExists(){

		Long testLong = 2L;

		if (mediaService.findById(testLong).isPresent()){


			UIPet uiPet = new UIPet();
			uiPet.setMediaId(testLong);
			uiPet.setImageName("Grey");
			uiPet.setImageType("png/image");
			uiPet.setPetName("Grey Lambalidlile");
			uiPet.setFirstName("MduduziDube");
			uiPet.setSurname("Dube");
			uiPet.setPhoneNumber("0783418198");
			uiPet.setOwnerProvince("Mat South");
			uiPet.setOwnerAddress("Longfield Primary school");
			uiPet.setType("Dog");
			uiPet.setEntryDate(LocalDateTime.now());


			Pet pet = mapper.map(uiPet, Pet.class);
			final Media media = mediaService.findById(uiPet.getMediaId()).orElse(new Media());
			LOGGER.info(">>> Looking up media by Id from the mediaRepository{}", media);
			final Person owner = new Person(uiPet.getFirstName(), uiPet.getSurname(), uiPet.getPhoneNumber(),
					uiPet.getOwnerProvince(), uiPet.getOwnerAddress());

			LOGGER.info(">>> setting a media object onto the pet Entity {} ", media);
			pet.setMedia(media);
			pet.setOwner(owner);

			LOGGER.info(">>>>>Attempting to save a pet objects with all the qualified foreign key relationships in place " +
					"{} {}",pet, owner);
			petService.addPet(pet);
			LOGGER.info(">>>>>> Succesfully saved a pet object.");


		}




	}

}
