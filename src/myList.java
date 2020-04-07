package Assignment2;

import java.util.Comparator;
import java.util.Iterator;

public class myList<T> implements Iterable<T>{
	private Node<T> head = null;
	private Comparator<T> c =null;

	public myList(Comparator<T> comp) {
		this.head = null;
		this.c = comp;
	}

	public void clear(){
		this.head = null;
	}

	public int getSize(){
		Node<T> temp = this.head;
		int count = 0;
		while(temp != null){
			temp = temp.getNext();
			count++;
		}

		return count;

	}

	public void addElements(T in) {
		if(this.head==null) {
			Node<T> newNode = new Node<T>(in);
			this.head=newNode;
		}else {
			Node<T> temp = this.head;
			Node<T> newNode = new Node<T>(in);
			if(this.c.compare(temp.getItem(), in)==1) {
				this.head=newNode;
				this.head.setNext(temp);
			}else if(this.c.compare(temp.getItem(), in)==0) {
				this.head=newNode;
				this.head.setNext(temp);
			}else if(this.c.compare(temp.getItem(), in)==-1) {
				int size = this.getSize();
				if(this.head.getNext() != null) {
					for(int i =1;i<size;i++) {
						Node<T> theNode = this.getNodeAtIndex(i);
						if(this.c.compare(theNode.getItem(), in)==1) {
							Node<T> preNode = this.getNodeAtIndex(i-1);
							preNode.setNext(newNode);
							newNode.setNext(theNode);
							break;
						}else if(this.c.compare(theNode.getItem(), in)==-1) {
							if(theNode.getNext()==null) {
								theNode.setNext(newNode);
							}else {
								continue;
							}
						}else if(this.c.compare(theNode.getItem(), in)==0) {
							if(theNode.getNext()==null) {
								theNode.setNext(newNode);
								break;
							}else {
								Node<T> preNode = this.getNodeAtIndex(i-1);
								preNode.setNext(newNode);
								newNode.setNext(theNode);
								break;
							}
						}

					}
				}else {
					this.head.setNext(newNode);
				}
			}
		}
	}
	
	public Node<T> getNodeAtIndex(int position){
		Node<T> temp = this.head;

		if(position < 0){
			throw new IndexOutOfBoundsException();
		}else if (position > this.getSize()){
			throw new IndexOutOfBoundsException();
		}

		if(position == 0){
			return this.head;
		}else if(position > 0) {
			for(int i = 0; i < position; i++){
				temp = temp.getNext();

			}
		}
		return temp;
	}
	
	public String toString() {
		if(this.head == null) {
			return "[empty]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<T> curr = head;
		while(curr.hasNext()) {
			sb.append(curr.getItem());
			sb.append("\n");
			curr = curr.getNext();
		}
		sb.append(curr.getItem());
		sb.append("]");
		return sb.toString();
	}
	
	public void remove(T item){
		if(this.head == null){
			return;
		}else if (head.getItem().equals(item)){
			head = head.getNext();
		}else {
			Node<T> current = head;
			Node<T> prev = null;
			while(current.hasNext() && !current.getItem().equals(item)){
				prev = current;
				current = current.getNext();
			}
			
			if(current.getItem().equals(item)){
				prev.setNext(current.getNext());
			}
		}
	}
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>(){
			Node<T> temp = head;
			@Override
			public boolean hasNext() {

				if(temp == null){
					return false;
				}else 
					return true;
			}

			@Override
			public T next() {
				T item = temp.getItem();
				temp = temp.getNext();
				return item;
			}

		};
	}


}