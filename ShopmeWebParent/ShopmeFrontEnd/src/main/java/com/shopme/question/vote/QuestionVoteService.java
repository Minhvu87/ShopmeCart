package com.shopme.question.vote;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Customer;
import com.shopme.common.entity.Question;
import com.shopme.common.entity.QuestionVote;
import com.shopme.question.QuestionRepository;
import com.shopme.review.vote.VoteResult;
import com.shopme.review.vote.VoteType;

@Service
@Transactional
public class QuestionVoteService {

	@Autowired
	private QuestionVoteRepository voteRepo;
	
	@Autowired
	private QuestionRepository questionRepo;
		
	public VoteResult doVote(Integer questionId, Customer customer, VoteType voteType) {
		Question question = null;
		
		try {
			question = questionRepo.findById(questionId).get();
		} catch (NoSuchElementException ex) {
			return VoteResult.fail("The question ID " + questionId + " no longer exists.");
		}
		
		QuestionVote vote = voteRepo.findByQuestionAndCustomer(questionId, customer.getId());
		
		if (vote != null) {
			if (vote.isUpvoted() && voteType.equals(VoteType.UP) || 
					vote.isDownvoted() && voteType.equals(VoteType.DOWN)) {
				return undoVote(vote, questionId, voteType);
			} else if (vote.isUpvoted() && voteType.equals(VoteType.DOWN)) {
				vote.voteDown();
			} else if (vote.isDownvoted() && voteType.equals(VoteType.UP)) {
				vote.voteUp();
			}			
		} else {
			vote = new QuestionVote();
			vote.setQuestion(question);
			vote.setCustomer(customer);
			
			if (voteType.equals(VoteType.UP)) {
				vote.voteUp();
			} else {
				vote.voteDown();
			}			
		}
		
		voteRepo.save(vote);
		questionRepo.updateVoteCount(questionId);
		Integer voteCount = questionRepo.getVoteCount(questionId);
		
		return VoteResult.success("You have successfully voted " + voteType + " that question.", 
				voteCount);
	}
	
	public VoteResult undoVote(QuestionVote vote, Integer questionId, VoteType voteType) {
		voteRepo.delete(vote);
		questionRepo.updateVoteCount(questionId);
		Integer voteCount = questionRepo.getVoteCount(questionId);
		
		return VoteResult.success("You have unvoted " + voteType + " that question.", voteCount);
	}
	
	public void markQuestionsVotedForProductByCustomer(List<Question> listQuestions, Integer productId, Integer customerId) {
		List<QuestionVote> listVotes = voteRepo.findByProductAndCustomer(productId, customerId);
		
		for (QuestionVote aVote : listVotes) {
			Question votedQuestion = aVote.getQuestion();
			if (listQuestions.contains(votedQuestion)) {
				int index = listQuestions.indexOf(votedQuestion);
				Question question = listQuestions.get(index);
				
				if (aVote.isUpvoted()) {
					question.setUpvotedByCurrentCustomer(true);
				} else if (aVote.isDownvoted()) {
					question.setDownvotedByCurrentCustomer(true);
				}
			}
		}
	}
}
