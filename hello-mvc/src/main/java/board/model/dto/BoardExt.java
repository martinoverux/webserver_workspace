package board.model.dto;

import java.sql.Date;
import java.util.List;

public class BoardExt extends Board {

	private int attachCount;
	private List<Attachment> attachments;
	
	
	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public BoardExt() {
		super();
	}


	public int getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}

	@Override
	public String toString() {
		return "BoardExt [attachCount=" + attachCount + ", attachments=" + attachments + ", getNo()=" + getNo()
				+ ", getTitle()=" + getTitle() + ", getMemberId()=" + getMemberId() + ", getContent()=" + getContent()
				+ ", getReadCount()=" + getReadCount() + ", getRegDate()=" + getRegDate() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}



	
	
}
