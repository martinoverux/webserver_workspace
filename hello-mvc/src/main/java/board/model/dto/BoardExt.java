package board.model.dto;

import java.util.List;

public class BoardExt extends Board {

	private int attachCount;
	private int commentCount;
	private List<Attachment> attachments; 
	private List<BoardComment> comments;

	public int getAttachCount() {
		return attachCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public List<BoardComment> getComments() {
		return comments;
	}

	public void setComments(List<BoardComment> comments) {
		this.comments = comments;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	
	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	
	public List<BoardComment> getBoardComments(){
		return comments;
	}
	
	public void setBoardComments(List<BoardComment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "BoardExt [attachCount=" + attachCount + ", attachments=" + attachments + ", comments=" + comments
				+ ", getNo()=" + getNo() + ", getTitle()=" + getTitle() + ", getMemberId()=" + getMemberId()
				+ ", getContent()=" + getContent() + ", getReadCount()=" + getReadCount() + ", getRegDate()="
				+ getRegDate() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}




	
	
	
	
	
}
