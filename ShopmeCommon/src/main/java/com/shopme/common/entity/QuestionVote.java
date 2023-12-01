package com.shopme.common.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "questions_votes")
public class QuestionVote {

	public static final int VOTE_UP_POINT = 1;
	public static final int VOTE_DOWN_POINT = -1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	private int votes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public void voteUp() {
		this.votes = VOTE_UP_POINT;
	}
	
	public void voteDown() {
		this.votes = VOTE_DOWN_POINT;
	}

	@Transient
	public boolean isUpvoted() {
		return this.votes == VOTE_UP_POINT;
	}
	
	@Transient
	public boolean isDownvoted() {
		return this.votes == VOTE_DOWN_POINT;
	}
}
