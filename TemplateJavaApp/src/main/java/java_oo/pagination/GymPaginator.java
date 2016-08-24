package java_oo.pagination;

import java.util.ArrayList;
import java.util.List;

public class GymPaginator extends Paginator {

	private Integer totalPagePositions = null;

	public GymPaginator(int totalNumberOfRecords, int itemPerPage) {
		super(totalNumberOfRecords, itemPerPage);
		setTotalPagePositions(5);
	}

	public void setTotalPagePositions(Integer totalPagePositions) {
		this.totalPagePositions = totalPagePositions;
	}

	public Integer getTotalPagePositions() {
		return totalPagePositions;
	}

	public String displayPaginatorViewStatus() {
		return "Viewing " + getStartPageCount() + "-" + getEndPageCount() + " of " + getTotalNumberOfRecords()
				+ " Items";
	}

	public List<PageSlotDetails> getPaginatorSlots() {

		List<PageSlotDetails> pID = new ArrayList<PageSlotDetails>();
		pID.add(new PageSlotDetails("<"));

		for (int slot = 1; slot <= getTotalPagePositions(); slot++) {

			switch (slot) {

			case 1: {
				pID.add(new PageSlotDetails(1, getCurrentPageClickNumber()));
				break;
			}
			case 2: {

				// all item comes in 1st page so skip 2nd slot
				if (getTotalPage() < 2) {
					break;
				}

				// always display value 2 when total page is less or equal to 5
				if (getTotalPage() <= 5) {
					pID.add(new PageSlotDetails(2, getCurrentPageClickNumber()));
					break;
				}

				// display 2 if user is within any of the first 3 slot
				if (getCurrentPageClickNumber() <= 3) {
					pID.add(new PageSlotDetails(2, getCurrentPageClickNumber()));
					break;
				}

				pID.add(new PageSlotDetails(".."));

				break;
			}
			case 3: {

				// all item comes in 2ns page so skip 3rd slot
				if (getTotalPage() < 3) {
					break;
				}

				// always display value 3 when total page is less or equal to 5
				if (getTotalPage() <= 5) {
					pID.add(new PageSlotDetails(3, getCurrentPageClickNumber()));
					break;
				}

				// display 3 if user is within any of the first 3 slot
				if (getCurrentPageClickNumber() <= 3) {
					pID.add(new PageSlotDetails(3, getCurrentPageClickNumber()));
					break;
				}

				boolean cannotReachEndByTwoClicks = getCurrentPageClickNumber() + 2 < getTotalPage();

				if (cannotReachEndByTwoClicks) {
					pID.add(new PageSlotDetails(getCurrentPageClickNumber(), getCurrentPageClickNumber()));
					break;
				}

				pID.add(new PageSlotDetails(getTotalPage() - 2, getCurrentPageClickNumber()));

				break;
			}
			case 4: {

				// all item comes in 3 pages so skip 4th slot
				if (getTotalPage() < 4) {
					break;
				}

				// always display value 4 when total page is less or equal to 5
				if (getTotalPage() <= 5) {
					pID.add(new PageSlotDetails(4, getCurrentPageClickNumber()));
					break;
				}

				boolean canReachEndByTwoClick = (getCurrentPageClickNumber() + 2) >= getTotalPage();

				if (!canReachEndByTwoClick) {
					pID.add(new PageSlotDetails(".."));
					break;
				}

				boolean canReachEndByThreeClick = (getCurrentPageClickNumber() + 3) >= getTotalPage();

				if (canReachEndByThreeClick) {
					pID.add(new PageSlotDetails(getTotalPage() - 1, getCurrentPageClickNumber()));
					break;
				}

			}
			case 5: {

				if (getTotalPage() < 5) {
					break;
				}

				pID.add(new PageSlotDetails(getTotalPage(), getCurrentPageClickNumber()));
				break;
			}

			}

		}
		pID.add(new PageSlotDetails(">"));

		return pID;
	}

}
