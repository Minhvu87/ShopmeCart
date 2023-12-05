package com.shopme.review.vote;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopme.common.entity.Review;
import com.shopme.review.ReviewRepository;
import com.shopme.review.vote.VoteResult;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewVoteRestControllerTests {

	@Autowired private ReviewRepository reviewRepo;
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	
	@Test
	public void testVoteNotLogin() throws Exception {
		String requestURL = "/vote_review/1/up";
		
		MvcResult mvcResult = mockMvc.perform(post(requestURL).with(csrf()))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
		
		String json = mvcResult.getResponse().getContentAsString();
		VoteResult voteResult = objectMapper.readValue(json, VoteResult.class);
		
		assertFalse(voteResult.isSuccessful());
		assertThat(voteResult.getMessage()).contains("You must login");
	}
	
	@Test
	@WithMockUser(username = "tina.jamerson.2021@gmail.com", password = "tina2020")
	public void testVoteNonExistReview() throws Exception {
		String requestURL = "/vote_review/123/up";
		
		MvcResult mvcResult = mockMvc.perform(post(requestURL).with(csrf()))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
		
		String json = mvcResult.getResponse().getContentAsString();
		VoteResult voteResult = objectMapper.readValue(json, VoteResult.class);
		
		assertFalse(voteResult.isSuccessful());
		assertThat(voteResult.getMessage()).contains("no longer exists");
	}	
	
	@Test
	@WithMockUser(username = "tina.jamerson.2021@gmail.com", password = "tina2020")
	public void testVoteUp() throws Exception {
		Integer reviewId = 20;
		String requestURL = "/vote_review/" + reviewId + "/up";
		
		Review review = reviewRepo.findById(reviewId).get();
		int voteCountBefore = review.getVotes();
		
		MvcResult mvcResult = mockMvc.perform(post(requestURL).with(csrf()))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
		
		String json = mvcResult.getResponse().getContentAsString();
		VoteResult voteResult = objectMapper.readValue(json, VoteResult.class);
		
		assertTrue(voteResult.isSuccessful());
		assertThat(voteResult.getMessage()).contains("successfully voted up");
		
		int voteCountAfter = voteResult.getVoteCount();
		assertEquals(voteCountBefore + 1, voteCountAfter);
		
	}
	
	@Test
	@WithMockUser(username = "tina.jamerson.2021@gmail.com", password = "tina2020")
	public void testUndoVoteUp() throws Exception {
		Integer reviewId = 20;
		String requestURL = "/vote_review/" + reviewId + "/up";
		
		Review review = reviewRepo.findById(reviewId).get();
		int voteCountBefore = review.getVotes();
		
		MvcResult mvcResult = mockMvc.perform(post(requestURL).with(csrf()))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
		
		String json = mvcResult.getResponse().getContentAsString();
		VoteResult voteResult = objectMapper.readValue(json, VoteResult.class);
		
		assertTrue(voteResult.isSuccessful());
		assertThat(voteResult.getMessage()).contains("unvoted up");
		
		int voteCountAfter = voteResult.getVoteCount();
		assertEquals(voteCountBefore - 1, voteCountAfter);
		
	}	
}
