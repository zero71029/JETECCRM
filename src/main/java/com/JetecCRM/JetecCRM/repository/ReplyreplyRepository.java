package com.JetecCRM.JetecCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.ReplyreplyBean;

public interface ReplyreplyRepository extends JpaRepository<ReplyreplyBean, String>{

	void deleteByReplyid(String replyId);

}
