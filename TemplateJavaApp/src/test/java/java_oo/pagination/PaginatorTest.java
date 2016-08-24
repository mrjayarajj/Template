package java_oo.pagination;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/*
1	1	24
2	25	48
3	49	72
4	73	96
5	97	120
6	121	144
7	145	155
*/
public class PaginatorTest {

	@Test
	public void testPaginatorWhenTotalPageEqualToAndLessThanFive() {
		Paginator paginator = new GymPaginator(120, 24);
		List<PageSlotDetails> expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 1));
		expectedOutput.add(new PageSlotDetails(2, 1));
		expectedOutput.add(new PageSlotDetails(3, 1));
		expectedOutput.add(new PageSlotDetails(4, 1));
		expectedOutput.add(new PageSlotDetails(5, 1));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);

		paginator = new GymPaginator(96, 24);
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 1));
		expectedOutput.add(new PageSlotDetails(2, 1));
		expectedOutput.add(new PageSlotDetails(3, 1));
		expectedOutput.add(new PageSlotDetails(4, 1));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
		paginator.clickNext();
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 2));
		expectedOutput.add(new PageSlotDetails(2, 2));
		expectedOutput.add(new PageSlotDetails(3, 2));
		expectedOutput.add(new PageSlotDetails(4, 2));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
		paginator.clickNext();
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 3));
		expectedOutput.add(new PageSlotDetails(2, 3));
		expectedOutput.add(new PageSlotDetails(3, 3));
		expectedOutput.add(new PageSlotDetails(4, 3));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);

		paginator = new GymPaginator(72, 24);
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 1));
		expectedOutput.add(new PageSlotDetails(2, 1));
		expectedOutput.add(new PageSlotDetails(3, 1));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);

		paginator = new GymPaginator(48, 24);
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 1));
		expectedOutput.add(new PageSlotDetails(2, 1));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);

		paginator = new GymPaginator(24, 24);
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 1));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
	}

	@Test
	public void testPaginatorWhenTotalPageEqualToAndLessThanSix() {
		Paginator paginator = new GymPaginator(144, 24);
		List<PageSlotDetails> expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 1));
		expectedOutput.add(new PageSlotDetails(2, 1));
		expectedOutput.add(new PageSlotDetails(3, 1));
		expectedOutput.add(new PageSlotDetails(".."));
		expectedOutput.add(new PageSlotDetails(6, 1));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
		paginator.clickPage(4);
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 4));
		expectedOutput.add(new PageSlotDetails(".."));
		expectedOutput.add(new PageSlotDetails(4, 4));
		expectedOutput.add(new PageSlotDetails(5, 4));
		expectedOutput.add(new PageSlotDetails(6, 4));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
		paginator.clickNext();
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 5));
		expectedOutput.add(new PageSlotDetails(".."));
		expectedOutput.add(new PageSlotDetails(4, 5));
		expectedOutput.add(new PageSlotDetails(5, 5));
		expectedOutput.add(new PageSlotDetails(6, 5));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
	}

	@Test
	public void testPaginatorWhenTotalPageEqualToAndLessThanSeven() {
		Paginator paginator = new GymPaginator(155, 24);
		List<PageSlotDetails> expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 1));
		expectedOutput.add(new PageSlotDetails(2, 1));
		expectedOutput.add(new PageSlotDetails(3, 1));
		expectedOutput.add(new PageSlotDetails(".."));
		expectedOutput.add(new PageSlotDetails(7, 1));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
		paginator.clickPage(4);
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 4));
		expectedOutput.add(new PageSlotDetails(".."));
		expectedOutput.add(new PageSlotDetails(4, 4));
		expectedOutput.add(new PageSlotDetails(".."));
		expectedOutput.add(new PageSlotDetails(7, 4));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
		paginator.clickNext();
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 5));
		expectedOutput.add(new PageSlotDetails(".."));
		expectedOutput.add(new PageSlotDetails(5, 5));
		expectedOutput.add(new PageSlotDetails(6, 5));
		expectedOutput.add(new PageSlotDetails(7, 5));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
		paginator.clickPage(3);
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 3));
		expectedOutput.add(new PageSlotDetails(2, 3));
		expectedOutput.add(new PageSlotDetails(3, 3));
		expectedOutput.add(new PageSlotDetails(".."));
		expectedOutput.add(new PageSlotDetails(7, 3));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
		paginator.clickPrevious();
		expectedOutput = new ArrayList<PageSlotDetails>();
		expectedOutput.add(new PageSlotDetails("<"));
		expectedOutput.add(new PageSlotDetails(1, 2));
		expectedOutput.add(new PageSlotDetails(2, 2));
		expectedOutput.add(new PageSlotDetails(3, 2));
		expectedOutput.add(new PageSlotDetails(".."));
		expectedOutput.add(new PageSlotDetails(7, 2));
		expectedOutput.add(new PageSlotDetails(">"));
		testPaginator(paginator, expectedOutput);
	}

	private void testPaginator(Paginator paginator, List<PageSlotDetails> expectedOutput) {

		displayWebPage(paginator);

		for (int i = 0; i < expectedOutput.size(); i++) {
			if (expectedOutput.get(i).getDisplayNumber() == -1) {
				Assert.assertEquals(expectedOutput.get(i).getDisplayText(),
						paginator.getPaginatorSlots().get(i).getDisplayText());
			} else {
				Assert.assertEquals(expectedOutput.get(i).getDisplayNumber(),
						paginator.getPaginatorSlots().get(i).getDisplayNumber());
			}

			Assert.assertEquals(expectedOutput.get(i).isSelected(), paginator.getPaginatorSlots().get(i).isSelected());
		}

	}

	public void displayWebPage(Paginator paginator) {
		System.out.println("------------------------");
		System.out.println(paginator.displayPaginatorViewStatus());
		System.out.println("------------------------");
		paginator.displayPaginatorView();
		System.out.println("------------------------");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

	}
}
