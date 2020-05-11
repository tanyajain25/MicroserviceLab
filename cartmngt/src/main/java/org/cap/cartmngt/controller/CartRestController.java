package org.cap.cartmngt.controller;


import org.cap.cartmngt.dto.CartDto;
import org.cap.cartmngt.dto.CartItemDetailsDto;
import org.cap.cartmngt.dto.ProductDto;
import org.cap.cartmngt.entities.Cart;
import org.cap.cartmngt.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cartitems")
@Validated // required for validating path variables
public class CartRestController {

   // private static final Logger Log = LoggerFactory.getLogger(CartRestController.class);

    @Autowired
    private CartServiceImpl service;
    
    @Value("${productservice.baseurl}")
    private String productServiceBasicUrl;
   
    public String getProductServiceBasicUrl() {
		return productServiceBasicUrl;
	}

	public void setProductServiceBasicUrl(String productServiceBasicUrl) {
		this.productServiceBasicUrl = productServiceBasicUrl;
	}
	
	@Autowired
	private RestTemplate restTemplate;
	

	@PostMapping("/add")
    public ResponseEntity<Cart> addItems(@RequestBody CartDto dto) {
        Cart item = convertFromDto(dto);
        item= service.toSaveItems(item);
        ResponseEntity<Cart> response = new ResponseEntity<>(item, HttpStatus.OK);
        return response;
    }

    public Cart convertFromDto(CartDto dto) {
    	Cart item = new Cart();
    	item.setUserId(dto.getUserId());
    	item.setProductId(dto.getProductId());
        return item;
    }
    
    @GetMapping("/userdetails/{userid}")
    public ResponseEntity<List<CartItemDetailsDto>> findItemsById(@PathVariable("userid")  int userid) {
    	List<Cart> cartitems = service.fetchCartItems(userid);
    	List<CartItemDetailsDto> desiredItems= new ArrayList<CartItemDetailsDto>();
    	for(Cart items: cartitems )
    	{
    		ProductDto productDto =fetchProductsById(items.getProductId());
    		CartItemDetailsDto dto = cartItemsDetailsDto(items,productDto);
    		desiredItems.add(dto);
    	}
        ResponseEntity<List<CartItemDetailsDto>> response = new ResponseEntity<>(desiredItems, HttpStatus.OK);
        return response;
    }

    public CartItemDetailsDto cartItemsDetailsDto(Cart item, ProductDto dto) {
    	CartItemDetailsDto cartItemsDetailsDto= new CartItemDetailsDto();
    	cartItemsDetailsDto.setProductName(dto.getProductName());
    	cartItemsDetailsDto.setPrice(dto.getPrice());
    	return cartItemsDetailsDto;
    }
    
    public ProductDto fetchProductsById(int id){
    
    	String url=productServiceBasicUrl+"/find/"+id;
    	ProductDto productDto= restTemplate.getForObject(url, ProductDto.class);
    	return productDto;
    }
    
    

  

//    @GetMapping
//    public ResponseEntity<List<Employee>> fetchAll() {
//        List<Employee> employees = service.fetchAll();
//        ResponseEntity<List<Employee>> response = new ResponseEntity<>(employees, HttpStatus.OK);
//        return response;
//    }

//    @ExceptionHandler(EmployeeNotFoundException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException ex) {
//        Log.error("employee not found exception", ex);
//        String msg = ex.getMessage();
//        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
//        return response;
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
//        Log.error("constraint violation", ex);
//        String msg = ex.getMessage();
//        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
//        return response;
//    }
//
//    @ExceptionHandler(Throwable.class)
//    public ResponseEntity<String> handleAll(Throwable ex) {
//        Log.error("exception caught", ex);
//        String msg = ex.getMessage();
//        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
//        return response;
//    }


}
