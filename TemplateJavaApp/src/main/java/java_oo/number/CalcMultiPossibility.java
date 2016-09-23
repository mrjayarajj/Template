package java_oo.number;

public class CalcMultiPossibility {

	public static void main(String args[]) {
		
		NumberNode head = new NumberNode(1);
		NumberNode remaining = new NumberNode(2, new NumberNode(5, head));
		head.setNext(remaining);

		NumberNode outerLoopRef = null;
		NumberNode outerCurrent = head;

		while (outerLoopRef == null || outerLoopRef != head) {

			System.out.println(outerCurrent);

			NumberNode innerLoopRef = null;
			NumberNode innerCurrent = outerCurrent;
			Integer newValue = 1;

			while (innerLoopRef == null || innerLoopRef != outerCurrent) {

				System.out.println("        " + innerCurrent);

				if (outerCurrent != innerCurrent) {
					newValue = newValue * innerCurrent.getValue();
				}

				innerLoopRef = innerCurrent.getNext();
				innerCurrent = innerCurrent.getNext();

			}

			outerCurrent.setNewValue(newValue);

			outerLoopRef = outerCurrent.getNext();
			outerCurrent = outerCurrent.getNext();

		}

		NumberNode loopRef = null;
		outerCurrent = head;

		while (loopRef == null || loopRef != head) {

			System.out.println(outerCurrent.printAllNodeValues());

			loopRef = outerCurrent.getNext();
			outerCurrent = outerCurrent.getNext();
		}

	}

}

// 1 2 5
// 10 5 2

class NumberNode {

	private Integer value;

	private Integer newValue;

	private NumberNode next;

	NumberNode(Integer value) {
		this.value = value;
	}

	NumberNode(Integer value, NumberNode next) {
		this.value = value;
		this.next = next;
	}

	public void setNewValue(Integer newValue) {
		this.newValue = newValue;
	}

	public void setNext(NumberNode next) {
		this.next = next;
	}

	public NumberNode getNext() {
		return this.next;
	}

	public Integer getValue() {
		return this.value;
	}

	public Integer getNewValue() {
		return this.newValue;
	}

	public String toString() {
		return this.getValue() + "";
	}

	public String printAllNodeValues() {
		return "(OL:" + this.getValue() + " NV:" + this.getNewValue() + ") ";
	}
}