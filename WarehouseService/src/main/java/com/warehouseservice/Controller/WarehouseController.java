package com.warehouseservice.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.warehouseservice.Entity.AuthenticationRequest;
import com.warehouseservice.Entity.AuthenticationResponse;
import com.warehouseservice.Entity.Pallet;
import com.warehouseservice.Entity.Slot;
import com.warehouseservice.Service.JwtUtil;
import com.warehouseservice.Service.MyUserDetailsService;
import com.warehouseservice.repositories.PalletsRepo;
import com.warehouseservice.repositories.SlotsRepo;

@RestController
@RequestMapping("/api")
public class WarehouseController {
	
	@Autowired
	private PalletsRepo prepo;//link access to the pallets repository 
	
	@Autowired
	private SlotsRepo srepo;//link access to the slots repository
	
	
		///  Enabling JWT authorization //////
		@Autowired
		private AuthenticationManager authenticationManager;//link access to the authentication manager 
		
		@Autowired
		private MyUserDetailsService userDetailsService;//link access to the user details service
		
		@Autowired
		private JwtUtil jwtTokenUtil;//link to access a jwt generator just once initially 
	
		@Autowired
		private WebClient webClient;
		///// depricated template for communication between  micro-services//////
//			//@Autowired
//			//private RestTemplate restTemplate;
//			
//	
			private final Logger logger = LoggerFactory.getLogger(WarehouseController.class);
	
	////////////////////////////////////////////////////////////
	
	@GetMapping(path = "/test")
	public ResponseEntity<String> test() 
	{
		return new ResponseEntity<>("Hi Hello",HttpStatus.OK);
	}
	

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception 
	{
		//if the authentication fails we will throw an exception and error message but if completed we need to return JWT
		try//try to authenticate 
		{
		 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {throw new Exception("Incorrect username or password", e);}
		//first lets create it with the dedicated functions to fetch the user details first:
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		//now that we have the details we can send to create the jwt according to these details
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		//now that we have the newly generated token we just want to pass it back as a response to the user
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
	
			/****   authorization allocations	***/

				/****get path for an ADMIN user****/
				@GetMapping("/admin")
				public String admin() 
				{
					return ("<h1>Welcome Admin</h1>");
					//gets back also as 200 OK
				}
				
				/****get path for a standard USER****/
				@GetMapping(path = "/user")
				public String user() 
				{
					return ("<h1>Welcome User</h1>");
					//gets back also as 200 OK
				}
	
	/**********************************************************************************************************************************/
	/****get a pallet list of all WH stored pallets with this batch number****/
	@GetMapping(path = "/batch/{batch}")
	public List<Pallet> getByBatch(@PathVariable Integer batch) 
	{
		List<Pallet> listPallets = prepo.findByBatch(batch);
		return listPallets;
	}
	
	
	/****get a all  slots available in the WH for storage ****/
	@GetMapping(path = "/allslots")
	public List<Slot> getAllSlots() 
	{
		List<Slot> listSlots = srepo.findAll();
		return listSlots;
	}
	
	/****get a all stored pallets  in the WH , with a given height limit ****/
	@GetMapping(path = "/allpallets/{height}")
	public List<Pallet> getAllPallets(@PathVariable Double height) 
	{
		List<Pallet> listPallets = prepo.findByHightLessThanEqual(height);
		return listPallets;
	}
	
	/****get a specific slot from the slots DB according to a given id ****/
	@GetMapping(path = "/slotid/{id}")
	@Cacheable(key = "#id", value = "Slot")
	public Slot getById(@PathVariable Integer id) 
	{
		Slot slt = srepo.findSlot(id);
		logger.debug(">>WareouseController USERID CALL");
		return slt;
	}
	
	/****get a all empty slots available in the WH for storage, with a given height limit ****/
	@GetMapping(path = "/size/{height}")
	public List<Slot> getByHight(@PathVariable Double height) 
	{
		List<Slot> listSlots = srepo.findByHightLessThanEqual(height,false);
		logger.debug(">>WareouseController LIST OF SLOTS WERE CALLED");
		return listSlots;
	}
	
	/****insert a pallet to storage WH as registered pallet to be stored in pallets db****/
	@PostMapping(path = "/add") 
	public Pallet addPallet(@RequestBody Pallet newPallet) 
	{
		Pallet plt = prepo.save(newPallet);
		return plt;
	}
	
	/****register a new slot to slots db list ****/
	@PostMapping(path = "/addslot") 
	public Slot addSlot(@RequestBody Slot newSlot) 
	{
		Slot slt = srepo.save(newSlot);
		return slt;
	}
	
	/****removes a registered-stored WH Pallet from the Pallets db inventory ****/
	@DeleteMapping(path = "/removep/{id}")
	@CacheEvict
	public long deletePallet (@PathVariable Long id) 
	{
		prepo.deleteById(id);
		return id;
	}
	
	/****removes a WH SLOT from the slots db  ****/
	@DeleteMapping(path = "/removes/{id}")
	@CacheEvict
	public int deleteSlot (@PathVariable Integer id) 
	{
		srepo.deleteById(id);
		return id;
	}
	

//	/****removes a WH SLOT from the slots db  ****/
//	@DeleteMapping(path = "/removes/{id}")
//	public String removeSlot (@PathVariable int id) 
//	{
//		return dao.deleteSlot(id);
//	}
//	/****get a all empty slots available in the WH for storage, with a given height limit ****/

	
	
}
