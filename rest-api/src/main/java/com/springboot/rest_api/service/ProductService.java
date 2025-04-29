package com.springboot.rest_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.rest_api.dto.BarChartDto;
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
	@Autowired
	private BarChartDto barChartDto;
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
		
		
		String uploadPath= "D:\\fsd hex java march 2025\\react-ui\\public\\images";
		
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

	public BarChartDto getBarChartData() {
		Map<String,Integer> map= new HashMap<>();
		//Fetch All Products 
		List<Product> list =  productRepository.findAll();
		//System.out.println(list);
		//fetch all categories from product
		List<Category> listCat =  list.stream().map(p->p.getCategory()).distinct().toList();		 
		for(Category cat : listCat) {
			//count number fo products for each category 
			int num = (int)list.stream().filter(p->p.getCategory().getId() == cat.getId()).count();
			map.put(cat.getName(), num); 
		}
		Set<String> labels = map.keySet();
		Collection<Integer> numData =  map.values();
		
		barChartDto.setLabels(labels);
		barChartDto.setNumData(numData);
		
		return barChartDto; 
		//generate o/p in following format 
		/** 
		 * {
		    "labels": [
		        "printers",
		        "headphones",
		        "mobile",
		        "laptop"
		    ],
		    "numData": [
		        2,
		        1,
		        3,
		        1
		    ]
		}
		 * */
				 
		
		
		
	}

}
