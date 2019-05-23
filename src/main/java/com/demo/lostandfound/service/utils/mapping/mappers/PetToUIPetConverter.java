package com.demo.lostandfound.service.utils.mapping.mappers;


import com.demo.lostandfound.service.model.Media;
import com.demo.lostandfound.service.model.Person;
import com.demo.lostandfound.service.model.Pet;
import com.demo.lostandfound.service.service.MediaService;
import com.demo.lostandfound.service.service.PetService;
import com.demo.lostandfound.service.utils.mapping.pojos.UIPet;
import org.dozer.CustomConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PetToUIPetConverter implements CustomConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetToUIPetConverter.class);

    @Autowired
    private  MediaService mediaService;
    @Autowired
    private PetService petService;


    @Override
    public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
        LOGGER.info(">>> see if conveter gets called {}", source);
        if (source.getClass().equals(UIPet.class))
            return convertUIPetToPet(destination, source);
        if (!source.getClass().equals(Pet.class))
            throw new IllegalArgumentException("Pet Conversion error occurred.");
        return convertPetToUIPet(destination, source);
    }

    private Object convertUIPetToPet(Object destination, Object source){
        final UIPet uiPet = (UIPet) source;

        LOGGER.debug(">>> pet before mapping {}", uiPet);

        final Pet pet = getPet(uiPet);
        LOGGER.info(">>>> pet from UIPet {}", pet);
//        Media media = mediaService.findById(uiPet.getMediaId()).orElse(new Media());
//        LOGGER.info(">>>> media from Media {}", media);

        Person owner = new Person(uiPet.getFirstName(), uiPet.getSurname(), uiPet.getPhoneNumber(),
		        uiPet.getOwnerProvince(), uiPet.getOwnerAddress());
        LOGGER.info(">>>> owner from Person {}", owner);
//        pet.setMedia(media);
        pet.setEntryDate(uiPet.getEntryDate());
        pet.setOwner(owner);
        pet.setPetName(uiPet.getPetName());
        pet.setType(uiPet.getType());
        LOGGER.debug("<<< pet after mapping {}", pet);
        return pet;
    }

    private Object convertPetToUIPet(Object destination, Object source){
        UIPet uiPet = destination == null ? new UIPet() : (UIPet) destination;
        final Pet pet = (Pet) source;
        LOGGER.debug(">>> pet before mapping {}", pet);

       uiPet.setId(pet.getId());
       uiPet.setPetName(pet.getPetName());
       uiPet.setType(pet.getType());
       uiPet.setEntryDate(pet.getEntryDate());
       uiPet.setEntryDate(pet.getEntryDate());
       uiPet.setFirstName(pet.getOwner().getFirstName());
       uiPet.setSurname(pet.getOwner().getSurname());
       uiPet.setOwnerAddress(pet.getOwner().getAddress());
       uiPet.setOwnerProvince(pet.getOwner().getProvince());
       uiPet.setPhoneNumber(pet.getOwner().getPhoneNumber());
       uiPet.setImageName(pet.getMedia().getFileName());
       uiPet.setImageType(pet.getMedia().getFileType());
       uiPet.setImageData(pet.getMedia().getData());
       uiPet.setMediaId(pet.getMedia().getId());



	    LOGGER.debug("<<< ui-pet after mapping {}", uiPet);
        return uiPet;
    }

    private Pet getPet(UIPet uiPet){
        if(uiPet.getId() == null){
            return new Pet();
        }

        return petService.findByPetIdAndMediaId(uiPet.getId(), uiPet.getMediaId());
    }
}
