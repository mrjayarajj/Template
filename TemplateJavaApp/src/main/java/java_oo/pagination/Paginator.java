package java_oo.pagination;


import java.util.List;

public abstract class Paginator {

	private Integer totalNumberOfRecords;

	private Integer recordPerPage;

	private Integer currentPageClickNumber = 1;

	private Integer totalPage = null;

	public Paginator(int totalNumberOfRecords, int recordPerPage) {
		this.totalNumberOfRecords = totalNumberOfRecords;
		this.recordPerPage = recordPerPage;
		Double d = new Double(new Double(getTotalNumberOfRecords()) / new Double(getRecordPerPage()));
		this.totalPage = new Double(Math.ceil(d)).intValue();
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public Integer getCurrentPageClickNumber() {
		return currentPageClickNumber;
	}

	public int getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public int getStartPageCount() {

		int endPageNumber = getCurrentPageClickNumber() * getRecordPerPage();
		return (endPageNumber - getRecordPerPage()) + 1;

	}

	public int getEndPageCount() {

		int endPageNumber = getCurrentPageClickNumber() * getRecordPerPage();

		if (endPageNumber > getTotalNumberOfRecords()) {
			return getTotalNumberOfRecords();
		} else {
			return endPageNumber;
		}
	}

	public abstract String displayPaginatorViewStatus();

	public abstract List<PageSlotDetails> getPaginatorSlots();

	public void displayPaginatorView() {

		List<PageSlotDetails> pids = getPaginatorSlots();

		for (int i = 0; i < pids.size(); i++) {

			PageSlotDetails pIdStatus = pids.get(i);

			if (i == pids.size() - 1) {
				System.out.println(pIdStatus);
			} else {
				System.out.print(pIdStatus);
			}

		}

	}

	public void clickNext() {
		this.currentPageClickNumber++;
	}

	public void clickPrevious() {
		this.currentPageClickNumber--;
	}

	public void clickPage(int currentPageClickNumber) {
		this.currentPageClickNumber = currentPageClickNumber;
	}

}
