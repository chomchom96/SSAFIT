package com.ssafy.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.UserDetail;
import com.ssafy.board.model.dto.UserSchedule;
import com.ssafy.board.model.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="서비스 컨트롤러(매칭, 스케줄)")
@CrossOrigin("*")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/product")
	@ApiOperation(value="사용자 정보 입력받음")
	public ResponseEntity<?> startService(@RequestBody UserDetail userDetail){
		try {
			productService.registDetail(userDetail);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/{userId}")
	@ApiOperation(value="사용자 정보 반환")
	public ResponseEntity<?> getUserDetail(@PathVariable String userId){
		try {
			UserDetail userDetail = productService.getDetail(userId);
			if (userDetail != null)
				return new ResponseEntity<UserDetail>(userDetail,HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/product/{userId}")
	@ApiOperation(value="사용자 정보 반환")
	public ResponseEntity<?> modifyUserDetail(@RequestBody UserDetail userDetail){
		try {
			productService.modifyDetail(userDetail);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/product/{userId}")
	@ApiOperation(value="유저가 트레이너 선택")
	public ResponseEntity<Void> getTrainer(@RequestBody String trainerId, @PathVariable String userId){
		System.out.println(trainerId);
//		trainerId = trainerId.substring(14);
//		trainerId = trainerId.substring(0, trainerId.length() - 2);
//		System.out.println(trainerId);
		try {
			productService.matchTrainer(trainerId, userId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/product/schedule/{userId}")
	@ApiOperation(value="유저에 스케줄 등록")
	public ResponseEntity<?> writeSchedule(@RequestBody String jsonSchedule, @PathVariable String userId){
		UserSchedule schedule = new UserSchedule();
		schedule.setUserId(userId);
		schedule.setUserSchedule(jsonSchedule);
		try 
		{
			productService.writeSchedule(schedule);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/product/schedule/{userId}")
	@ApiOperation(value="유저 스케줄 업데이트")
	public ResponseEntity<?> updateSchedule(@RequestBody String jsonSchedule, @PathVariable String userId){
		UserSchedule schedule = new UserSchedule();
		schedule.setUserId(userId);
		schedule.setUserSchedule(jsonSchedule);
		System.out.println(jsonSchedule);
		try 
		{
			productService.updateSchedule(schedule);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/product/schedule/{userId}")
	@ApiOperation(value="유저 스케줄 업데이트")
	public ResponseEntity<?> deleteSchedule(@PathVariable String userId){
	
		try 
		{
			productService.deleteSchedule(userId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/schedule/{userId}")
	public ResponseEntity<?> getSchedule(@PathVariable String userId){
		System.out.println(userId);
		UserSchedule schedule = productService.getSchedule(userId);
		try {
			if (schedule != null) return new ResponseEntity<UserSchedule>(schedule, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
}
