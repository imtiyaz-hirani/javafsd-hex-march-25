package com.springboot.rest_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Category;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.repository.CategoryRepository;
import com.springboot.rest_api.repository.productRepository;

@Service
public class ProductService {

	@Autowired
	private productRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	public Product add(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getProductByCategory(int catId, Pageable pageable) throws InvalidIDException {
		Optional<Category> optional = categoryRepository.findById(catId);
		if(optional.isEmpty())
			throw new InvalidIDException("Category ID Invalid.."); 
		 
		return productRepository.findByCategoryId(catId,pageable);
	}

	public Product getById(int pid) throws InvalidIDException {
		Optional<Product> optional = productRepository.findById(pid);
		if(optional.isEmpty())
			throw new InvalidIDException("Category ID Invalid.."); 
		return optional.get();
	}

	public Product uploadImage(MultipartFile file,int pid) throws IOException, InvalidIDException {
		/*check if pid isvalid */
		Product product = productRepository.findById(pid)
				.orElseThrow(()->new InvalidIDException("Invalid PID given.."));
		
		List<String> allowedExtensions = Arrays.asList("png","jpg","jpeg","gif","svg"); 
		String originalFileName = file.getOriginalFilename(); 
		System.out.println(originalFileName);
		String extension= originalFileName.split("\\.")[1];
		/*Check weather extension is allowed or not */
		if( !(allowedExtensions.contains(extension))) {
			throw new RuntimeException("Image Type Invalid");
		}
		
		
		String uploadPath= "D:\\fsd hex java march 2025\\rest-api\\uploads";
		
		/*Create directory *///Check if directory is present else create it
		Files.createDirectories(Paths.get(uploadPath));
		/*Define full path with folder and image name */
		Path path = Paths.get(uploadPath + "\\" +originalFileName); 
		/*Copy the image into uploads path */
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		/*Save this path in Db */
		product.setImageUrl(path.toString());
		return productRepository.save(product);
	}

}
