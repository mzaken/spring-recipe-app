package guru.springframework.services.jpa;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.services.ImageService;

@Service
public class ImageServiceJPA implements ImageService {

	@Override
	public void saveImage(Long recipeId, MultipartFile file) {
		// TODO Auto-generated method stub
		
	}

}
