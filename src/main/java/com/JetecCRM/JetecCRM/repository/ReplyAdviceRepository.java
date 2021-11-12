package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.ReplyAdviceBbean;

public interface ReplyAdviceRepository extends JpaRepository<ReplyAdviceBbean, String>{

	List<ReplyAdviceBbean> findByReplyid(String replyId);

	void deleteAllByReplyid(String replyId);

	void deleteByReplyid(String replyId);

	boolean existsByReplyidAndAdviceto(String replyid, Integer adviceto);


}
