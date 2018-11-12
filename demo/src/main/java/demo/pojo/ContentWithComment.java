package demo.pojo;

import java.util.List;

public class ContentWithComment {
	
	private ContentWithBLOBs contentWithBLOBs;
	
	private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public ContentWithBLOBs getContentWithBLOBs() {
		return contentWithBLOBs;
	}

	public void setContentWithBLOBs(ContentWithBLOBs contentWithBLOBs) {
		this.contentWithBLOBs = contentWithBLOBs;
	}
	
	
	

}
