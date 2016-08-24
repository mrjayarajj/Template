package java_oo.pagination;


public class PageSlotDetails {

	private int displayNumber = -1;

	private String displayText;

	private boolean isSelected = false;

	public PageSlotDetails(int displayNumber) {
		this.displayNumber = displayNumber;
	}

	public PageSlotDetails(String displayText) {
		this.displayText = displayText;
	}

	public PageSlotDetails(int displayNumber, Integer currentPageClickNumber) {
		this.displayNumber = displayNumber;

		if (displayNumber == currentPageClickNumber) {
			this.isSelected = true;
		}
	}

	public int getDisplayNumber() {
		return displayNumber;
	}

	public String getDisplayText() {
		return displayText;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public String toString() {
		if (isSelected()) {
			return " (" + (getDisplayNumber() == -1 ? getDisplayText() : getDisplayNumber()) + ") ";
		} else {
			return " " + (getDisplayNumber() == -1 ? getDisplayText() : getDisplayNumber()) + " ";
		}
	}

}
