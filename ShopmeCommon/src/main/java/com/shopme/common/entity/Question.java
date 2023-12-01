package com.shopme.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.shopme.common.entity.product.Product;

@Entity
@Table(name = "questions")
public class Question extends IdBasedEntity{

	@Column(name = "question")
	private String questionContent;
	
	private String answer;
	private int votes;
	private boolean approved;
	
	@Column(name = "ask_time")
	private Date askTime;
	
	@Column(name = "answer_time")
	private Date answerTime;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "answerer_id")
	private User answerer;
	
	@ManyToOne
	@JoinColumn(name = "asker_id")
	private Customer asker;

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String question) {
		this.questionContent = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getAnswerer() {
		return answerer;
	}

	public void setAnswerer(User answerer) {
		this.answerer = answerer;
	}

	public Customer getAsker() {
		return asker;
	}

	public void setAsker(Customer asker) {
		this.asker = asker;
	}

	public Date getAskTime() {
		return askTime;
	}

	public void setAskTime(Date askTime) {
		this.askTime = askTime;
	}
	
	@Transient
	public boolean isAnswered() {
		return this.answer != null && !answer.isEmpty();
	}

	@Transient
	public String getProductName() {
		return this.product.getName();
	}
	
	@Transient
	public String getAskerFullName() {
		return asker.getFirstName() + " " + asker.getLastName();
	}
	
	@Transient
	public String getAnswererFullName() {
		if (answerer != null) {
			return answerer.getFirstName() + " " + answerer.getLastName();
		}
		return "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean isUpvotedByCurrentCustomer() {
		return upvotedByCurrentCustomer;
	}

	public void setUpvotedByCurrentCustomer(boolean isUpvotedByCurrentCustomer) {
		this.upvotedByCurrentCustomer = isUpvotedByCurrentCustomer;
	}

	
	public boolean isDownvotedByCurrentCustomer() {
		return downvotedByCurrentCustomer;
	}

	public void setDownvotedByCurrentCustomer(boolean isDownvotedByCurrentCustomer) {
		this.downvotedByCurrentCustomer = isDownvotedByCurrentCustomer;
	}

	@Transient
	private boolean upvotedByCurrentCustomer;
	
	@Transient	
	private boolean downvotedByCurrentCustomer;
}
