package com.shopme.question;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopme.ControllerHelper;
import com.shopme.common.entity.Customer;
import com.shopme.common.entity.Question;
import com.shopme.common.exception.ProductNotFoundException;

@RestController
public class QuestionRestController {

    @Autowired private ControllerHelper controllerHelper;
	
	@Autowired private QuestionService questionService;
	
	@PostMapping("/post_question/{productId}")
	public ResponseEntity<?> postQuestion(@RequestBody Question question,
			@PathVariable(name = "productId") Integer productId,
			HttpServletRequest request) throws ProductNotFoundException, IOException {
	
		Customer customerUser = controllerHelper.getAuthenticatedCustomer(request);
		if (customerUser == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		questionService.saveNewQuestion(question, customerUser, productId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
