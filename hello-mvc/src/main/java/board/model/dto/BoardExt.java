package board.model.dto;

import java.sql.Date;

public class BoardExt extends Board {

	private int attachCount;

	public int getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}

	@Override
	public String toString() {
		return "BoardExt [attachCount=" + attachCount + ", getNo()=" + getNo() + ", getTitle()=" + getTitle()
				+ ", getMemberId()=" + getMemberId() + ", getContent()=" + getContent() + ", getReadCount()="
				+ getReadCount() + ", getRegDate()=" + getRegDate() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
}
