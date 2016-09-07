package java_oo.node;

import java.util.HashSet;
import java.util.Set;

public class TestNode {

	public static void main(String args[]) throws CloneNotSupportedException {
		testNode();
		// System.out.println(testSring("Jayaraj Jaganthan"));

	}

	public static String testSring(String name) {

		if (name == null) {
			return null;
		}

		char nameAsCharArray[] = name.toCharArray();
		int lengthOfName = name.length();
		StringBuffer sbf = new StringBuffer();
		for (int i = lengthOfName; i > 0; i--) {
			sbf.append(nameAsCharArray[i - 1]);
		}

		return sbf.toString();

	}

	private static Node generateRandomNode() {

		// String apla[] = { "a", "b", "c" };
		String apla[] = { "a", "b", "c", "d", "e", "f", "g" };

		Node head = null;
		Node currentNode = null;

		for (int i = 0; i < (int) (Math.random() * 100); i++) {

			String value = apla[(int) (Math.random() * apla.length)];
			Node newNode = new Node(value);

			if (head == null) {

				head = newNode;
				currentNode = head;
			} else {
				currentNode.setNext(newNode);
				currentNode = newNode;
			}

		}

		return head;

	}

	public static void testRemovingDuplicateNode(Node head) {
		// new Node("a", new Node("1", new Node("2", new Node("a", new Node("3",
		// new Node("1"))))));
		// new Node("c", new Node("a", new Node("a", new Node("a", new
		// Node("c")))));
		NodeExecutor nodeExecutor = new NodeExecutor(head);
		System.out.println(head.printHierarchy());
		nodeExecutor.removeDuplicateNode();
		System.out.println(head.printHierarchy());
	}

	public static void testExtractingUniueNode(Node head) throws CloneNotSupportedException {
		// new Node("a", new Node("1", new Node("2", new Node("a", new
		// Node("3", new Node("1"))))));
		// new Node("g", new Node("f", new Node("f", new Node("d", new Node("f",
		// new Node("d", new Node("e")))))));
		NodeExecutor nodeExecutor = new NodeExecutor(head);
		System.out.println(head.printHierarchy());
		Node newNode = nodeExecutor.extractUniqueNode();
		System.out.println(newNode.printHierarchy());
	}

	public static void testNode() throws CloneNotSupportedException {
		Node head = generateRandomNode();
		testExtractingUniueNode(head);
		System.out.println("---------------");
		testRemovingDuplicateNode(head);
	}

}

class NodeExecutor {

	private Node head;

	public NodeExecutor(Node head) {
		this.head = head;
	}

	/**
	 * to track duplicate node
	 */
	private Set<Node> uniqueNodeValues = new HashSet<Node>();

	public void removeDuplicateNode() {
		uniqueNodeValues.add(head);
		removeDuplicateNode(head);
	}

	public Node extractUniqueNode() throws CloneNotSupportedException {

		// adding head node, going fwd we will add only the next node
		uniqueNodeValues.add(head);

		// create a unique node only if there is a valid current node
		if (head != null) {

			uniqueHead = head.clone();

			// hold the last node of the newly constructed node

			clonedNextNodePlaceHolder = uniqueHead;

		}

		extractUniqueNode(head);

		return uniqueHead;
	}

	private boolean isNextNodeAlreadyExist(Node node) {

		if (node.getNext() == null) {
			return false;
		}

		return uniqueNodeValues.add(node.getNext()) == false;
	}

	/**
	 * hold newly constructed root node
	 */
	private Node uniqueHead = null;

	/**
	 * hold the last node of the newly constructed root node
	 */
	private Node clonedNextNodePlaceHolder = null;

	/**
	 * it return the node that not already exist
	 * 
	 * @param node
	 * @return
	 */
	private Node getUnProcessedNode(Node node) {

		System.out.println("Processing for Node value " + (node != null ? node.getValue() : "null") + " which "
				+ (node != null && node.hasNext() ? "has" : " dont have") + " the next node ");

		if (isNextNodeAlreadyExist(node)) {
			return getUnProcessedNode(node.getNext());
		} else {
			return node.getNext();
		}

	}

	/**
	 * extract and clone unique node
	 * 
	 * @param current
	 * @throws CloneNotSupportedException
	 */
	private void extractUniqueNode(Node current) throws CloneNotSupportedException {

		if (current != null && current.hasNext()) {

			// iterate the given node and find the node which not yet processed
			Node unProcessedNode = getUnProcessedNode(current);

			// if there is at least one input node and there are un processed
			// node then map it to the last node
			if (clonedNextNodePlaceHolder != null && unProcessedNode != null) {
				clonedNextNodePlaceHolder.setNext(unProcessedNode.clone());
				// replace the last node with the up processed node
				clonedNextNodePlaceHolder = clonedNextNodePlaceHolder.getNext();
			}

			// do a recurssive until it reached the last node
			if (unProcessedNode != null) {
				extractUniqueNode(unProcessedNode);
			}

		}
	}

	private void removeDuplicateNode(Node current) {

		System.out.println("Processing for Node value " + (current != null ? current.getValue() : "null") + " which "
				+ (current != null && current.hasNext() ? "has" : " dont have") + " the next node ");

		if (current != null && current.hasNext()) {

			/**
			 * node value already exist so skip it
			 */
			if (isNextNodeAlreadyExist(current)) {

				// iterate until a unique node is identified to proceed further
				removeDuplicateNode(current.getNext());

				Node next = current.getNext();
				Node next2next = next.getNext();
				current.setNext(next2next);// Referring next2next into next, so
											// that it will skip the duplicate
											// node

			} else {

				// iterate the entire node hierarchy
				removeDuplicateNode(current.getNext());
			}

		} // end of if

	}

}

class Node implements Cloneable {

	private String value;

	private Node next;

	public Node(String value, Node next) {
		this.value = value;
		this.next = next;
	}

	public String printHierarchy() {
		return this.value + "(" + this.superHashCode() + "h)<"
				+ (this.next != null ? this.next.superHashCode() + "h" : "null") + ">  "
				+ (this.next != null ? this.next.printHierarchy() : "null");

	}

	public Node(String value) {
		this.value = value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public boolean hasNext() {
		if (next == null) {
			return false;
		} else {
			return true;
		}
	}

	public Node getNext() {
		return this.next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node clone() throws CloneNotSupportedException {
		Node clonedNode = (Node) super.clone();
		clonedNode.setNext(null);
		return clonedNode;
	}

	public String toString() {
		return this.value + "(" + this.superHashCode() + "h)";
	}

	public boolean equals(Object o) {

		if (o == null) {
			return false;
		}

		if (!(o instanceof Node)) {
			return false;
		}

		if (this == o) {
			return true;
		}

		Node oNode = (Node) o;

		return this.getValue().equals(oNode.getValue());

	}

	public int hashCode() {
		return this.getValue().hashCode();
	}

	private int superHashCode() {
		return super.hashCode();
	}

}